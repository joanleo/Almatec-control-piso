package com.almatec.controlpiso.erp.webservices.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionDocumentosVersion01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionItemsVersion01;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;
import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;
import com.almatec.controlpiso.integrapps.interfaces.OrdenPvEstadoData;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;

@Service
public class OrdenProduccionPapaService {
	
	private final ConectorCostoStandarService conectorCostoStandarService;
	private final OrdenPvService ordenPvService;
	private final ConectorOrdenProduccionPapaService conectorOrdenProduccionPapaService;

	public OrdenProduccionPapaService(ConectorCostoStandarService conectorCostoStandarService,
			OrdenPvService ordenPvService, ConectorOrdenProduccionPapaService conectorOrdenProduccionPapaService) {
		super();
		this.conectorCostoStandarService = conectorCostoStandarService;
		this.ordenPvService = ordenPvService;
		this.conectorOrdenProduccionPapaService = conectorOrdenProduccionPapaService;
	}

	public List<Conector> crearConectoresOpPapa(List<VistaItemPedidoErp> items) {
		List<Conector> costosParametros = conectorCostoStandarService.calcularCostoStandarItem(items.get(0));
		
		List<Conector> ordenProduccion = new ArrayList<>();

		ordenProduccion.addAll(costosParametros);
		OrdenPvEstadoData ordenPv = ordenPvService.obtenerOrdenPorNumPv(items.get(0).getNoPedido());
		DoctoordenesdeproduccionDocumentosVersion01 encabezadoOp = conectorOrdenProduccionPapaService.crearEncabezadoOpPapa(items.get(0).getNoPedido(),
				ordenPv);
		ordenProduccion.add(encabezadoOp);
		List<DoctoordenesdeproduccionItemsVersion01> detallesOp = conectorOrdenProduccionPapaService.crearDetalleOpPapa(items);
		ordenProduccion.addAll(detallesOp);

		return ordenProduccion;	
	}
}
