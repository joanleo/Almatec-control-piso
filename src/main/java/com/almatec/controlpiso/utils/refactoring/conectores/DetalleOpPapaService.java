package com.almatec.controlpiso.utils.refactoring.conectores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionItemsVersion01;
import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;
import com.almatec.controlpiso.utils.UtilitiesApp;
import com.almatec.controlpiso.utils.refactoring.ParametrosAppService;

@Service
public class DetalleOpPapaService {
	
	@Autowired
	private UtilitiesApp util;
	
    public List<DoctoordenesdeproduccionItemsVersion01> crearDetalleOpPapa(List<VistaItemPedidoErp> items, ParametrosAppService parametroHelper) {
        List<DoctoordenesdeproduccionItemsVersion01> detalle = new ArrayList<>();

        int cont = 1;
        for(VistaItemPedidoErp item: items) {
            DoctoordenesdeproduccionItemsVersion01 movimiento = new DoctoordenesdeproduccionItemsVersion01();
            movimiento.setF_cia(parametroHelper.getCompania());
            movimiento.setF851_id_co(parametroHelper.getCentroOperacion());
            movimiento.setF851_nro_registro(cont);
            movimiento.setF851_id_item(Integer.valueOf(item.getReferencia()));
            movimiento.setF851_id_unidad_medida("KG");
            movimiento.setF851_porc_rendimiento(100.00);
            movimiento.setF851_cant_planeada_base(item.getCantidad());
            movimiento.setF851_fecha_inicio(util.obtenerFechaFormateada());
            movimiento.setF851_fecha_terminacion(util.obtenerFechaFormateada(item.getFechaEntrega()));
            movimiento.setF851_id_tipo_docto(parametroHelper.getTipoDocOpPapa());
            movimiento.setF851_id_bodega(parametroHelper.getBodegaEntregaItemFacturable());
            detalle.add(movimiento);
            cont++;
        }
        return detalle;
	}
}
    
