package com.almatec.controlpiso.integrapps.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_items_op_programacion")
public class VistaItemsOpsProgramacion {

	@Id
	@Column(name = "item_id")
	private Long idItem;
	
	@Column(name = "id_op_ia")
	private Integer idOp;
	
	@Column(name = "Tipo_OP")
	private String tipoOp;
	
	@Column(name = "Num_Op")
	private Integer numOp;
	
	@Column(name = "id_est_doc")
	private Integer idEstadoDoc;
	
	@Column(name = "Anulada")
	private Boolean isAnulada;
	
	@Column(name = "Und_Neg")
	private String unidadNegocio;
	
	@Column(name = "Zona_Desc")
	private String zona;
	
	@Column(name = "Fecha_Ent")
	private Date fecha;
	
	@Column(name = "Fecha_A_Prod")
	private Date fechaProd;
	
	@Column(name = "f431_id_proyecto")
	private String proyecto;
	
	@Column(name ="f200_razon_social")
	private String cliente;
	
	@Column(name = "estado_doc")
	private String estadoDoc;
	
	@Column(name = "Item_fab_Id")
	private Integer idItemFab;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "cant_req")
	private Integer cant;
	
	@Column(name = "prioridad")
	private Integer prioridad;

	public VistaItemsOpsProgramacion() {
		super();
	}

	public Long getIdItem() {
		return idItem;
	}

	public Integer getIdOp() {
		return idOp;
	}

	public String getTipoOp() {
		return tipoOp;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public Integer getIdEstadoDoc() {
		return idEstadoDoc;
	}

	public Boolean getIsAnulada() {
		return isAnulada;
	}

	public String getUnidadNegocio() {
		return unidadNegocio;
	}

	public String getZona() {
		return zona;
	}

	public Date getFecha() {
		return fecha;
	}

	public Date getFechaProd() {
		return fechaProd;
	}

	public String getProyecto() {
		return proyecto;
	}

	public String getCliente() {
		return cliente;
	}

	public String getEstadoDoc() {
		return estadoDoc;
	}

	public Integer getIdItemFab() {
		return idItemFab;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getCant() {
		return cant;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	@Override
	public String toString() {
		return "VistaItemsOpsProgramacion [idItem=" + idItem + ", idOp=" + idOp + ", tipoOp=" + tipoOp + ", numOp="
				+ numOp + ", idEstadoDoc=" + idEstadoDoc + ", isAnulada=" + isAnulada + ", unidadNegocio="
				+ unidadNegocio + ", zona=" + zona + ", fecha=" + fecha + ", fechaProd=" + fechaProd + ", proyecto="
				+ proyecto + ", cliente=" + cliente + ", estadoDoc=" + estadoDoc + ", idItemFab=" + idItemFab
				+ ", descripcion=" + descripcion + ", cant=" + cant + ", prioridad=" + prioridad + "]";
	}
	
	
	

}
