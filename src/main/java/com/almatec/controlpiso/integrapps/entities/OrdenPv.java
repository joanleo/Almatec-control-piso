package com.almatec.controlpiso.integrapps.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orden_pv_vw")
public class OrdenPv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_op_ia")
	private Integer id;
	@Column(name = "id_op_padre")
	private Integer idPadre;
	/*@Column(name = "Row850_id")
	private Integer rowIdOpErp;
	@Column(name = "Row851_id")
	private Integer rowIdMovOpErp;*/
	@Column(name = "Id_Emp_Ing")
	private Integer idEmpIng;
	@Column(name = "Tipo_OP")
	private String tipoOp;
	@Column(name = "Num_Op")
	private Integer numOp;
	@Column(name = "op_UnoEE")
	private String opErp;
	@Column(name = "id_est_doc")
	private Integer idEstadoDoc;
	@Column(name = "Fec_plan_IngR")
	private Date fecPlanIngR;
	@Column(name = "Fec_Real_IngR")
	private Date fecRealIngR;
	@Column(name = "Codigo_Sgc")
	private String codigoSgc;
	@Column(name = "Resp_Ingenieria")
	private String respIngenieria;
	@Column(name = "fecha_ingenieria")
	private Date fechaIngenieria;
	@Column(name = "Fecha_contractual")
	private Date fechaContractual;
	@Column(name = "F_Con_Actual")
	private Date fConActual;
	@Column(name = "Kg_Ttl")
	private Double kgTotal;
	@Column(name = "Kg_Reales")
	private Double kgReal;
	@Column(name = "ord_und")
	private String um;
	@Column(name = "ord_cant")
	private Double cant;
	@Column(name = "Observaciones")
	private String observaciones;
	@Column(name = "Anulada")
	private Boolean isAnulada;
	@Column(name = "Fec_Desp")
	private Date fechaDespacho;
	@Column(name = "Arch_adjunto")
	private String adjunto;
	@Column(name = "Fec_Competada")
	private Date fechaCompletada;
	@Column(name = "F_Aper")
	private Date fAper;
	@Column(name = "BarCodeH")
	private String barCodeHumano;
	@Column(name = "BarCodeM")
	private String barCodeMaquina;
	@Column(name = "ord_fecha_planeada")
	private Date ordFechaPlaneada;
	@Column(name = "Bodega")
	private String bodega;
	@Column(name = "Ept_UnoEE")
	private String eptUnoEE;
	@Column(name = "Sci_UnoEE")
	private String sciUnoEE;
	@Column(name = "Und_Neg")
	private String unidadNegocio;
	@Column(name = "Zona_Desc")
	private String zonaSistema;
	@Column(name = "Fecha_Ent")
	private Date fechaEntrega;
	@Column(name = "Fecha_A_Prod")
	private Date fechaAProduccion;
	@Column(name = "Esq_Pintura")
	private String esquemaPintura;
	@Column(name = "Color_Bas")
	private String colorBastidores;
	@Column(name = "Color_Vigas")
	private String colorVigas;
	@Column(name = "Color_Pro")
	private String colorProtectores;
	@Column(name = "f431_id_proyecto")
	private String idProyecto;
	@Column(name = "f200_razon_social")
	private String cliente;
	@Column(name = "estado_doc")
	private String estadoDoc;
	
	public OrdenPv() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}
	/*public Integer getRowIdOpErp() {
		return rowIdOpErp;
	}
	public void setRowIdOpErp(Integer rowIdOpErp) {
		this.rowIdOpErp = rowIdOpErp;
	}
	public Integer getRowIdMovOpErp() {
		return rowIdMovOpErp;
	}
	public void setRowIdMovOpErp(Integer rowIdMovOpErp) {
		this.rowIdMovOpErp = rowIdMovOpErp;
	}*/
	public Integer getIdEmpIng() {
		return idEmpIng;
	}
	public void setIdEmpIng(Integer idEmpIng) {
		this.idEmpIng = idEmpIng;
	}
	public String getTipoOp() {
		return tipoOp;
	}
	public void setTipoOp(String tipoOp) {
		this.tipoOp = tipoOp;
	}
	public Integer getNumOp() {
		return numOp;
	}
	public void setNumOp(Integer numOp) {
		this.numOp = numOp;
	}
	public String getOpErp() {
		return opErp;
	}
	public void setOpErp(String opErp) {
		this.opErp = opErp;
	}
	public Integer getIdEstadoDoc() {
		return idEstadoDoc;
	}
	public void setIdEstadoDoc(Integer idEstadoDoc) {
		this.idEstadoDoc = idEstadoDoc;
	}
	public Date getFecPlanIngR() {
		return fecPlanIngR;
	}
	public void setFecPlanIngR(Date fecPlanIngR) {
		this.fecPlanIngR = fecPlanIngR;
	}
	public Date getFecRealIngR() {
		return fecRealIngR;
	}
	public void setFecRealIngR(Date fecRealIngR) {
		this.fecRealIngR = fecRealIngR;
	}
	public String getCodigoSgc() {
		return codigoSgc;
	}
	public void setCodigoSgc(String codigoSgc) {
		this.codigoSgc = codigoSgc;
	}
	public String getRespIngenieria() {
		return respIngenieria;
	}
	public void setRespIngenieria(String respIngenieria) {
		this.respIngenieria = respIngenieria;
	}
	public Date getFechaIngenieria() {
		return fechaIngenieria;
	}
	public void setFechaIngenieria(Date fechaIngenieria) {
		this.fechaIngenieria = fechaIngenieria;
	}
	public Date getFechaContractual() {
		return fechaContractual;
	}
	public void setFechaContractual(Date fechaContractual) {
		this.fechaContractual = fechaContractual;
	}
	public Date getfConActual() {
		return fConActual;
	}
	public void setfConActual(Date fConActual) {
		this.fConActual = fConActual;
	}
	public Double getKgTotal() {
		return kgTotal;
	}
	public void setKgTotal(Double kgTotal) {
		this.kgTotal = kgTotal;
	}
	public Double getKgReal() {
		return kgReal;
	}
	public void setKgReal(Double kgReal) {
		this.kgReal = kgReal;
	}
	public String getUm() {
		return um;
	}
	public void setUm(String um) {
		this.um = um;
	}
	public Double getCant() {
		return cant;
	}
	public void setCant(Double cant) {
		this.cant = cant;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Boolean getIsAnulada() {
		return isAnulada;
	}
	public void setIsAnulada(Boolean isAnulada) {
		this.isAnulada = isAnulada;
	}
	public Date getFechaDespacho() {
		return fechaDespacho;
	}
	public void setFechaDespacho(Date fechaDespacho) {
		this.fechaDespacho = fechaDespacho;
	}
	public String getAdjunto() {
		return adjunto;
	}
	public void setAdjunto(String adjunto) {
		this.adjunto = adjunto;
	}
	public Date getFechaCompletada() {
		return fechaCompletada;
	}
	public void setFechaCompletada(Date fechaCompletada) {
		this.fechaCompletada = fechaCompletada;
	}
	public Date getfAper() {
		return fAper;
	}
	public void setfAper(Date fAper) {
		this.fAper = fAper;
	}
	public String getBarCodeHumano() {
		return barCodeHumano;
	}
	public void setBarCodeHumano(String barCodeHumano) {
		this.barCodeHumano = barCodeHumano;
	}
	public String getBarCodeMaquina() {
		return barCodeMaquina;
	}
	public void setBarCodeMaquina(String barCodeMaquina) {
		this.barCodeMaquina = barCodeMaquina;
	}
	public Date getOrdFechaPlaneada() {
		return ordFechaPlaneada;
	}
	public void setOrdFechaPlaneada(Date ordFechaPlaneada) {
		this.ordFechaPlaneada = ordFechaPlaneada;
	}
	public String getBodega() {
		return bodega;
	}
	public void setBodega(String bodega) {
		this.bodega = bodega;
	}
	public String getEptUnoEE() {
		return eptUnoEE;
	}
	public void setEptUnoEE(String eptUnoEE) {
		this.eptUnoEE = eptUnoEE;
	}
	public String getSciUnoEE() {
		return sciUnoEE;
	}
	public void setSciUnoEE(String sciUnoEE) {
		this.sciUnoEE = sciUnoEE;
	}
	public String getUnidadNegocio() {
		return unidadNegocio;
	}
	public void setUnidadNegocio(String unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}
	public String getZonaSistema() {
		return zonaSistema;
	}
	public void setZonaSistema(String zonaSistema) {
		this.zonaSistema = zonaSistema;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public Date getFechaAProduccion() {
		return fechaAProduccion;
	}
	public void setFechaAProduccion(Date fechaAProduccion) {
		this.fechaAProduccion = fechaAProduccion;
	}
	public String getEsquemaPintura() {
		return esquemaPintura;
	}
	public void setEsquemaPintura(String pintura) {
		this.esquemaPintura = pintura;
	}
	public String getColorBastidores() {
		return colorBastidores;
	}
	public void setColorBastidores(String colorBastidores) {
		this.colorBastidores = colorBastidores;
	}
	public String getColorVigas() {
		return colorVigas;
	}
	public void setColorVigas(String colorVigas) {
		this.colorVigas = colorVigas;
	}
	public String getColorProtectores() {
		return colorProtectores;
	}
	public void setColorProtectores(String colorProtectores) {
		this.colorProtectores = colorProtectores;
	}
	public String getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(String id_proyecto) {
		this.idProyecto = id_proyecto;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getEstadoDoc() {
		return estadoDoc;
	}
	public void setEstadoDoc(String estadoDoc) {
		this.estadoDoc = estadoDoc;
	}
	@Override
	public String toString() {
		return "OrdenPv [id=" + id + ", idPadre=" + idPadre + ", idEmpIng=" + idEmpIng + ", tipoOp=" + tipoOp + ", numOp=" + numOp + ", opErp="
				+ opErp + ", idEstadoDoc=" + idEstadoDoc + ", fecPlanIngR=" + fecPlanIngR + ", fecRealIngR="
				+ fecRealIngR + ", codigoSgc=" + codigoSgc + ", respIngenieria=" + respIngenieria + ", fechaIngenieria="
				+ fechaIngenieria + ", fechaContractual=" + fechaContractual + ", fConActual=" + fConActual
				+ ", kgTotal=" + kgTotal + ", kgReal=" + kgReal + ", um=" + um + ", cant=" + cant + ", observaciones="
				+ observaciones + ", isAnulada=" + isAnulada + ", fechaDespacho=" + fechaDespacho + ", adjunto="
				+ adjunto + ", fechaCompletada=" + fechaCompletada + ", fAper=" + fAper + ", barCodeHumano="
				+ barCodeHumano + ", barCodeMaquina=" + barCodeMaquina + ", ordFechaPlaneada=" + ordFechaPlaneada
				+ ", bodega=" + bodega + ", eptUnoEE=" + eptUnoEE + ", sciUnoEE=" + sciUnoEE + ", unidadNegocio="
				+ unidadNegocio + ", zonaSistema=" + zonaSistema + ", fechaEntrega=" + fechaEntrega
				+ ", fechaAProduccion=" + fechaAProduccion + ", pintura=" + esquemaPintura + ", colorBastidores="
				+ colorBastidores + ", colorVigas=" + colorVigas + ", colorProtectores=" + colorProtectores
				+ ", idProyecto=" + idProyecto + ", cliente=" + cliente + ", estadoDoc=" + estadoDoc + "]";
	}


}
