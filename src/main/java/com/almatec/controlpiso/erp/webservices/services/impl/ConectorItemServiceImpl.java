package com.almatec.controlpiso.erp.webservices.services.impl;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.services.ListaMaterialService;
import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.generated.ItemsVersion05;
import com.almatec.controlpiso.erp.webservices.interfaces.TipoServicioYGrupoImpositivo;
import com.almatec.controlpiso.erp.webservices.services.ConectorItemService;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Service
public class ConectorItemServiceImpl implements ConectorItemService {
	
	private final ConfigurationService configService;
	private final UtilitiesApp util;
	private final ListaMaterialService listaMaterialService;

	public ConectorItemServiceImpl(ConfigurationService configService, UtilitiesApp util,
			ListaMaterialService listaMaterialService) {
		super();
		this.configService = configService;
		this.util = util;
		this.listaMaterialService = listaMaterialService;
	}

	@Override
	public ItemsVersion05 crearConectorItemEntrega(VistaOrdenPv ordenOPHijo, VistaOrdenPv ordenIFPapa) {
		ItemsVersion05 item = new ItemsVersion05();
		item.setF_cia(configService.getCIA());
		item.setF_actualiza_reg(0);
		Integer idLast = listaMaterialService.obtenerUltimoIdRef();
		Integer id = idLast + 1;
		item.setF120_id(id);
		String stringId = String.valueOf(id);
		item.setF120_referencia("0" + stringId);
		if (!ordenOPHijo.getZona().isEmpty() && ordenOPHijo.getZona().length() > 0) {
			item.setF120_descripcion(ordenOPHijo.getObservaciones());
			item.setF120_descripcion_corta(ordenOPHijo.getObservaciones());
		}
		Integer itemIFId =  listaMaterialService.obtenerItemOp(ordenIFPapa);
		TipoServicioYGrupoImpositivo dataTipoYGrupo = listaMaterialService.obtenerTipoServicioYGrupoImpositivoItem(itemIFId, ordenIFPapa.getTipoOp(), ordenIFPapa.getNumOp());
		item.setF120_id_grupo_impositivo(dataTipoYGrupo.getGrupoImpositivo());
		item.setF120_id_tipo_inv_serv(dataTipoYGrupo.getTipoServicio());
		item.setF120_ind_tipo_item(1);
		item.setF120_ind_compra(0);
		item.setF120_ind_venta(0);
		item.setF120_ind_manufactura(1);
		item.setF120_ind_lote(0);
		item.setF120_ind_lote_asignacion(0);
		item.setF120_id_unidad_inventario("KG");
		item.setF120_factor_peso_inventario(1.0);
		item.setF120_id_unidad_orden("KG");
		item.setF120_ind_lista_precios_ext(0);
		item.setF121_ind_estado(1);
		item.setF121_fecha_creacion(util.obtenerFechaFormateada());
		item.setF120_ind_serial(0);
		item.setF120_ind_paquete(0);
		item.setF120_ind_exento(0);
		item.setF120_factor_orden(1.0);

		return item;
	}

}
