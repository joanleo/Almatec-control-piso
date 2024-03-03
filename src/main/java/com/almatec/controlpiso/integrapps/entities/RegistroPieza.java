package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pro_reg_pieza")
public class RegistroPieza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Reg_Pie_Dia_Id")
	private Integer id;
	
	@Column(name = "C_ciaorg_id")
	private Integer idCia = 22;
	
	@Column(name = "C_centrotrabajo_id")
	private Integer idCT;
	
	@Column(name = "Item_id")
	private Integer idItem;
	
	@Column(name = "N_sstranscurrido")
	private Integer transcurrido=0;
	
	@Column(name = "N_ssreproceso")
	private Integer reproceso=0;
	
	@Column(name = "N_cantidad")
	private Integer cantidad = 1;
	
	@Column(name = "E_activo")
	private Boolean isActivo;
	
	/*@Column(name = "A_usuariocrea")
	private String usuarioCrea;
	
	@Column(name = "A_usuarioedita")
	private String usuarioEdita;*/
	
	@Column(name = "FC_registro")	
	private LocalDateTime fechaCreacion;
	
	@Column(name = "FE_registro")
	private LocalDateTime fechaEdicion;
	
	@Column(name = "E_ajustado")
	private Boolean isAjustado = true;
	
	@Column(name = "C_proconfigproceso_id")
	private Integer idConfig;
	
	@Column(name = "C_prooperario_id")
	private Integer idOperario;
	
	@Column(name = "item_perf_id")
	private Integer idPerfil;

	public RegistroPieza() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCia() {
		return idCia;
	}

	public void setIdCia(Integer idCia) {
		this.idCia = idCia;
	}

	public Integer getIdCT() {
		return idCT;
	}

	public void setIdCT(Integer idCT) {
		this.idCT = idCT;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public Integer getTranscurrido() {
		return transcurrido;
	}

	public void setTranscurrido(Integer transcurrido) {
		this.transcurrido = transcurrido;
	}

	public Integer getReproceso() {
		return reproceso;
	}

	public void setReproceso(Integer reproceso) {
		this.reproceso = reproceso;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	/*public String getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public String getUsuarioEdita() {
		return usuarioEdita;
	}

	public void setUsuarioEdita(String usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
	}*/

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaEdicion() {
		return fechaEdicion;
	}

	public void setFechaEdicion(LocalDateTime fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}

	public Boolean getIsAjustado() {
		return isAjustado;
	}

	public void setIsAjustado(Boolean isAjustado) {
		this.isAjustado = isAjustado;
	}

	public Integer getIdConfig() {
		return idConfig;
	}

	public void setIdConfig(Integer idConfig) {
		this.idConfig = idConfig;
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	@Override
	public String toString() {
		return "RegistroPieza [id=" + id + ", idCia=" + idCia + ", IdCT=" + idCT + ", idItem=" + idItem
				+ ", transcurrido=" + transcurrido + ", reproceso=" + reproceso + ", cantidad=" + cantidad
				+ ", isActivo=" + isActivo + ", fechaCreacion=" + fechaCreacion + ", fechaEdicion=" + fechaEdicion + ", isAjustado=" + isAjustado
				+ ", idConfig=" + idConfig + ", idOperario=" + idOperario + ", idPerfil=" + idPerfil + "]";
	}
	
	
	
	
	
	


}
