package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.almatec.controlpiso.integrapps.dtos.CentroOperacion;
import com.almatec.controlpiso.integrapps.dtos.Compania;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.dtos.PiezaOperarioDTO;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.entities.Operario;
import com.almatec.controlpiso.integrapps.entities.VistaItemsRutas;
import com.almatec.controlpiso.integrapps.entities.VistaTiemposOperarios;

public interface CentroTrabajoService {

	List<Compania> buscarCompanias();

	List<CentroTrabajo> buscarCentrosTrabajo(Integer integer);

	CentroTrabajo buscarCentroTrabajo(Integer id);

	List<CentroOperacion> buscarCentrosOperacion(Integer cia);

	void guardar(CentroTrabajo centroTrabajo);

	String agregarRetirarOperario(OperarioDTO operarioDTO);

	List<Operario> buscarOperariosCtDia(Integer idCT, Integer idConfigP);

	List<OpCentroTrabajoDTO> buscarOpCT(Integer idCT);

	List<VistaTiemposOperarios> obtenerTiemposOperarios(Integer idProceso);

	ResponseEntity<?> asignarActualizarPiezaOperario(Integer idCT, List<PiezaOperarioDTO> piezas);


}