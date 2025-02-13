package com.almatec.controlpiso.integrapps.interfaces;

import java.util.Date;

public interface VistaOrdenPvDTO {
	String getIdProyecto();
	String getCliente(); //Cliente
	String getTipoOp(); //
	String getNumOp();
	String getCentroOperaciones(); //CentroOperaciones
	String getZona(); //zona
	Double getKgFabricar();
	Double getKgCumplidos();
	Date getFechaEntrega(); //fechaEntrega
	String getEstadoOp();
	String getEsquemaPintura(); //EsquemaPintura
	String getColorBastidores(); //colorBastidores
	String getColorVigas(); //colorVigas
	String getColorProtectores(); //colorProtectores
	
	default Double getKgPendientes() {
        return getKgFabricar() - (getKgCumplidos() != null ? getKgCumplidos() : 0.0);
    }

	
	
	
	/*Long getIdOpIa();
    Long getIdOpPadre();
    Long getRow430Id();
    Long getRow431Id();
    String getIdEmpIng();
    String getOpUnoEE();
    Long getIdEstDoc();
    Date getFecPlanIngR();
    Date getFecRealIngR();
    String getCodigoSgc();
    String getRespIngenieria();
    Date getFechaIngenieria();
    Date getFechaContractual();
    Date getFConActual();
    Double getKgTtl();
    Double getKgReales();
    String getOrdUnd();
    Double getOrdCant();
    String getObservaciones();
    Boolean getAnulada();
    Date getFecDesp();
    String getArchAdjunto();
    Date getFecCompetada();
    Date getFAper();
    String getBarCodeH();
    String getBarCodeM();
    Date getOrdFechaPlaneada();
    String getBodega();
    String getEptUnoEE();
    String getSciUnoEE();
    String getUndNeg();
    Date getFechaAProd();
    String getF431IdProyecto();
    String getEstadoDoc();
    String getF200Nit();
    String getF120Referencia();
    String getF120Descripcion();
    String getF285Id();
    Long getIdEstadoOp();
    Long getF120Id();
    String getCentrosTrabajoTepReportado();*/
}