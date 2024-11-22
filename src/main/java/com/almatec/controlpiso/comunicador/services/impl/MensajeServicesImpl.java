package com.almatec.controlpiso.comunicador.services.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.almatec.controlpiso.comunicador.entities.Mensaje;
import com.almatec.controlpiso.comunicador.repositories.MensajeRepository;
import com.almatec.controlpiso.comunicador.services.MensajeServices;
import com.almatec.controlpiso.erp.interfaces.DetalleTransferenciaInterface;
import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.Remision;
import com.almatec.controlpiso.integrapps.entities.SolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.services.UsuarioService;


@Service
public class MensajeServicesImpl implements MensajeServices{
		
	private final MensajeRepository mensajeRepo;
	private final UsuarioService usuarioService;
    private final SpringTemplateEngine templateEngine;
    private final OrdenPvService ordenPvService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
	

    public MensajeServicesImpl(MensajeRepository mensajeRepo, UsuarioService usuarioService,
			SpringTemplateEngine templateEngine, OrdenPvService ordenPvService) {
		super();
		this.mensajeRepo = mensajeRepo;
		this.usuarioService = usuarioService;
		this.templateEngine = templateEngine;
		this.ordenPvService = ordenPvService;
	}

	@Override
    public void enviarEmailGeneracionRemision(Remision remision) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	String fechaStr = dateFormat.format(remision.getFechaCreacion());
    	
    	VistaOrdenPv orden = ordenPvService.obtenerOrdenPorId(remision.getIdOpIa());
    	
    	Context context = new Context();
        context.setVariable("idRemision", "RM-" + remision.getIdRemision());
        context.setVariable("op", orden.getTipoOp() + "-" + orden.getNumOp());
        context.setVariable("cliente", orden.getCliente());
        context.setVariable("proyecto", orden.getCentroOperaciones());
        context.setVariable("fecha", fechaStr);
        context.setVariable("usuarioCreaRemision", remision.getUsuarioCreaRemision().getNombres());
        context.setVariable("detalles", remision.getDetalles());
        context.setVariable("observaciones", remision.getObservaciones());

        String content = templateEngine.process("remision-generada ", context);
        
        crearEmail("CREACION_RM", 
        			"Remision Generada " 
    				+"RM-" + remision.getIdRemision() 
    				+ "_" + orden.getTipoOp() + "-" + orden.getNumOp() 
    				+ "_" + orden.getCentroOperaciones()
    				+ "_" + orden.getCliente() , 
    				content);
    }

	@Override
	public void enviarEmailSolicitudMateriaPrima(SolicitudMateriaPrima solicitud,
			List<DetalleSolicitudMateriaPrima> detalleSolicitud, String nombreSolicita) {
		
		VistaOrdenPv orden = ordenPvService.obtenerOrdenPorId(solicitud.getIdOp());
		Context context = new Context();
		context.setVariable("solicitud", "ST-" + solicitud.getId());
    	context.setVariable("orden", solicitud.getTipoOp() + "-" + solicitud.getNumOp());
    	context.setVariable("proyecto", orden.getCentroOperaciones());
    	context.setVariable("solicitante", nombreSolicita);
    	
    	String contenido = templateEngine.process("solicitud-transferencia", context);
		
		crearEmail("SOLICITUD_TR", 	
				"Solicitud de Transferencia "
				+ "ST-" + solicitud.getId()
				+ "_" + orden.getTipoOp() + "-" + orden.getNumOp() 
				+ "_" + orden.getCentroOperaciones() 
				+ "_" + orden.getCliente(), 
				contenido);
	}
	
	@Override
	public void crearEmailAprobacionTransferencia(DetalleTransferenciaInterface transferencia, SolicitudMateriaPrima solicitud,
			Usuario usuarioAprueba) {
		VistaOrdenPv orden = ordenPvService.obtenerOrdenPorId(solicitud.getIdOp());
		Usuario usuarioSolicita = usuarioService.buscarUsuarioPorId(solicitud.getIdUsuarioSol());
		Context context = new Context();
		context.setVariable("transferencia", transferencia.getf350_id_tipo_docto() +"-" + transferencia.getf350_consec_docto());
    	context.setVariable("orden", solicitud.getTipoOp() + "-" + solicitud.getNumOp());
    	context.setVariable("proyecto", orden.getCentroOperaciones());
    	context.setVariable("solicitud", "ST-" + solicitud.getId());
    	context.setVariable("solicitante", usuarioSolicita.getNombres());
    	context.setVariable("aprobador", usuarioAprueba.getNombres());
    	
    	String contenido = templateEngine.process("aprobacion-transferencia", context);
		
		crearEmail("APROBACION_TR", 
				"Aprobacion de Solicitud de Transferencia "
				+ "ST-" + solicitud.getId()
				+ "_" + orden.getTipoOp() + "-" + orden.getNumOp() 
				+ "_" + orden.getCentroOperaciones() 
				+ "_" + orden.getCliente(), 
				contenido);
		
	}
	

	@Override
    public void enviarEmailCreacionOrdenProduccion(VistaOrdenPv orden) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaEntregaStr = dateFormat.format(orden.getFechaEntrega());
        
    	Context context = new Context();
    	context.setVariable("orden", orden.getTipoOp() + '-' + orden.getNumOp());
    	context.setVariable("cliente", orden.getCliente());
    	context.setVariable("proyecto", orden.getCentroOperaciones());
    	context.setVariable("fechaEntrega", fechaEntregaStr);
    	
    	String fechaElaboracionStr = dateFormat.format(new Date());
        context.setVariable("fechaElaboracion", fechaElaboracionStr);
        
        String contenido = templateEngine.process("orden-produccion-creada", context);        
        
        crearEmail("CREACION_OP", 
        		"Orden de Producción Generada " 
				+ "_" + orden.getTipoOp() + "-" + orden.getNumOp() 
				+ "_" + orden.getCentroOperaciones() 
				+ "_" + orden.getCliente(),
        		contenido);
    }
    
    @Override
    public void enviarEmailAprobacionPedidoVenta(Map<String, String> datos) {
        Context context = new Context();
        for (Map.Entry<String, String> entry : datos.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        String contenido = templateEngine.process("pedido-aprobado-orden-produccion", context);

        crearEmail("APROBACION_PV", 
        		"Pedido Aprobado y Orden de Producción Generada "
				+ "PV-" + datos.get("pedido") 
				+ "_" + datos.get("ordenProduccion")
				+ "_" + datos.get("proyecto")
				+ "_" + datos.get("cliente"), contenido);
    }
	
	private void crearEmail(String tipoMensaje, String asunto, String contenidoMensaje) {
		Mensaje email = new Mensaje();

        try {
        	String correos = mensajeRepo.obtenerDestinatarios(tipoMensaje);
            List<String> destinatariosLimpios = limpiarYValidarCorreos(correos);
            String destinatarios = String.join(", ", destinatariosLimpios);
            
            email.setDestinatarios(destinatarios);
            email.setAsunto(asunto);
            email.setCuerpo(contenidoMensaje);
            LocalDateTime fecha = LocalDateTime.now();
            email.setFechaCreacion(fecha);
            mensajeRepo.save(email);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error al tratar de crear el email ", e);
        }
    }
	
	private List<String> limpiarYValidarCorreos(String correosString) {
        if (correosString == null || correosString.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        return Arrays.stream(correosString.split(","))
            .map(String::trim)
            .filter(this::esCorreoValido)
            .distinct()
            .collect(Collectors.toList());
    }

    private boolean esCorreoValido(String correo) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(correo).matches();
    }

}
