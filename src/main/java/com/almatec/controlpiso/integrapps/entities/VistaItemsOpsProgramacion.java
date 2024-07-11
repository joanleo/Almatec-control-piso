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
	@Column(name = "id")
	private String id;
	
	@Column(name = "item_id")
	private Long idItem;
	
	@Column(name = "id_op_ia")
	private Integer idOp;
	
	@Column(name = "tipo_op")
	private String tipoOp;
	
	@Column(name = "num_op")
	private Integer numOp;	
	
	@Column(name = "zona")
	private String zona;
		
	@Column(name ="cliente_nombre")
	private String cliente;
	
	@Column(name = "item_fab_id")
	private Integer idItemFab;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "cant_req")
	private Integer cant;
	
	@Column(name = "prioridad")
	private Integer prioridad;
	
	@Column(name = "grupo")
	private String grupo;
	
	@Column(name = "marca")
	private String marca;
	
	@Column(name = "fecha_real_ing")
	private Date fechaIng;
	
	@Column(name = "f285_descripcion")
	private String centroOperacionNombre;
	
	@Column(name = "f285_id")
	private String centroOperacionId;
	
	@Column(name = "esquema_pintura")
	private Integer esquemaPintura;
	
	@Column(name = "cod_pintura")
	private Integer codPintura;
	
	@Column(name = "peso_pintura")
	private Double pesoPintura;
	
	@Column(name = "codigo_erp")
	private Integer codErp;
	
	@Column(name = "kg_fabricar")
	private Double totalKgOp;
	
	@Column(name = "id_centro_trabajo_prioridad")
	private Integer idCentroTrabajoPrioridad;
		
	@Column(name = "cant_cumplida")
	private Double cantCumplida;

	public VistaItemsOpsProgramacion() {
		super();
	}

	public String getId() {
		return id;
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

	public String getZona() {
		return zona;
	}

	public String getCliente() {
		return cliente;
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

	public String getGrupo() {
		return grupo;
	}

	public String getMarca() {
		return marca;
	}

	public Date getFechaIng() {
		return fechaIng;
	}

	public String getCentroOperacionNombre() {
		return centroOperacionNombre;
	}

	public String getCentroOperacionId() {
		return centroOperacionId;
	}

	public Integer getEsquemaPintura() {
		return esquemaPintura;
	}

	public Integer getCodPintura() {
		return codPintura;
	}

	public Double getPesoPintura() {
		return pesoPintura;
	}

	public Integer getCodErp() {
		return codErp;
	}

	public Double getTotalKgOp() {
		return totalKgOp;
	}

	public Integer getIdCentroTrabajoPrioridad() {
		return idCentroTrabajoPrioridad;
	}

	public Double getCantCumplida() {
		return cantCumplida;
	}

}
