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
	
	@Column(name = "centro_trabajo")
	private String centroTrabajo;
	
	@Column(name = "C_prooperario_id")	
	private Integer idOperario;
	
	@Column(name = "fecha_creacion")
	private Date fechaCreachion;
	
	@Column(name = "no_conforme")
	private Integer noConforme;
	
	private Integer desperdicio;
	
	private Integer sobrante;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	private String op;
	
	@Column(name = "A_Operario_Nombre")
	private String nombreOperario;
	
	@Column(name = "cod_erp_mp")
	private Integer codigoErpMateriaPrima;
	
	@Column(name = "lote")
	private String loteMateriaPrima;
	
	@Column(name = "ind_registro_siesa")
	private Boolean enviadoErp;
	

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

	public String getCentroTrabajo() {
		return centroTrabajo;
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

	public String getDescripcion() {
		return descripcion;
	}

	public String getOp() {
		return op;
	}

	public String getNombreOperario() {
		return nombreOperario;
	}

	public Integer getCodigoErpMateriaPrima() {
		return codigoErpMateriaPrima;
	}

	public String getLoteMateriaPrima() {
		return loteMateriaPrima;
	}

	public Boolean getEnviadoErp() {
		return enviadoErp;
	}	

}
