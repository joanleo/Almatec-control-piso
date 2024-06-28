package com.almatec.controlpiso.comunicador.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.SolicitudMateriaPrima;

public interface MensajeServices {

	void enviarEmailSolicitudMateriaPrima(SolicitudMateriaPrima solicitud,
			List<DetalleSolicitudMateriaPrima> detalleSolicitud);

	void enviarEmailAprobacionPedidoVenta(String pedidoVenta);

	void enviarEmailCreacionOrdenProduccion(String ordenProduccion);

}
