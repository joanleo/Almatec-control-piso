package com.almatec.controlpiso.integrapps.services.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.almatec.controlpiso.calidad.dtos.ReporteCalidadDTO;
import com.almatec.controlpiso.integrapps.dtos.ComponenteDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemOpCtDTO;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.entities.ReporteCalidad;
import com.almatec.controlpiso.integrapps.repositories.ReporteCalidadRepository;
import com.almatec.controlpiso.integrapps.services.ReporteCalidadService;
import com.almatec.controlpiso.integrapps.services.VistaOpItemsMaterialesRutaService;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.services.UsuarioService;

@Service
public class ReporteCalidadServiceImpl implements ReporteCalidadService {
	
	private final ReporteCalidadRepository reporteCalidadRepo;
	private final ModelMapper modelMapper;
	private final VistaOpItemsMaterialesRutaService vistaOpItemsMaterialesRutaService;
	private final UsuarioService usuarioService;

	public ReporteCalidadServiceImpl(ReporteCalidadRepository reporteCalidadRepo,
			VistaOpItemsMaterialesRutaService vistaOpItemsMaterialesRutaService,
			UsuarioService usuarioService) {
		super();
		this.reporteCalidadRepo = reporteCalidadRepo;
		this.vistaOpItemsMaterialesRutaService = vistaOpItemsMaterialesRutaService;
		this.usuarioService = usuarioService;
		
		this.modelMapper = new ModelMapper();
        this.modelMapper.createTypeMap(String.class, LocalDateTime.class)
            .setConverter(context -> LocalDateTime.parse(context.getSource(), DateTimeFormatter.ISO_DATE_TIME));
	}

	@Override
	public ReporteCalidad guardarReporteCalidad(ReporteCalidadDTO formCalidad) {
		ReporteCalidad reporte = new ReporteCalidad();
		try {
			if(formCalidad.getId() != null && StringUtils.hasText(formCalidad.getId().toString())) {
				reporte = reporteCalidadRepo.findById(formCalidad.getId())
						.orElseThrow(() -> new NoSuchElementException("Reporte de calidad no encontrado con ID: " + formCalidad.getId()));
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				Usuario usuarioP = usuarioService.ObtenerUsuarioPorNombreUsuario(authentication.getName());
				reporte = modelMapper.map(formCalidad, ReporteCalidad.class);
				reporte.setUsuarioAprueba(usuarioP);
				
			}else {
				reporte = modelMapper.map(formCalidad, ReporteCalidad.class);				
			}
			
			return reporteCalidadRepo.save(reporte);
		} catch (IllegalArgumentException | NoSuchElementException e) {
            throw new RuntimeException("Error al guardar el reporte de calidad: " + e.getMessage(), e);
        }
	}

	@Override
	public ReporteCalidadDTO buscarItemReporteCalidadCt(Long idItemOp, Integer idCT, Integer idOperario, Integer idItem, String tipo) {
		Set<OpCentroTrabajoDTO> ops = vistaOpItemsMaterialesRutaService.buscarItemParteCt(idItemOp, idCT, idItem, tipo);
		if (ops != null && !ops.isEmpty()) {
		    OpCentroTrabajoDTO op = ops.iterator().next();
		    ItemOpCtDTO item = op.getItems().get(0);
		    String descripcion = null;
		    Integer cant = 0;
		    String centroTrabajo = null;
		    String marca = null;
		    Integer idItemFab = 0;
		    Integer idParte = 0;
		    if(Objects.equals(item.getItem_centro_t_id(), idCT)) {
		    	descripcion = item.getItem_desc();
		    	cant = item.getCant_req();
		    	centroTrabajo = item.getItem_centro_t_nombre();
		    	marca = item.getMarca();
		    	idItemFab = item.getItem_id();
		    }
		    if(descripcion == null) {
		    	for(ComponenteDTO componente: item.getComponentes()) {
		    		if(Objects.equals(componente.getMaterial_centro_t_id(), idCT)) {
		    			descripcion = componente.getMaterial_desc();
		    			cant = componente.getMaterial_cant();
		    			idParte = componente.getMaterial_id();
		    			centroTrabajo = componente.getMaterial_centro_t_nombre();
		    		}
		    	}
		    }
		    
		    ReporteCalidadDTO reporte = new ReporteCalidadDTO();
		    reporte.setProyecto(op.getProyecto());
		    reporte.setZona(op.getZona());
		    reporte.setMarca(marca);
		    reporte.setNumOp(Integer.valueOf(op.getOp().split("-")[1]));
		    reporte.setDescripcionItem(descripcion);
		    reporte.setCentroTrabajo(centroTrabajo);
		    reporte.setIdCentroTrabajo(idCT);
		    reporte.setIdItemOp(idItemOp);
		    reporte.setColor(item.getItem_color());
		    reporte.setCantSol(cant);
		    reporte.setIdParte(idParte);
		    reporte.setIdItem(idItemFab);
		    		    
		    return reporte;
		}
		
		return null;
	}

	@Override
	public Page<ReporteCalidadDTO> listarFormularios(int page, int size, String search) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
	    Page<ReporteCalidad> reportesPage;
	    
	    if (search != null && !search.isEmpty()) {
	        reportesPage = reporteCalidadRepo.findByProyectoContainingIgnoreCaseOrZonaContainingIgnoreCaseOrDescripcionItemContainingIgnoreCase(search, search, search, pageable);
	    } else {
	        reportesPage = reporteCalidadRepo.findAll(pageable);
	    }
	    
	    return reportesPage.map(reporte -> modelMapper.map(reporte, ReporteCalidadDTO.class));
	}

	@Override
	public ReporteCalidadDTO obtenerFormularioPorId(Long id) {
	    ReporteCalidad reporte = reporteCalidadRepo.findById(id)
	            .orElseThrow(() -> new NoSuchElementException("Reporte de calidad no encontrado con ID: " + id));
	    return modelMapper.map(reporte, ReporteCalidadDTO.class);
	}
	

}
