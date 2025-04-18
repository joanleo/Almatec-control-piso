package com.almatec.controlpiso.erp.services;

import java.util.List;

import com.almatec.controlpiso.erp.entities.ListaMaterial;
import com.almatec.controlpiso.erp.interfaces.DataConsumoInterface;
import com.almatec.controlpiso.erp.interfaces.DataCostoStandarInterface;
import com.almatec.controlpiso.erp.interfaces.DataTEP;
import com.almatec.controlpiso.erp.interfaces.DetalleTransferenciaInterface;
import com.almatec.controlpiso.erp.interfaces.RutaInterface;
import com.almatec.controlpiso.erp.interfaces.TarifaCostosSegmentoItem;
import com.almatec.controlpiso.erp.webservices.interfaces.ConsultaItemOpCreado;
import com.almatec.controlpiso.erp.webservices.interfaces.TipoServicioYGrupoImpositivo;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;

public interface ListaMaterialService {
	
	List<ListaMaterial> obtenerListaActual(Integer f820_id);

	List<RutaInterface> obtenerRutasActual(String f808_id);

	Integer obtenerUltimoIdRef();

	ConsultaItemOpCreado obtenerRowIdOpItemOp(String f120_id);

	DetalleTransferenciaInterface obtenerDetalleTransferencia(String id);

	DataConsumoInterface obtenerDataParaConsumo(Integer idOp);

	String obtenerConsecutivoNotasTransferencia(String string);

	DataTEP obtenerDataTEP(String idRuta, String idCentroTrabajo);

	Integer obtenerItemOp(VistaOrdenPv ordenIFPapa);

	DataCostoStandarInterface obtenerCostoStandar(Integer f820_id_hijo);

	List<TarifaCostosSegmentoItem> obtenerCostosSegmentos(String ref);

	TipoServicioYGrupoImpositivo obtenerTipoServicioYGrupoImpositivoItem(Integer itemIFId, String tipoOp, Integer numOp);

}
