package com.almatec.controlpiso.erp.webservices.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.interfaces.DataConsumoInterface;
import com.almatec.controlpiso.erp.interfaces.DataTEP;
import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.generated.DoctoTEPDocumentosVersion01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoTEPMovimientosVersion01;
import com.almatec.controlpiso.erp.webservices.services.ConectorTepService;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.interfaces.ItemInterface;
import com.almatec.controlpiso.integrapps.repositories.CentroTrabajoRepository;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Service
public class ConectorTepServiceImpl implements ConectorTepService {
	
	private final ConfigurationService configService;
	private final UtilitiesApp util;
	private final ItemOpService itemOpService;
	private final CentroTrabajoRepository centroTrabajoRepo;
	
	public ConectorTepServiceImpl(ConfigurationService configService, 
			UtilitiesApp util, 
			ItemOpService itemOpService,
			CentroTrabajoRepository centroTrabajoRepo) {
		super();
		this.configService = configService;
		this.util = util;
		this.itemOpService = itemOpService;
		this.centroTrabajoRepo = centroTrabajoRepo;
	}

	@Override
	public DoctoTEPDocumentosVersion01 crearEncabezadoTEP(ItemOp item, ReporteDTO reporte, String idCTErp) {
		DoctoTEPDocumentosVersion01 encabezado = new DoctoTEPDocumentosVersion01();
		encabezado.setF_cia(configService.getCIA());
		encabezado.setF_consec_auto_reg(1);
		encabezado.setF350_id_co(configService.getC_O());
		encabezado.setF350_id_tipo_docto(configService.getTIPO_DOC_TEP());
		encabezado.setF350_consec_docto(0);
		encabezado.setF350_fecha(util.obtenerFechaFormateada());
		encabezado.setF350_ind_estado(1);
		encabezado.setF350_ind_impresión(0);
		encabezado.setF350_id_clase_docto(731);

		encabezado.setF450_id_centro_trabajo(idCTErp);// revisar
		encabezado.setF450_turno(1);// revisar
		encabezado.setF350_notas("Creado desde Guayacan reporte de tiempos para: " + reporte.getRef() + " Cant: " + reporte.getCantReportar() + " und(s).");
		return encabezado;
	}

	@Override
	public List<DoctoTEPMovimientosVersion01> crearMovTiempos(ReporteDTO reporte, DataConsumoInterface data,
			DataTEP dataTE, String idCTErp, boolean tepFaltante) {		
		
		if(tepFaltante) {
			return crearMovimientoTepFaltante(data, dataTE, idCTErp);
		}

		return crearMovimientosRegulares(reporte, data, dataTE, idCTErp);
	}
	
	private List<DoctoTEPMovimientosVersion01> crearMovimientoTepFaltante(DataConsumoInterface data, 
	        DataTEP dataTE, String idCTErp) {
	    List<DoctoTEPMovimientosVersion01> movs = new ArrayList<>();
	    BigDecimal tiempoMin = BigDecimal.valueOf(0.01);
	    BigDecimal cantMinima = BigDecimal.valueOf(0.0001);
	    crearMovimiento(data, dataTE, idCTErp, tiempoMin, movs, cantMinima);
	    return movs;
	}
	
	private List<DoctoTEPMovimientosVersion01> crearMovimientosRegulares(ReporteDTO reporte, 
	        DataConsumoInterface data, DataTEP dataTE, String idCTErp) {
	    List<DoctoTEPMovimientosVersion01> movs = new ArrayList<>();
	    
	    Integer idItem = reporte.getIdItemFab() != 0 ? reporte.getIdItemFab() : reporte.getIdParte();
	    ItemInterface item = itemOpService.obtenerItemFabricaPorId(idItem);
	    Double valorAplicar = itemOpService.obtenerValorAplicarTepItemCentroTrabajo(idItem, 
	            reporte.getIdCentroTrabajo());
	    
	    BigDecimal cantReportar = calcularCantidadReportar(reporte, item, valorAplicar);
	    BigDecimal cantReportarTotalHoras = BigDecimal.valueOf(valorAplicar * reporte.getCantReportar());
	    
	    distribuirReportes(cantReportarTotalHoras, cantReportar, data, dataTE, idCTErp, movs);
	    
	    return movs;
	}	
	
