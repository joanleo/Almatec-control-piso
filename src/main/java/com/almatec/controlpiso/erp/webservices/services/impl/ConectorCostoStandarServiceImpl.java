package com.almatec.controlpiso.erp.webservices.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.dto.ListaMaterialesDTO;
import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.generated.ItemsParametrosVersion5;
import com.almatec.controlpiso.erp.webservices.generated.ManufacturaedicióndecostositemCostosV02;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;
import com.almatec.controlpiso.erp.webservices.services.ConectorCostoStandarService;
import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;

@Service
public class ConectorCostoStandarServiceImpl implements ConectorCostoStandarService {
	
	private final ConfigurationService configService;

	public ConectorCostoStandarServiceImpl(ConfigurationService configService) {
		super();
		this.configService = configService;
	}

	@Override
	public List<Conector> calcularCostoStandarItem(VistaItemPedidoErp item) {
		List<Conector> costoXml = new ArrayList<>();
		ItemsParametrosVersion5 parametros = new ItemsParametrosVersion5();
		parametros.setF_cia(configService.getCIA());
		parametros.setF_actualiza_reg(1);
		parametros.setF132_id_instalacion(configService.getINSTALACION());
		parametros.setF132_abc_rotacion_veces("A");
		parametros.setF132_abc_rotacion_costo("A");
		parametros.setF132_id_um_venta_suge(item.getUnidadMedidaInventario());
		parametros.setF132_mf_ind_mps(0);
		parametros.setF132_mf_porc_rendimiento(100.0);
		parametros.setF132_mf_ind_tipo_orden(1);
		parametros.setF132_mf_ind_politica_orden(1);
		parametros.setF132_mf_id_bodega_asigna(configService.getBODEGA_ENTREGA_ITEM_FACTURABLE());
		parametros.setF132_mf_ind_generar_orden_prod(1);
		parametros.setF132_mf_ind_item_critico(1);
		parametros.setF120_referencia(item.getReferencia());
		parametros.setF132_mf_ind_genera_ord_pln(0);

		costoXml.add(parametros);

		List<ManufacturaedicióndecostositemCostosV02> costo = creaConectoresEdicionCosto(item.getReferencia(), null);

		costoXml.addAll(costo);

		return costoXml;
	}

	@Override
	public List<ManufacturaedicióndecostositemCostosV02> creaConectoresEdicionCosto(String ref,
			List<ListaMaterialesDTO> listaMaterialesDTO) {
		List<ManufacturaedicióndecostositemCostosV02> costos = new ArrayList<>();
		Double costoStantar = 0.0001;
		if (listaMaterialesDTO == null) {
			costos.add(crearConectorCosto(ref, costoStantar, 901, null));
			return costos;
		} else {
			// costoStantar = calcularCostoStandar(listaMaterialesDTO);
			ManufacturaedicióndecostositemCostosV02 costo = crearConectorCosto(ref, costoStantar, 1,
					listaMaterialesDTO.get(0).getF820_cant_base());
			costos.add(costo);
			/*
			 * List<TarifaCostosSegmentoItem> segmentos =
			 * listaMaterialService.obtenerCostosSegmentos(ref);
			 * segmentos.forEach(segmento->{ ManufacturaedicióndecostositemCostosV02
			 * costoSegmento = crearConectorCosto(ref, segmento.getCostoTarifa(),
			 * segmento.getSegmento(), listaMaterialesDTO.get(0).getF820_cant_base());
			 * costos.add(costoSegmento); });
			 */
			return costos;
		}
	}

	@Override
	public ManufacturaedicióndecostositemCostosV02 crearConectorCosto(String ref, Double costoCalculado, int segmento,
			Double cantBase) {
		ManufacturaedicióndecostositemCostosV02 costo = new ManufacturaedicióndecostositemCostosV02();
		costo.setF_cia(configService.getCIA());
		costo.setF402_referencia_item(ref);
		costo.setF402_id_instalacion(configService.getINSTALACION());
		costo.setF402_id_grupo_costo(2);
		costo.setF402_id_segmento_costo(segmento);
		if (cantBase == null) {
			costo.setF402_costo_este_nivel_uni(costoCalculado);
		} else {
			// Double costoUnidad = costoCalculado / cantBase;
			// costo.setF402_costo_nivel_previo_uni(costoUnidad);
			costo.setF402_costo_nivel_previo_uni(costoCalculado);
		}
		return costo;
	}

}
