package com.almatec.controlpiso.integrapps.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_novedades")
public class VistaNovedades {

	@Id
	@Column(name = "novedad_id")
	private Integer idNovedad;
	
	@Column(name = "item_id")
	private Long idItem;
	
	@Column(name = "Item_fab_id")
	private Integer idItemFab;
	
	@Column(name = "item_parte_id")
	private Integer idPerfil;
	
	@Column(name = "C_centrotrabajo_id")
	private Integer idCentroTrabajo;
	
	@Column(name = "C_prooperario_id")	
	private Integer idOperario;
	
	@Column(name = "fecha_creacion")
	private Date fechaCreachion;
	
	@Column(name = "no_conforme")
	private Integer noConforme;
	
	private Integer desperdicio;
	
	private Integer sobrante;
	
	@Column(name = "ind_registro_siesa")
	private Boolean isRegistered;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "Num_Op")
	private Integer numOp;
	
	@Column(name = "Und_Neg")
	private String proyecto;
	
	@Column(name = "A_Operario_Nombre")
	private String nombreOperario;

	public VistaNovedades() {
		super();
	}

	public Integer getIdNovedad() {
		return idNovedad;
	}

	public Long getIdItem() {
		return idItem;
	}

	public Integer getIdItemFab() {
		return idItemFab;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public Integer getIdCentroTrabajo() {
		return idCentroTrabajo;
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public Date getFechaCreachion() {
		return fechaCreachion;
	}

	public Integer getNoConforme() {
		return noConforme;
	}

	public Integer getDesperdicio() {
		return desperdicio;
	}

	public Integer getSobrante() {
		return sobrante;
	}

	public Boolean getIsRegistered() {
		return isRegistered;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public String getProyecto() {
		return proyecto;
	}

	public String getNombreOperario() {
		return nombreOperario;
	}

	

}
