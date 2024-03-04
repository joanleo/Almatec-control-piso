package com.almatec.controlpiso.integrapps.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.dtos.CentroOperacion;
import com.almatec.controlpiso.integrapps.dtos.Compania;
import com.almatec.controlpiso.integrapps.dtos.ComponenteDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemOpCtDTO;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.dtos.PiezaOperarioDTO;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.entities.Operario;
import com.almatec.controlpiso.integrapps.entities.RegistroOperDia;
import com.almatec.controlpiso.integrapps.entities.RegistroPieza;
import com.almatec.controlpiso.integrapps.entities.VistaTiemposOperarios;
import com.almatec.controlpiso.integrapps.interfaces.CentroOperacionInterface;
import com.almatec.controlpiso.integrapps.interfaces.CompaniaErp;
import com.almatec.controlpiso.integrapps.repositories.CentroTrabajoRepository;
import com.almatec.controlpiso.integrapps.repositories.OperarioRepository;
import com.almatec.controlpiso.integrapps.services.CentroTrabajoService;
import com.almatec.controlpiso.integrapps.services.OperarioService;
import com.almatec.controlpiso.integrapps.services.RegistroOperDiaService;
import com.almatec.controlpiso.integrapps.services.RegistroPiezaService;
import com.almatec.controlpiso.integrapps.services.ReportePiezaCtService;
import com.almatec.controlpiso.integrapps.services.VistaItemsRutasService;
import com.almatec.controlpiso.integrapps.services.VistaTiemposOperariosService;


@Service
public class CentroTrabajoServiceImpl implements CentroTrabajoService {
	
	@Autowired
	private CentroTrabajoRepository centroTrabajoRepo;
	
	@Autowired
	private RegistroOperDiaService registroOperdiaService;
	
	@Autowired
	private OperarioRepository operarioRepo;
	
	@Autowired
	private VistaItemsRutasService vistaItemsRutasService;
	
	@Autowired
	private VistaTiemposOperariosService vistaTiemposOperariosService;
	
	@Autowired
	private RegistroPiezaService registroPiezaService;
	
	@Autowired
	private ReportePiezaCtService reportePiezaCtService;
	
	@Autowired
	private OperarioService operarioService;

	@Override
	public List<Compania> buscarCompanias() {
		List<CompaniaErp> companiasErp = centroTrabajoRepo.buscarCompanias();
		return convertirACompanias(companiasErp);
	}
	
	private List<Compania> convertirACompanias(List<CompaniaErp> companiasErp) {
        List<Compania> companias = new ArrayList<>();
        for (CompaniaErp companiaErp : companiasErp) {
            Compania compania = new Compania(companiaErp);
            companias.add(compania);
        }
        return companias;
    }

	@Override
	public List<CentroTrabajo> buscarCentrosTrabajo(Integer cia) {
		return centroTrabajoRepo.findByIdCiaAndIsShowTrue(cia);
	}

	@Override
	public CentroTrabajo buscarCentroTrabajo(Integer id) {
		return centroTrabajoRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No se encontro el centro de trabajo"));
	}

	@Override
	public List<CentroOperacion> buscarCentrosOperacion(Integer cia) {
		List<CentroOperacionInterface> centrosOperacionInterface = centroTrabajoRepo.buscarCentrosOperacion(cia);
		List<CentroOperacion> centrosOperacion = new ArrayList<>();
		for(CentroOperacionInterface centroOperacionInterface: centrosOperacionInterface) {
			CentroOperacion centroOperacion = new CentroOperacion(centroOperacionInterface);
			centrosOperacion.add(centroOperacion);
		}
		return centrosOperacion;
	}

	@Transactional
	@Override
	public void guardar(CentroTrabajo centroTrabajo) {
		centroTrabajoRepo.save(centroTrabajo);
		
	}

	@Override
	public String agregarRetirarOperario(OperarioDTO operarioDTO) {
		return registroOperdiaService.agregarRetirarOperario(operarioDTO);
	}

	@Override
	public List<Operario> buscarOperariosCtDia(Integer idCT, Integer idConfigP) {
		List<RegistroOperDia> registros = registroOperdiaService.findByIdCentroTAndIdConfigProceso(idCT, idConfigP);
		List<Operario> operarios = new ArrayList<>();
		for(RegistroOperDia registro:registros) {
			Operario operario = operarioRepo.findById(registro.getIdOperario())
					.orElseThrow(()-> new ResourceNotFoundException("No se encontro el operario con id: " + registro.getIdOperario()));
			operarios.add(operario);
		}
		return operarios;
	}

