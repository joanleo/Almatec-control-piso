package com.almatec.controlpiso.erp.webservices.services.impl;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionDocumentosVersion01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionItemsVersion01;
import com.almatec.controlpiso.erp.webservices.generated.ItemsVersion05;
import com.almatec.controlpiso.erp.webservices.services.ConectorOrdenProduccionHijoService;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Service
public class ConectorOrdenProduccionHijoServiceImpl implements ConectorOrdenProduccionHijoService {

	private final ConfigurationService configService;
	private final UtilitiesApp util;
	
	public ConectorOrdenProduccionHijoServiceImpl(ConfigurationService configService, UtilitiesApp util) {
		super();
		this.configService = configService;
		this.util = util;
	}

	@Override
	public DoctoordenesdeproduccionDocumentosVersion01 crearEncabezadoOPEntrega(VistaOrdenPv ordenPadreIF) {
		DoctoordenesdeproduccionDocumentosVersion01 encabezado = new DoctoordenesdeproduccionDocumentosVersion01();
		encabezado.setF_cia(configService.getCIA());
		encabezado.setF_consec_auto_reg(1);
		encabezado.setF850_id_co(configService.getC_O());
		encabezado.setF850_id_tipo_docto(configService.getTIPO_DOC_OP_HIJO());
		encabezado.setF850_fecha(util.obtenerFechaFormateada());
		encabezado.setF850_ind_estado(0);
		encabezado.setF850_id_clase_docto(701);
		encabezado.setF850_tercero_planificador(configService.getPLANIFICADOR());
		encabezado.setF850_id_instalacion("001");
		encabezado.setF850_clase_op(configService.getCLASE_OP_HIJO());
		encabezado.setF850_id_co_pv(configService.getC_O());
		encabezado.setF850_id_tipo_docto_op_padre(ordenPadreIF.getTipoOp());
		encabezado.setF850_consec_docto_op_padre(ordenPadreIF.getNumOp());
		String referencia1 = ordenPadreIF.getCliente();
		if (referencia1.length() > 30) {
			referencia1 = referencia1.substring(0, 30);
		}
		encabezado.setF850_referencia_1(referencia1);
		String referencia2 = ordenPadreIF.getCentroOperaciones() + "-" + ordenPadreIF.getZona();
		if (referencia2.length() > 30) {
			referencia2 = referencia2.substring(0, 30);
		}
		encabezado.setF850_referencia_2(referencia2);
		return encabezado;
	}

	@Override
	public DoctoordenesdeproduccionItemsVersion01 crearDetalleOpEntrega(ItemsVersion05 item,
			VistaOrdenPv ordenIntegrapps, Double cantBase) {
		DoctoordenesdeproduccionItemsVersion01 movimiento = new DoctoordenesdeproduccionItemsVersion01();
		movimiento.setF_cia(configService.getCIA());
		movimiento.setF851_id_co(configService.getC_O());
		movimiento.setF851_nro_registro(0);
		movimiento.setF851_id_item(Integer.valueOf(item.getF120_id()));
		movimiento.setF851_id_unidad_medida("KG");
		movimiento.setF851_porc_rendimiento(100.00);
		movimiento.setF851_cant_planeada_base(cantBase);// ordenIntegrapps.getCant()
		movimiento.setF851_fecha_inicio(util.obtenerFechaFormateada());
		movimiento.setF851_fecha_terminacion(util.obtenerFechaFormateada(ordenIntegrapps.getFechaEntrega()));
		movimiento.setF851_id_tipo_docto(ordenIntegrapps.getTipoOp());
		movimiento.setF851_id_bodega(ordenIntegrapps.getBodega());
		movimiento.setF851_id_metodo_lista(configService.getMETODO());
		movimiento.setF851_id_metodo_ruta(configService.getMETODO());

		return movimiento;
	}

}
