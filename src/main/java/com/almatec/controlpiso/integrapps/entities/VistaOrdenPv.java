package com.almatec.controlpiso.integrapps.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_orden_pv")
public class VistaOrdenPv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_op_ia")
	private Integer id;
	@Column(name = "id_op_padre")
	private Integer idPadre;
	@Column(name = "Row430_id")
	private Integer rowIdOp;
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
	private String zona;
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
	@Column(name = "f120_referencia")
	private String itemRef;
	@Column(name = "f120_descripcion")
	private String itemDescripcion;
	@Column(name = "kg_fabricar")
	private Double kilosFabricar;
	@Column(name = "f285_descripcion")
	private String centroOperaciones;
	@Column(name = "id_estado_op")
	private Integer idEstadoOp;
	@Column(name = "f285_id")
	private String idCentroOperaciones;
	@Column(name = "f120_id")
	private Integer itemId;
	@Column(name = "estado_op")
	private String estadoOp;
	@Column(name = "centros_trabajo_tep_reportado")
	private String centrosTrabajoTepReportado;

	public VistaOrdenPv() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdPadre() {
		return idPadre;
	}

	public Integer getRowIdOp() {
		return rowIdOp;
	}

	public Integer getIdEmpIng() {
		return idEmpIng;
	}

	public String getTipoOp() {
		return tipoOp;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public String getOpErp() {
		return opErp;
	}

	public Integer getIdEstadoDoc() {
		return idEstadoDoc;
	}

	public Date getFecPlanIngR() {
		return fecPlanIngR;
	}

	public Date getFecRealIngR() {
		return fecRealIngR;
	}

	public String getCodigoSgc() {
		return codigoSgc;
	}

	public String getRespIngenieria() {
		return respIngenieria;
	}

	public Date getFechaIngenieria() {
		return fechaIngenieria;
	}

	public Date getFechaContractual() {
		return fechaContractual;
	}

	public Date getfConActual() {
		return fConActual;
	}

	public Double getKgTotal() {
		return kgTotal;
	}

	public Double getKgReal() {
		return kgReal;
	}

	public String getUm() {
		return um;
	}

	public Double getCant() {
		return cant;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public Boolean getIsAnulada() {
		return isAnulada;
	}

	public Date getFechaDespacho() {
		return fechaDespacho;
	}

	public String getAdjunto() {
		return adjunto;
	}

	public Date getFechaCompletada() {
		return fechaCompletada;
	}

	public Date getfAper() {
		return fAper;
	}

	public String getBarCodeHumano() {
		return barCodeHumano;
	}

	public String getBarCodeMaquina() {
		return barCodeMaquina;
	}

	public Date getOrdFechaPlaneada() {
		return ordFechaPlaneada;
	}

	public String getBodega() {
		return bodega;
	}

	public String getEptUnoEE() {
		return eptUnoEE;
	}

	public String getSciUnoEE() {
		return sciUnoEE;
	}

	public String getUnidadNegocio() {
		return unidadNegocio;
	}

	public String getZona() {
		return zona;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public Date getFechaAProduccion() {
		return fechaAProduccion;
	}

	public String getEsquemaPintura() {
		return esquemaPintura;
	}

	public String getColorBastidores() {
		return colorBastidores;
	}

	public String getColorVigas() {
		return colorVigas;
	}

	public String getColorProtectores() {
		return colorProtectores;
	}

	public String getIdProyecto() {
		return idProyecto;
	}

	public String getCliente() {
		return cliente;
	}

	public String getEstadoDoc() {
		return estadoDoc;
	}

	public String getItemRef() {
		return itemRef;
	}

	public String getItemDescripcion() {
		return itemDescripcion;
	}

	public Double getKilosFabricar() {
		return kilosFabricar;
	}

	public String getCentroOperaciones() {
		return centroOperaciones;
	}

	public String getEstadoOp() {
		return estadoOp;
	}

	public String getIdCentroOperaciones() {
		return idCentroOperaciones;
	}

	public Integer getItemId() {
		return itemId;
	}

	public Integer getIdEstadoOp() {
		return idEstadoOp;
	}

	public String getCentrosTrabajoTepReportado() {
		return centrosTrabajoTepReportado;
	}


	@Override
	public String toString() {
		return "VistaOrdenPv [id=" + id + ", idPadre=" + idPadre + ", rowIdOp=" + rowIdOp + ", idEmpIng=" + idEmpIng
				+ ", tipoOp=" + tipoOp + ", numOp=" + numOp + ", opErp=" + opErp + ", idEstadoDoc=" + idEstadoDoc
				+ ", fecPlanIngR=" + fecPlanIngR + ", fecRealIngR=" + fecRealIngR + ", codigoSgc=" + codigoSgc
				+ ", respIngenieria=" + respIngenieria + ", fechaIngenieria=" + fechaIngenieria + ", fechaContractual="
				+ fechaContractual + ", fConActual=" + fConActual + ", kgTotal=" + kgTotal + ", kgReal=" + kgReal
				+ ", um=" + um + ", cant=" + cant + ", observaciones=" + observaciones + ", isAnulada=" + isAnulada
				+ ", fechaDespacho=" + fechaDespacho + ", adjunto=" + adjunto + ", fechaCompletada=" + fechaCompletada
				+ ", fAper=" + fAper + ", barCodeHumano=" + barCodeHumano + ", barCodeMaquina=" + barCodeMaquina
				+ ", ordFechaPlaneada=" + ordFechaPlaneada + ", bodega=" + bodega + ", eptUnoEE=" + eptUnoEE
				+ ", sciUnoEE=" + sciUnoEE + ", unidadNegocio=" + unidadNegocio + ", zona=" + zona + ", fechaEntrega="
				+ fechaEntrega + ", fechaAProduccion=" + fechaAProduccion + ", esquemaPintura=" + esquemaPintura
				+ ", colorBastidores=" + colorBastidores + ", colorVigas=" + colorVigas + ", colorProtectores="
				+ colorProtectores + ", idProyecto=" + idProyecto + ", cliente=" + cliente + ", estadoDoc=" + estadoDoc
				+ ", itemRef=" + itemRef + ", itemDescripcion=" + itemDescripcion + ", kilosFabricar=" + kilosFabricar
				+ ", centroOperaciones=" + centroOperaciones + ", idEstadoOp=" + idEstadoOp + ", idCentroOperaciones="
				+ idCentroOperaciones + ", itemId=" + itemId + ", estadoOp=" + estadoOp
				+ ", centrosTrabajoTepReportado=" + centrosTrabajoTepReportado + "]";
	}
	
}
