package com.almatec.controlpiso.erp.webservices.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.generated.DoctoinventariosDocumentosVersion02;
import com.almatec.controlpiso.erp.webservices.generated.DoctoinventariosMovimientosVersion06;
import com.almatec.controlpiso.erp.webservices.services.ConectorTransferenciaService;
import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Service
public class ConectorTransferenciaServiceImpl implements ConectorTransferenciaService {

	private final ConfigurationService configService;
	private final UtilitiesApp util;
	
	public ConectorTransferenciaServiceImpl(ConfigurationService configService, UtilitiesApp util) {
		super();
		this.configService = configService;
		this.util = util;
	}

	@Override
	public DoctoinventariosDocumentosVersion02 crearEncabezadoTransferencia(String nota) {
		DoctoinventariosDocumentosVersion02 encabezado = new DoctoinventariosDocumentosVersion02();
		encabezado.setF_cia(configService.getCIA());
		encabezado.setF_consec_auto_reg(1);
		encabezado.setF350_id_co(configService.getC_O());
		encabezado.setF350_id_tipo_docto(configService.getTIPO_DOC_TRANSFERENCIA());
		encabezado.setF350_consec_docto(0);
		encabezado.setF350_fecha(util.obtenerFechaFormateada());
		encabezado.setF350_id_clase_docto(67);
		encabezado.setF350_ind_estado(1);
		encabezado.setF350_ind_impresión(0);
		encabezado.setF450_id_concepto(607);
		encabezado.setF450_id_bodega_salida(configService.getBODEGA_MATERIAL());
		encabezado.setF450_id_bodega_entrada(configService.getBODEGA_ENTREGA_TRANSFERENCIA());
		encabezado.setF350_notas(nota);
		return encabezado;
	}

	@Override
	public List<DoctoinventariosMovimientosVersion06> crearMovimientosTransferencia(
			List<DetalleSolicitudMateriaPrima> detalles) {
		List<DoctoinventariosMovimientosVersion06> movs = new ArrayList<>();
		int count = 1;
		for (DetalleSolicitudMateriaPrima item : detalles) {
			DoctoinventariosMovimientosVersion06 mov = new DoctoinventariosMovimientosVersion06();
			mov.setF_cia(configService.getCIA());
			mov.setF470_id_co(configService.getC_O());
			mov.setF470_id_tipo_docto(configService.getTIPO_DOC_TRANSFERENCIA());
			mov.setF470_consec_docto(0);
			mov.setF_numero_reg(count);
			mov.setF470_id_bodega(configService.getBODEGA_MATERIAL());
			mov.setF470_id_lote(item.getLoteErp());
			mov.setF470_id_concepto(607);
			mov.setF470_id_motivo(configService.getMOTIVO_TRANSFERENCIA());
			mov.setF470_id_co_movto(configService.getC_O());
			mov.setF470_id_unidad_medida(item.getUndErp());
			mov.setF470_cant_base(item.getCantSol());
			mov.setF470_id_lote_ent(item.getLoteErp());
			mov.setF470_id_item(item.getCodigoErp());
			mov.setF470_id_un_movto("001");
			mov.setF470_rowid_movto(0);

			movs.add(mov);
			count++;
		}
		return movs;
	}

}
