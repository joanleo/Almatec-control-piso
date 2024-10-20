package com.almatec.controlpiso.erp.webservices.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.almacen.service.DetalleSolicitudMateriaPrimaService;
import com.almatec.controlpiso.almacen.service.SolicitudMateriaPrimaService;
import com.almatec.controlpiso.comunicador.services.MensajeServices;
import com.almatec.controlpiso.erp.interfaces.DetalleTransferenciaInterface;
import com.almatec.controlpiso.erp.services.ListaMaterialService;
import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.XmlService;
import com.almatec.controlpiso.erp.webservices.generated.DoctoinventariosDocumentosVersion02;
import com.almatec.controlpiso.erp.webservices.generated.DoctoinventariosMovimientosVersion06;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;
import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.SolicitudMateriaPrima;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Service
public class TransferenciaService {
	
	private final ListaMaterialService listaMaterialService;

	private final ConectorTransferenciaService conectorTransferenciaService;
	private final XmlService xmlService;
	private final SolicitudMateriaPrimaService solicitudMateriaPrimaService;
	private final DetalleSolicitudMateriaPrimaService detalleSolicitudMateriaPrimaService;
	private final MensajeServices mensajeService;
	private final UtilitiesApp util;
	private final ConfigurationService configService;
	
	static final String RESPUESTA_OK = "Operacion realizada exitosamente";

	public TransferenciaService(ListaMaterialService listaMaterialService,
			ConectorTransferenciaService conectorTransferenciaService, XmlService xmlService,
			SolicitudMateriaPrimaService solicitudMateriaPrimaService,
			DetalleSolicitudMateriaPrimaService detalleSolicitudMateriaPrimaService,
			MensajeServices mensajeService,
			UtilitiesApp util,
			ConfigurationService configService) {
		super();
		this.listaMaterialService = listaMaterialService;
		this.conectorTransferenciaService = conectorTransferenciaService;
		this.xmlService = xmlService;
		this.solicitudMateriaPrimaService = solicitudMateriaPrimaService;
		this.detalleSolicitudMateriaPrimaService = detalleSolicitudMateriaPrimaService;
		this.mensajeService = mensajeService;
		this.util = util;
		this.configService = configService;
	}
	
	public ResponseMessage crearTransferencia(Integer idSolIntegrapps, Usuario usuarioAprueba) throws IOException {
		List<Conector> transferenciaXml = new ArrayList<>();
		
		List<DetalleSolicitudMateriaPrima> detalleSol = detalleSolicitudMateriaPrimaService.obtenerDetallePorIdSol(idSolIntegrapps);
		String nota = "Creado por IntegrApps ** Solicitud transferencia: " + idSolIntegrapps;//listaMaterialService.obtenerConsecutivoNotasTransferencia(idSolIntegrapps.toString());
		/*if (nota != null) {
			if (nota.split("-").length > 1) {
				Integer consec = Integer.valueOf(nota.split("-")[1]);
				nota = idSolIntegrapps + "-" + (consec + 1);
			} else {
				nota = idSolIntegrapps + "-" + 1;
			}
		} else {
			nota = idSolIntegrapps.toString();
		}*/
		DoctoinventariosDocumentosVersion02 encabezado = conectorTransferenciaService.crearEncabezadoTransferencia(nota);
		transferenciaXml.add(encabezado);
		List<DoctoinventariosMovimientosVersion06> movs = conectorTransferenciaService.crearMovimientosTransferencia(detalleSol);
		transferenciaXml.addAll(movs);

		String responseCrear = xmlService.postImportarXML(transferenciaXml);
		if (!responseCrear.equals(RESPUESTA_OK)) {
			return new ResponseMessage(true, responseCrear);
		}
		DetalleTransferenciaInterface transferencia = listaMaterialService.obtenerDetalleTransferencia(nota);
		SolicitudMateriaPrima solicitudIntegrapps = solicitudMateriaPrimaService.obtenerSolicitudPorId(idSolIntegrapps);
		solicitudIntegrapps.setNumDocErp(transferencia.getf350_consec_docto());
		solicitudIntegrapps.setIdEstado(1);
		solicitudIntegrapps.setFechaDocEp(transferencia.getf350_fecha_ts_creacion());
		solicitudMateriaPrimaService.actualizaSolicitud(solicitudIntegrapps);
		
		mensajeService.crearEmailAprobacionTransferencia(transferencia, solicitudIntegrapps, usuarioAprueba);
		util.crearArchivoPlano(transferenciaXml, transferencia.getf350_id_tipo_docto() + "-" + transferencia.getf350_consec_docto(), configService.getCIA());
		return new ResponseMessage(false,
				responseCrear + "  se creo el documento de transferencia " + transferencia.getf350_id_tipo_docto() +"-" + transferencia.getf350_consec_docto());
	}

}
