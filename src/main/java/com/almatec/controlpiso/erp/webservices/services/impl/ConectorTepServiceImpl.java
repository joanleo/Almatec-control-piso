package com.almatec.controlpiso.erp.webservices.services.impl;

import java.math.BigDecimal;
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
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.interfaces.ItemInterface;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Service
public class ConectorTepServiceImpl implements ConectorTepService {
	
	private final ConfigurationService configService;
	private final UtilitiesApp util;
	private final ItemOpService itemOpService;
	
	public ConectorTepServiceImpl(ConfigurationService configService, UtilitiesApp util, ItemOpService itemOpService) {
		super();
		this.configService = configService;
		this.util = util;
		this.itemOpService = itemOpService;
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
		List<DoctoTEPMovimientosVersion01> movs = new ArrayList<>();
		Integer idItem = reporte.getIdItemFab() != 0 ? reporte.getIdItemFab() : reporte.getIdParte();
		ItemInterface item = itemOpService.obtenerItemFabricaPorId(idItem);
		
		if(!tepFaltante) {
			
			Double valorAplicar = itemOpService.obtenerValorAplicarTepItemCentroTrabajo(idItem,
					reporte.getIdCentroTrabajo());
			
			Integer cantPiezasReportar = reporte.getCantReportar();
	
			BigDecimal cantReportarTotalHoras = BigDecimal.valueOf(valorAplicar * reporte.getCantReportar());
			List<BigDecimal> reportes = calcularReportes(cantReportarTotalHoras);
	
			Double kilosTotales = item.getitem_peso_b().multiply(new BigDecimal(cantPiezasReportar)).doubleValue();
	
			for (int i = 0; i < reportes.size(); i++) {
				BigDecimal cantReportarActual = reportes.get(i);
				Double kiloIteracion = kilosTotales / reportes.size();
				crearMovimiento(data, dataTE, idCTErp, cantReportarActual, movs, kiloIteracion);
			}
		}else {
			BigDecimal horas = BigDecimal.valueOf(0.01);
			crearMovimiento(data, dataTE, idCTErp, horas, movs, 0.0001);
		}
		return movs;
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
	
	private void crearMovimiento(DataConsumoInterface data, DataTEP dataTE, String idCTErp, BigDecimal cantidadReportar,
			List<DoctoTEPMovimientosVersion01> movs, Double kilosReportar) {
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
			mov.setF880_cant_completa_base(kilosReportar);
			double horasPreCalculadas = precalcularHoras(cantidadReportar.doubleValue());
			
			mov.setF880_horas(horasPreCalculadas);
			movs.add(mov);			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private double precalcularHoras(double horas) {
		int horasEnteras = (int) horas;
		double minutos = (horas - horasEnteras);
		// Ajustamos los minutos para contrarrestar el recálculo de la aplicación
		double minutosAjustados = minutos * 60 / 100;

		return horasEnteras + minutosAjustados;
	}

}
