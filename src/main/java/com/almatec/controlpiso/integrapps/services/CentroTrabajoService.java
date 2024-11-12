package com.almatec.controlpiso.integrapps.services;

import java.util.List;
import java.util.Set;

import com.almatec.controlpiso.integrapps.dtos.CentroOperacion;
import com.almatec.controlpiso.integrapps.dtos.Compania;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.dtos.PiezaOperarioDTO;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.dtos.TiemposOperariosDTO;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.entities.Operario;
import com.almatec.controlpiso.integrapps.interfaces.DatosOpItem;

public interface CentroTrabajoService {

	List<Compania> buscarCompanias();

	List<CentroTrabajo> buscarCentrosTrabajo(Integer integer);

	CentroTrabajo buscarCentroTrabajo(Integer id);

	List<CentroOperacion> buscarCentrosOperacion(Integer cia);

	void guardar(CentroTrabajo centroTrabajo);

	String agregarRetirarOperario(OperarioDTO operarioDTO);

	List<Operario> buscarOperariosCtDia(Integer idCT, Integer idConfigP);

	Set<OpCentroTrabajoDTO> buscarOpCT(Integer idCT);

	List<TiemposOperariosDTO> obtenerTiemposOperarios(Integer idProceso);

	void asignarActualizarPiezaOperario(Integer idCT, List<PiezaOperarioDTO> piezas);

	ReporteDTO buscarItemCt(Long idItemOp, Integer idCT, Integer idOperario);

	DatosOpItem obtenerDatosOpItem(Long idItem);

	Integer obtenerIdctErp(Integer idCentroTrabajo);

	CentroTrabajo buscarCentroTrabajoPorIdCtErp(Integer idCT);

	ReporteDTO buscarItemCtReporte(Long idItem, Integer idCT, Integer idOperario, Integer idItem2, String tipo);


}
