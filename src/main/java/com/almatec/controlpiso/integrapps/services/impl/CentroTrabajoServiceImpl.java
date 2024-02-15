package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.dtos.CentroOperacion;
import com.almatec.controlpiso.integrapps.dtos.Compania;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.dtos.PiezaOperarioDTO;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.entities.Operario;
import com.almatec.controlpiso.integrapps.entities.RegistroOperDia;
import com.almatec.controlpiso.integrapps.entities.VistaTiemposOperarios;
import com.almatec.controlpiso.integrapps.interfaces.CentroOperacionInterface;
import com.almatec.controlpiso.integrapps.interfaces.CompaniaErp;
import com.almatec.controlpiso.integrapps.repositories.CentroTrabajoRepository;
import com.almatec.controlpiso.integrapps.repositories.OperarioRepository;
import com.almatec.controlpiso.integrapps.services.CentroTrabajoService;
import com.almatec.controlpiso.integrapps.services.RegistroOperDiaService;
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

	@Override
	public List<Compania> buscarCompanias() {
		List<CompaniaErp> companiasErp = centroTrabajoRepo.buscarCompanias();
		List<Compania> companias = new ArrayList<>();
		for(CompaniaErp companiaErp: companiasErp) {
			Compania compania= new Compania(companiaErp);
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
		System.out.println("Buscando operarios centro trabajo proceso");
		List<RegistroOperDia> registros = registroOperdiaService.findByIdCentroTAndIdConfigProceso(idCT, idConfigP);
		System.out.println(registros);
		List<Operario> operarios = new ArrayList<>();
		for(RegistroOperDia registro:registros) {
			System.out.println("Registro operario dia " + registro);
			Operario operario = operarioRepo.findById(registro.getIdOperario())
					.orElseThrow(()-> new ResourceNotFoundException("No se encontro el operario con id: " + registro.getIdOperario()));
			operarios.add(operario);
			System.out.println(operario);
		}
		return operarios;
	}

	@Override
	public List<OpCentroTrabajoDTO> buscarOpCT(Integer idCT) {
		List<OpCentroTrabajoDTO> itemsRutas = vistaItemsRutasService.buscarOpCT(idCT);				
		return itemsRutas;
	}

	@Override
	public List<VistaTiemposOperarios> obtenerTiemposOperarios(Integer idProceso) {
		List<VistaTiemposOperarios> tiempos = vistaTiemposOperariosService.obtenerTiemposOperarios(idProceso);		
		return tiempos;
	}

	@Override
	public ResponseEntity<?> asignarActualizarPiezaOperario(Integer idCT, List<PiezaOperarioDTO> piezas) {
		return null;
	}

}
