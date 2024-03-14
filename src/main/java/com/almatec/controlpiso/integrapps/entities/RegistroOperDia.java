package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "pro_regoperxdia")
public class RegistroOperDia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_proregoperxdia_id")
	private Integer id;
	
	@Column(name = "C_proconfigproceso_id")
	private Integer idConfigProceso;
	
	@Column(name = "C_ciaorg_id")
	private Integer cia;
	
	@Column(name = "C_centrotrabajo_id")
	private Integer idCentroT;
	
	@Column(name = "C_prooperario_id")
	private Integer idOperario;
	
	@Column(name = "C_proparada_id")
	private Integer idParada;
	
	@Column(name = "C_Pro_Item_Id")
	private Integer idItem;
	
	@Column(name = "F_turnoini")
	private Date fechaInicio;
	
	@Column(name = "F_turnofin")
	private Date fechaFin;
	
	@Column(name = "A_usuariocrea")
	private String usuarioCrea;
	
	@Column(name = "A_usuarioedita")
	private String usuarioEdita;
	
	@Column(name = "FC_registro")
	private LocalDateTime fechaCreacion;
	
	@Column(name = "FE_registro")
	private LocalDateTime fechaEdicion;
	
	@Column(name = "N_sstranscurrido")
	private Float segundosTrans;
	
	@Column(name = "N_ssimproductivo")
	private Float segundosImpro;
	
	@Column(name = "C_prooperario_Repro")
	private Boolean isOperarioRepro;
	
	@Column(name = "E_ajustado")
	private Boolean isAjustado;
	
	@Column(name = "E_activo")
	private Boolean isActivo;

	public RegistroOperDia() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdConfigProceso() {
		return idConfigProceso;
	}

	public void setIdConfigProceso(Integer idConfigProceso) {
		this.idConfigProceso = idConfigProceso;
	}

	public Integer getCia() {
		return cia;
	}

	public void setCia(Integer cia) {
		this.cia = cia;
	}

	public Integer getIdCentroT() {
		return idCentroT;
	}

	public void setIdCentroT(Integer idCT) {
		this.idCentroT = idCT;
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
	}

	public Integer getIdParada() {
		return idParada;
	}

	public void setIdParada(Integer idParada) {
		this.idParada = idParada;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getUsuarioCrea() {
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
	}

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

	public Float getSegundosTrans() {
		return segundosTrans;
	}

	public void setSegundosTrans(Float segundosTrans) {
		this.segundosTrans = segundosTrans;
	}

	public Float getSegundosImpro() {
		return segundosImpro;
	}

	public void setSegundosImpro(Float segundosImpro) {
		this.segundosImpro = segundosImpro;
	}

	public Boolean getIsOperarioRepro() {
		return isOperarioRepro;
	}

	public void setIsOperarioRepro(Boolean isOperarioRepro) {
		this.isOperarioRepro = isOperarioRepro;
	}

	public Boolean getIsAjustado() {
		return isAjustado;
	}

	public void setIsAjustado(Boolean isAjustado) {
		this.isAjustado = isAjustado;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	@Override
	public String toString() {
		return "RegistroOperDia [id=" + id + ", idConfigProceso=" + idConfigProceso + ", cia=" + cia + ", idCentroT=" + idCentroT
				+ ", idOperario=" + idOperario + ", idParada=" + idParada + ", idItem=" + idItem + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", usuarioCrea=" + usuarioCrea + ", usuarioEdita="
				+ usuarioEdita + ", fechaCreacion=" + fechaCreacion + ", fechaEdicion=" + fechaEdicion
				+ ", segundosTrans=" + segundosTrans + ", segundosImpro=" + segundosImpro + ", isOperarioRepro="
				+ isOperarioRepro + ", isAjustado=" + isAjustado + ", isActivo=" + isActivo + "]";
	}


}
