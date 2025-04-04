package com.almatec.controlpiso.integrapps.services.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
import com.almatec.controlpiso.integrapps.dtos.TiemposOperariosDTO;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.entities.Operario;
import com.almatec.controlpiso.integrapps.entities.RegistroOperDia;
import com.almatec.controlpiso.integrapps.entities.RegistroPieza;
import com.almatec.controlpiso.integrapps.interfaces.CentroOperacionInterface;
import com.almatec.controlpiso.integrapps.interfaces.CompaniaErp;
import com.almatec.controlpiso.integrapps.interfaces.DatosOpItem;
import com.almatec.controlpiso.integrapps.repositories.CentroTrabajoRepository;
import com.almatec.controlpiso.integrapps.repositories.OperarioRepository;
import com.almatec.controlpiso.integrapps.services.CentroTrabajoService;
import com.almatec.controlpiso.integrapps.services.OperarioService;
import com.almatec.controlpiso.integrapps.services.RegistroOperDiaService;
import com.almatec.controlpiso.integrapps.services.RegistroPiezaService;
import com.almatec.controlpiso.integrapps.services.ReportePiezaCtService;
import com.almatec.controlpiso.integrapps.services.VistaOpItemsMaterialesRutaService;
import com.almatec.controlpiso.integrapps.services.VistaTiemposOperariosService;
 
@Transactional
@Service
public class CentroTrabajoServiceImpl implements CentroTrabajoService {
	
	@Autowired
	private CentroTrabajoRepository centroTrabajoRepo;
	
	@Autowired
	private RegistroOperDiaService registroOperdiaService;
	
	@Autowired
	private OperarioRepository operarioRepo;
	
	@Autowired
	private VistaTiemposOperariosService vistaTiemposOperariosService;
	
	@Autowired
	private RegistroPiezaService registroPiezaService;
	
	@Autowired
	private ReportePiezaCtService reportePiezaCtService;
	
	@Autowired
	private OperarioService operarioService;
	
