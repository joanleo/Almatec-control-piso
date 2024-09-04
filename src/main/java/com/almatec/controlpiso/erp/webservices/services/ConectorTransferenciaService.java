package com.almatec.controlpiso.erp.webservices.services;

import java.util.List;

import com.almatec.controlpiso.erp.webservices.generated.DoctoinventariosDocumentosVersion02;
import com.almatec.controlpiso.erp.webservices.generated.DoctoinventariosMovimientosVersion06;
import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;

public interface ConectorTransferenciaService {

	DoctoinventariosDocumentosVersion02 crearEncabezadoTransferencia(String nota);
	List<DoctoinventariosMovimientosVersion06> crearMovimientosTransferencia(List<DetalleSolicitudMateriaPrima> detalles);
}
