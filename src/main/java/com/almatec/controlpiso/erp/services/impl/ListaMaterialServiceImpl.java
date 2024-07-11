package com.almatec.controlpiso.erp.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.entities.ListaMaterial;
import com.almatec.controlpiso.erp.interfaces.DataConsumoInterface;
import com.almatec.controlpiso.erp.interfaces.DataTEP;
import com.almatec.controlpiso.erp.interfaces.DetalleTransferenciaInterface;
import com.almatec.controlpiso.erp.interfaces.RutaInterface;
import com.almatec.controlpiso.erp.repositories.ListaMaterialRepository;
import com.almatec.controlpiso.erp.services.ListaMaterialService;
import com.almatec.controlpiso.erp.webservices.interfaces.ConsultaItemOpCreado;

@Service
public class ListaMaterialServiceImpl implements ListaMaterialService {

	@Autowired
	private ListaMaterialRepository listaMaterialRepo;

	@Override
	public List<ListaMaterial> obtenerListaActual(Integer f820_id) {
		List<ListaMaterial> lista = listaMaterialRepo.obtenerListaActual(f820_id);
		return lista;
	}

	@Override
	public List<RutaInterface> obtenerRutasActual(String f808_id) {
		List<RutaInterface> rutas = listaMaterialRepo.obtenerRutas(f808_id);
		return rutas;
	}

	@Override
	public Integer obtenerUltimoIdRef() {
		return listaMaterialRepo.obtenerUltimoIdRefItem();
	}

	@Override
	public ConsultaItemOpCreado obtenerRowIdOpItemOp(String f120_id) {
		return listaMaterialRepo.obtenerRowIdOpItemOp(Integer.valueOf(f120_id));
	}

	@Override
	public DetalleTransferenciaInterface obtenerDetalleTransferencia(String idSolIntegrapps) {
		try {
			DetalleTransferenciaInterface detalle = listaMaterialRepo.obtenerDetalleTransferencia(idSolIntegrapps);
			return detalle;
		}catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}

	@Override
	public DataConsumoInterface obtenerDataParaConsumo(Integer idOp) {
		return listaMaterialRepo.obtenerDataParaConsumo(idOp);
	}

	@Override
	public String obtenerConsecutivoNotasTransferencia(String idOp) {
		return listaMaterialRepo.obtenerConsecutivoNotasTransferencia(idOp);
	}

	@Override
	public DataTEP obtenerDataTEP(String idRuta, String idCentroTrabajo) {
		try {
			return listaMaterialRepo.obtenerDataTEP(idRuta, idCentroTrabajo);			
		}catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}

	@Override
	public Integer obtenerItemOp(Integer numOp) {
		
		return listaMaterialRepo.obtenerItemOp(numOp);
	}
}
