package com.almatec.controlpiso.comunicador.services.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.comunicador.entities.Mensaje;
import com.almatec.controlpiso.comunicador.repositories.MensajeRepository;
import com.almatec.controlpiso.comunicador.services.MensajeServices;
import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.SolicitudMateriaPrima;


@Service
public class MensajeServicesImpl implements MensajeServices{
		
	@Autowired
	private MensajeRepository mensajeRepo;

	@Override
	public void enviarEmailSolicitudMateriaPrima(SolicitudMateriaPrima solicitud,
			List<DetalleSolicitudMateriaPrima> detalleSolicitud) {
		crearEmail("Sol_Trans_Mp", 
				"Solicitud de Materia Prima MP-" + solicitud.getId(), 
				"Se requiere su aprobación para la siguiente solicitud de transferencia de materia prima.");
	}
	
	@Override
	public void enviarEmailAprobacionPedidoVenta(String pedidoVenta) {
		String asunto = "Aprobación de Pedido de Venta " + pedidoVenta;
		String mensaje = "Se ha aprobado el pedido de venta " + pedidoVenta;
		crearEmail("Aprobacion_PV", asunto, mensaje);
		
	}


    @Override
    public void enviarEmailCreacionOrdenProduccion(String orden) {
        String asunto = "Creación de Orden de Producción " + orden;
        String mensaje = "Se ha creado la siguiente orden de producción: " + orden;
        crearEmail("Creacion_OP", asunto, mensaje);
    }
    /*
     * 

    @Override
    public void enviarEmailGeneracionRemision(Remision remision) {
        String asunto = "Generación de Remisión RM-" + remision.getId();
        String mensaje = "Se ha generado la siguiente remisión: " + remision.getDescripcion();
        enviarEmail("Generacion_Remision", asunto, mensaje);
    }
	 */
	
	private void crearEmail(String tipoMensaje, String asunto, String contenidoMensaje) {
		Mensaje email = new Mensaje();
        String contenido = "<html><body style='background-color: #F4F4F5;padding: 20px;border-radius: .375rem;width:90%'>"
                + "<header style='max-height: 72px'; max-width: 196px></header>"
                + "<div><p>" + contenidoMensaje + "</p></div>"
                + "<footer style='background-color: #007B63;margin-top: 4rem;text-align: center;color: #FFFFFF;'>"
                + "<p>Este es un mensaje automático generado por Guayacan <a href='https://www.integrapps.com' style='text-decoration: none;color: #ffff;'> Powered by INTEGRAPPS</a></p>"
                + "</footer></body></html>";
        try {
            String correos = mensajeRepo.obtenerDestinatarios(tipoMensaje);
            String[] correosArr = correos.split(",");
            String destinatarios = Arrays.toString(correosArr).replace("[", "").replace("]", "");
            email.setDestinatarios(destinatarios);
            email.setAsunto(asunto);
            email.setCuerpo(contenido);
            LocalDateTime fecha = LocalDateTime.now();
            email.setFechaCreacion(fecha);
            mensajeRepo.save(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