	@Autowired
	private VistaOpItemsMaterialesRutaService opItemsMaterialesRutaService;
	
	
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
	@Cacheable
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
		CentroTrabajo ct = centroTrabajoRepo.save(centroTrabajo);
		ct.setaCT("CT"+ct.getId());
		String codigo = String.format("CTR%05d", ct.getId());
		ct.setCodigoBarraHum(codigo);
		centroTrabajoRepo.save(ct);
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
		return opItemsMaterialesRutaService.buscarOpCt(idCT);
	}

	@Override
	public List<TiemposOperariosDTO> obtenerTiemposOperarios(Integer idProceso) {
		List<TiemposOperariosDTO> tiempos = vistaTiemposOperariosService.obtenerTiemposOperarios(idProceso);		
		return tiempos;
	}

	@Override
	public void asignarActualizarPiezaOperario(Integer idCT, List<PiezaOperarioDTO> piezas) {
		try {
			if (idCT == null || piezas == null || piezas.isEmpty()) {
	            throw new IllegalArgumentException("Parámetros inválidos");
	        }
			for(PiezaOperarioDTO pieza: piezas) {
				procesarPieza(idCT, pieza);
			}
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error en la asignación de piezas: " + e.getMessage());
	    }
		
	}
	
	private void procesarPieza(Integer idCT, PiezaOperarioDTO pieza) {
		
		RegistroPieza registro = obtenerRegistroExistente(idCT, pieza);
		
		LocalDateTime fecha = LocalDateTime.now();
		if(registro != null) {
			actualizarRegistroExistente(registro, fecha);
			return;
		}
		
		crearnuevoRegistro(idCT, pieza, fecha);
		actualizarParadaOperario(idCT, pieza.getIdProceso(), pieza.getIdOperario());		
		
	}

	private void actualizarParadaOperario(Integer idCT, Integer idProceso, Integer idOperario) {
		//validar registro operariodia parada == 50 cambiar a cero
		RegistroOperDia registroOperario = registroOperdiaService.obtenerRegistroOperario(idCT, idProceso, idOperario);
		if(registroOperario.getIdParada() == 50) {
			Integer idParada = 0;
			registroOperdiaService.actualizaParada(registroOperario, idParada);					
		}
		
	}

	private void crearnuevoRegistro(Integer idCT, PiezaOperarioDTO pieza, LocalDateTime fecha) {
		RegistroPieza nuevoRegistro = new RegistroPieza();
		nuevoRegistro.setIdCT(idCT);
		nuevoRegistro.setIdConfig(pieza.getIdProceso());
		nuevoRegistro.setIdOperario(pieza.getIdOperario());
		nuevoRegistro.setIdItem(pieza.getIdItemOp());
		
		if(Boolean.TRUE.equals(pieza.getIsComponente())) {
			nuevoRegistro.setIdItemParte(pieza.getIdItem());				
		}else {
			nuevoRegistro.setIdItemFab(pieza.getIdItem());
		}
		
		nuevoRegistro.setFechaCreacion(fecha);
		nuevoRegistro.setIsActivo(pieza.getEstaActivo());
		registroPiezaService.actualizarRegistro(nuevoRegistro);
		
	}

	private void actualizarRegistroExistente(RegistroPieza registro, LocalDateTime fecha) {
		registro.setIsActivo(!registro.getIsActivo());
	    registro.setFechaEdicion(fecha);
	    registroPiezaService.actualizarRegistro(registro);		
	}
	
	private RegistroPieza obtenerRegistroExistente(Integer idCT, PiezaOperarioDTO pieza) {
		return Boolean.TRUE.equals(pieza.getIsComponente()) 
		        ? registroPiezaService.consultaRegistroPieza(
		            idCT, 
		            pieza.getIdProceso(), 
		            pieza.getIdOperario(), 
		            pieza.getIdItemOp(), 
		            pieza.getIdItem())
		        : registroPiezaService.consultaRegistroPieza(
		            idCT, 
		            pieza.getIdProceso(), 
		            pieza.getIdOperario(), 
		            pieza.getIdItemOp());
	}

	public ReporteDTO buscarItemCt(Long idItemOp, Integer idCT, Integer idOperario) {
		Operario operario = operarioService.buscarOperarioPorId(idOperario);
		Set<OpCentroTrabajoDTO> ops = opItemsMaterialesRutaService.buscarItemCt(idItemOp, idCT);
		if (ops != null && !ops.isEmpty()) {
		    OpCentroTrabajoDTO op = ops.iterator().next();
		    ItemOpCtDTO item = op.getItems().get(0);
		    String descripcion = null;
		    Integer cant = 0;
		    String centroTrabajo = null;
		    Integer idItemFab = 0;
		    Integer idParte = 0;
		    Integer cantFabricada = 0;
		    if(Objects.equals(item.getItem_centro_t_id(), idCT)) {
		    	descripcion = item.getItem_desc();
		    	cant = item.getCant_req();
		    	centroTrabajo = item.getItem_centro_t_nombre();
		    	idItemFab = item.getItem_id();
		    	Integer cantFabDb = reportePiezaCtService.buscarCantidadesFabricadasConjunto(idItemOp, idItemFab, idCT);
		    	cantFabricada = cantFabDb != null ? cantFabDb : cantFabricada;
		    }
		    if(descripcion == null) {
		    	for(ComponenteDTO componente: item.getComponentes()) {
		    		if(Objects.equals(componente.getMaterial_centro_t_id(), idCT)) {
		    			descripcion = componente.getMaterial_desc();
		    			cant = componente.getMaterial_cant();
		    			centroTrabajo = componente.getMaterial_centro_t_nombre();
		    			idParte = componente.getMaterial_id();
		    			Integer cantFabDb = reportePiezaCtService.buscarCantidadesFabricadasPerfil(idItemOp, idParte, idCT);
		    			cantFabricada = cantFabDb != null ? cantFabDb : cantFabricada;
		    		}
		    	}
		    }
		    
		    ReporteDTO reporte = new ReporteDTO();
		    reporte.setProyecto(op.getProyecto());
		    reporte.setNumOp(Integer.valueOf(op.getOp().split("-")[1]));
		    reporte.setRef(descripcion);
		    reporte.setCantSol(cant);
		    reporte.setCentroTrabajo(centroTrabajo);
		    reporte.setOperario(operario);
		    reporte.setIdCentroTrabajo(idCT);
		    reporte.setIdItemFab(idItemFab);
		    reporte.setIdParte(idParte);
		    reporte.setIdItem(idItemOp);
		    reporte.setCantFab(cantFabricada);
		    reporte.setColor(item.getItem_color());
		    		    
		    return reporte;
		}
		
		return null;
	}

	@Override
	public ReporteDTO buscarItemCtReporte(Long idItemOp, Integer idCT, Integer idOperario, Integer idItem, String tipo) {
		//StopWatch stopWatch = new StopWatch();
        //stopWatch.start();
		Operario operario = operarioService.buscarOperarioPorId(idOperario);
		//stopWatch.stop();
		//logger.info("Tiempo de ejecución de consulta a los datos del operario: {} ms", stopWatch.getTotalTimeMillis());
		//stopWatch.start();
		Set<OpCentroTrabajoDTO> ops = opItemsMaterialesRutaService.buscarItemParteCt(idItemOp, idCT, idItem, tipo);
		if (ops != null && !ops.isEmpty()) {
		    OpCentroTrabajoDTO op = ops.iterator().next();
		    ItemOpCtDTO item = op.getItems().get(0);
		    String descripcion = null;
		    Integer cant = 0;
		    String centroTrabajo = null;
		    Integer idItemFab = 0;
		    Integer idParte = 0;
		    Integer cantFabricada = 0;
		    BigDecimal peso = BigDecimal.ZERO;
		    BigDecimal pesoPintura = item.getPesoPintura();
		    if(Objects.equals(item.getItem_centro_t_id(), idCT)) {
		    	descripcion = item.getItem_desc(); 
		    	cant = item.getCant_req();
		    	centroTrabajo = item.getItem_centro_t_nombre();
		    	idItemFab = item.getItem_id();
		    	Integer cantFabDb = reportePiezaCtService.buscarCantidadesFabricadasConjunto(idItemOp, idItemFab, idCT);
		    	cantFabricada = cantFabDb != null ? cantFabDb : cantFabricada;
		    	peso = item.getItem_peso();
		    }
		    if(descripcion == null) {
		    	for(ComponenteDTO componente: item.getComponentes()) {
		    		if(Objects.equals(componente.getMaterial_centro_t_id(), idCT)) {
		    			descripcion = componente.getMaterial_desc();
		    			cant = componente.getMaterial_cant();
		    			centroTrabajo = componente.getMaterial_centro_t_nombre();
		    			idParte = componente.getMaterial_id();
		    			Integer cantFabDb = reportePiezaCtService.buscarCantidadesFabricadasPerfil(idItemOp, idParte, idCT);
		    			cantFabricada = cantFabDb != null ? cantFabDb : cantFabricada;
		    			peso = componente.getMaterial_peso();
		    		}
		    	}
		    }
		    
		    ReporteDTO reporte = new ReporteDTO();
		    reporte.setProyecto(op.getProyecto());
		    reporte.setNumOp(Integer.valueOf(op.getOp().split("-")[1]));
		    reporte.setRef(descripcion);
		    reporte.setCantSol(cant);
		    reporte.setCentroTrabajo(centroTrabajo);
		    reporte.setOperario(operario);
		    reporte.setIdCentroTrabajo(idCT);
		    reporte.setIdItemFab(idItemFab);
		    reporte.setIdParte(idParte);
		    reporte.setIdItem(idItemOp);
		    reporte.setCantFab(cantFabricada);
		    reporte.setColor(item.getItem_color());
		    reporte.setPeso(peso);
		    reporte.setPesoPintura(peso);
		    reporte.setPesoPintura(pesoPintura);
		    //stopWatch.stop();
		    //logger.info("Tiempo de ejecución de tratamiento de los datos del reporte: {} ms", stopWatch.getTotalTimeMillis());
		    return reporte;
		}
		return null;
	}

	@Override
	public DatosOpItem obtenerDatosOpItem(Long idItem) {
		return centroTrabajoRepo.obtenerDatosOpItem(idItem);
		
	}

	@Override
	public Integer obtenerIdctErp(Integer idCentroTrabajo) {
		CentroTrabajo centroT = centroTrabajoRepo.findById(idCentroTrabajo)
				.orElseThrow(()-> new ResourceNotFoundException("No se encontro centro de trabajo con id: " + idCentroTrabajo));
		
		return centroT.getIdCentroTrabajoErp();
	}

	@Override
	public CentroTrabajo buscarCentroTrabajoPorIdCtErp(Integer idCT) {
		//return centroTrabajoRepo.findByIdCentroTrabajoErp(idCT);
		return centroTrabajoRepo.findById(idCT)
				.orElseThrow();
	}


}
