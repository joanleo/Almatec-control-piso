package com.almatec.controlpiso.erp.webservices.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionDocumentosVersion01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionItemsVersion01;
import com.almatec.controlpiso.erp.webservices.services.ConectorOrdenProduccionPapaService;
import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;
import com.almatec.controlpiso.integrapps.interfaces.OrdenPvEstadoData;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Service
public class ConectorOrdenProduccionPapaServiceImpl implements ConectorOrdenProduccionPapaService {
	
	private final ConfigurationService configService;
	private final UtilitiesApp util;

	public ConectorOrdenProduccionPapaServiceImpl(ConfigurationService configService, UtilitiesApp util) {
		super();
		this.configService = configService;
		this.util = util;
	}

	@Override
	public DoctoordenesdeproduccionDocumentosVersion01 crearEncabezadoOpPapa(Integer noPedido,
			OrdenPvEstadoData ordenPv) {
		DoctoordenesdeproduccionDocumentosVersion01 encabezado = new DoctoordenesdeproduccionDocumentosVersion01();
		encabezado.setF_cia(configService.getCIA());
		encabezado.setF_consec_auto_reg(1);
		encabezado.setF850_id_co(configService.getC_O());
		encabezado.setF850_id_tipo_docto(configService.getTIPO_DOC_OP_PAPA());
		encabezado.setF850_fecha(util.obtenerFechaFormateada());
		encabezado.setF850_ind_estado(1);
		encabezado.setF850_id_clase_docto(701);
		encabezado.setF850_tercero_planificador(configService.getPLANIFICADOR());
		encabezado.setF850_id_instalacion(configService.getINSTALACION());
		encabezado.setF850_clase_op(configService.getCLASE_OP_PAPA());
		encabezado.setF850_id_co_pv(configService.getC_O());
		encabezado.setF850_id_tipo_docto_pv("PV");
		encabezado.setF850_consec_docto_pv(noPedido);
		String referencia1 = ordenPv.getCliente();
		if (referencia1.length() > 30) {
			referencia1 = referencia1.substring(0, 30);
		}
		encabezado.setF850_referencia_1(referencia1);
		
		String referencia2 = ordenPv.getCoDescripcion();
		if (referencia2.length() > 30) {
			referencia2 = referencia2.substring(0, 30);
		}
		encabezado.setF850_referencia_2(referencia2);
		return encabezado;
	}

	@Override
	public List<DoctoordenesdeproduccionItemsVersion01> crearDetalleOpPapa(List<VistaItemPedidoErp> items) {
		List<DoctoordenesdeproduccionItemsVersion01> detalle = new ArrayList<>();

		int cont = 1;
		for (VistaItemPedidoErp item : items) {
			DoctoordenesdeproduccionItemsVersion01 movimiento = new DoctoordenesdeproduccionItemsVersion01();
			movimiento.setF_cia(configService.getCIA());
			movimiento.setF851_id_co(configService.getC_O());
			movimiento.setF851_nro_registro(cont);
			movimiento.setF851_id_item(item.getCodigo());
			movimiento.setF851_id_unidad_medida(item.getUm());
			movimiento.setF851_porc_rendimiento(100.00);
			movimiento.setF851_cant_planeada_base(item.getCantidad());
			movimiento.setF851_fecha_inicio(util.obtenerFechaFormateada());
			movimiento.setF851_fecha_terminacion(util.obtenerFechaFormateada(item.getFechaEntrega()));
			movimiento.setF851_id_tipo_docto(configService.getTIPO_DOC_OP_PAPA());
			movimiento.setF851_id_bodega(configService.getBODEGA_ENTREGA_ITEM_FACTURABLE());// 00190
			detalle.add(movimiento);
			cont++;
		}
		return detalle;
	}

}
