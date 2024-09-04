package com.almatec.controlpiso.comunicador.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.almatec.controlpiso.erp.interfaces.DetalleTransferenciaInterface;
import com.almatec.controlpiso.integrapps.dtos.ConsultaOpCreadaDTO;
import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.Remision;
import com.almatec.controlpiso.integrapps.entities.SolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.security.entities.Usuario;

public interface MensajeServices {

	void enviarEmailSolicitudMateriaPrima(SolicitudMateriaPrima solicitud,
			List<DetalleSolicitudMateriaPrima> detalleSolicitud, String nombreSolicita);

	void enviarEmailAprobacionPedidoVenta(Map<String, String> datos);

	void crearEmailAprobacionTransferencia(DetalleTransferenciaInterface transferencia, SolicitudMateriaPrima solicitud, Usuario usuarioAprueba);

	void enviarEmailGeneracionRemision(Remision remision);

	void enviarEmailCreacionOrdenProduccion(VistaOrdenPv ordenVacia);

}