	@Override
	public Set<OpCentroTrabajoDTO> buscarOpCT(Integer idCT) {
		Set<OpCentroTrabajoDTO> itemsRutas = vistaItemsRutasService.buscarOpCT(idCT);				
		return itemsRutas;
	}

	@Override
	public List<VistaTiemposOperarios> obtenerTiemposOperarios(Integer idProceso) {
		List<VistaTiemposOperarios> tiempos = vistaTiemposOperariosService.obtenerTiemposOperarios(idProceso);		
		return tiempos;
	}

	@Override
	public void asignarActualizarPiezaOperario(Integer idCT, List<PiezaOperarioDTO> piezas) {
		try {
			for(PiezaOperarioDTO pieza: piezas) {
				System.out.println(pieza);
				Integer idProceso = pieza.getIdProceso();
				Integer idOperario = pieza.getIdOperario();
				Integer idPieza = pieza.getIdPieza();
				Integer idPerfil = pieza.getIdPerfil();
				RegistroPieza registro = registroPiezaService.consultaRegistroPieza(idCT, idProceso, idOperario, idPieza);
				LocalDateTime fecha = LocalDateTime.now();
				if(registro != null) {
					Boolean estaActivo = !registro.getIsActivo();
					registro.setIsActivo(estaActivo);
					registro.setFechaEdicion(fecha);
					registroPiezaService.actualizarRegistro(registro);
					continue;
				}
				RegistroPieza nuevoRegistro = new RegistroPieza();
				nuevoRegistro.setIdCT(idCT);
				nuevoRegistro.setIdConfig(idProceso);
				nuevoRegistro.setIdOperario(idOperario);
				nuevoRegistro.setIdItem(idPieza);
				nuevoRegistro.setIdPerfil(idPerfil);
				nuevoRegistro.setFechaCreacion(fecha);
				nuevoRegistro.setIsActivo(pieza.getEstaActivo());
				System.out.println("Nuevo registro: " + nuevoRegistro);
				registroPiezaService.actualizarRegistro(nuevoRegistro);
			}
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error en la asignación de piezas: " + e.getMessage());
	    }
		
	}

	@Override
	public ReporteDTO buscarItemCt(Long idItem, Integer idCT, Integer idOperario) {
		Operario operario = operarioService.buscarOperarioPorId(idOperario);
		Set<OpCentroTrabajoDTO> ops = vistaItemsRutasService.buscarItemCt(idItem, idCT);
		OpCentroTrabajoDTO op = ops.iterator().next();
		ItemOpCtDTO item = op.getItems().get(0);
		String descripcion = null;
		Integer cant = 0;
		String centroTrabajo = null;
		Integer idItemFab = 0;
		Integer idPerfil = 0;
		Integer cantFabricada = 0;
		if(item.getIdCentroTrabajo() == idCT) {
			descripcion = item.getDescripcion();
			cant = item.getCant();
			centroTrabajo = item.getCentroTrabajo();
			idItemFab = item.getIdItemFab();
			cantFabricada = reportePiezaCtService.buscarCantidadesFabricadasConjunto(idItem, idItemFab, idCT);
		}
		if(descripcion == null) {
			for(ComponenteDTO componente: item.getComponentes()) {
				if(componente.getIdCentroTrabajoPerfil() == idCT) {
					descripcion = componente.getDescripcionPerfil();
					cant = componente.getCantListaMateriales();
					centroTrabajo = componente.getCentroTrabajoPerfil();
					idPerfil = componente.getIdPerfil();
					cantFabricada = reportePiezaCtService.buscarCantidadesFabricadasPerfil(idItem, idPerfil, idCT);
				}
			}
		}
		
		ReporteDTO reporte = new ReporteDTO();
		reporte.setProyecto(op.getProyecto());
		reporte.setNumOp(op.getNumOp());
		reporte.setRef(descripcion);
		reporte.setCantSol(cant);
		reporte.setCentroTrabajo(centroTrabajo);
		reporte.setOperario(operario);
		reporte.setIdCentroTrabajo(idCT);
		reporte.setIdItemFab(idItemFab);
		reporte.setIdPerfil(idPerfil);
		reporte.setIdItem(idItem);
		reporte.setCantFab(cantFabricada);
		
		return reporte;
	}

}