	private BigDecimal calcularCantidadReportar(ReporteDTO reporte, ItemInterface item, Double valorAplicar) {
	    CentroTrabajo centroTrabajo = centroTrabajoRepo.findById(reporte.getIdCentroTrabajo())
	            .orElseThrow();
	    String um = centroTrabajo.getUm();
	    
	    switch (um) {
	        case "KG":
	            return item.getitem_peso_n().multiply(new BigDecimal(reporte.getCantReportar()));
	        case "ML":
	            return item.getitem_long().multiply(new BigDecimal(reporte.getCantReportar()));
	        case "M2":
	        case "MICRAS":
	            return item.getitem_area().multiply(new BigDecimal(reporte.getCantReportar()));
	        case "GOL":
	            return BigDecimal.valueOf(valorAplicar * reporte.getCantReportar());
	        default:
	            throw new IllegalArgumentException("Unidad de medida no soportada: " + um);
	    }
	}
	
	private void distribuirReportes(BigDecimal cantReportarTotalHoras, BigDecimal cantReportar,
	        DataConsumoInterface data, DataTEP dataTE, String idCTErp, List<DoctoTEPMovimientosVersion01> movs) {
	    List<BigDecimal> reportes = calcularReportes(cantReportarTotalHoras);
	    BigDecimal cantReportarPorIteracion = cantReportar.divide(BigDecimal.valueOf(reportes.size()), 4, RoundingMode.HALF_UP);
	    
	    for (BigDecimal cantReportarActual : reportes) {
	        crearMovimiento(data, dataTE, idCTErp, cantReportarActual, movs, cantReportarPorIteracion);
	    }
	}
	
	private List<BigDecimal> calcularReportes(BigDecimal totalHoras) {
		List<BigDecimal> reportes = new ArrayList<>();
		BigDecimal maxPorReporte = BigDecimal.valueOf(23.59);

		while (totalHoras.compareTo(BigDecimal.ZERO) > 0) {
			if (totalHoras.compareTo(maxPorReporte) > 0) {
				reportes.add(maxPorReporte);
				totalHoras = totalHoras.subtract(maxPorReporte);
			} else {
				reportes.add(totalHoras);
				totalHoras = BigDecimal.ZERO;
			}
		}

		return reportes;
	}
	
	private void crearMovimiento(DataConsumoInterface data, DataTEP dataTE, String idCTErp, BigDecimal cantHorasReportar,
			List<DoctoTEPMovimientosVersion01> movs, BigDecimal cantReportar) {
		try {
			DoctoTEPMovimientosVersion01 mov = new DoctoTEPMovimientosVersion01();
			mov.setF_cia(configService.getCIA());
			mov.setF880_id_co(configService.getC_O());
			mov.setF880_id_tipo_docto(configService.getTIPO_DOC_TEP());
			mov.setF880_consec_docto(0);
			mov.setF880_nro_registro(data.getf851_rowid());// Revisar rowid itemop 851
			mov.setF880_id_tipo_docto_op(configService.getTIPO_DOC_OP_HIJO());
			mov.setF880_consec_docto_op(data.getf850_consec_docto());// revisar 850 consecutivo
			mov.setF880_id_item(data.getf120_id());// Revisar id 120
			mov.setF880_numero_operacion(dataTE.getf809_numero_operacion());// Revisar
			mov.setF880_rowid_ctrabajo(idCTErp);// id centro trabajo
			mov.setF880_ind_tipo_hora(1);// Revisar
			mov.setF880_id_maquina(dataTE.getf807_id());// Revisar
			mov.setF880_ind_operacion_completa(0);
			mov.setF880_cant_completa_base(cantReportar.doubleValue());
			double horasPreCalculadas = precalcularHoras(cantHorasReportar.doubleValue());
			
			mov.setF880_horas(horasPreCalculadas);
			movs.add(mov);			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private double precalcularHoras(double horas) {
		if (horas < 0.01) {
	        horas = 0.01;
	    }
		int horasEnteras = (int) horas;
		double minutos = (horas - horasEnteras);
		// Ajustamos los minutos para contrarrestar el recálculo de la aplicación
		double minutosAjustados = minutos * 60 / 100;

		double resultado = horasEnteras + minutosAjustados;
	    return Math.round(resultado * 100.0) / 100.0;
	}

}
