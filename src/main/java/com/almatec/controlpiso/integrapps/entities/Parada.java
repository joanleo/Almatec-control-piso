package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pro_parada")
public class Parada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_proparada_id")
	private Long id;
	
	@Column(name = "C_ciaorg_id")
	private Long idCia= 0L;
	
	@Column(name = "A_co", length = 3, nullable = false)
	private String co= "1";
	
	@Column(name = "A_nombre", length = 250)
	private String nombre= "Operacion";
	
	@Column(name = "A_descripcion", length = 15, nullable = false)
	private String descripcion = ".";
	
	@Column(name = "A_tipo", length = 15, nullable = false)
	private String tipo= "Tipo";
	
	@Column(name = "E_activo", nullable = false)
	private Boolean isActivo= true;
	
	@Column(name = "A_usuariocrea", length = 100, nullable = false)
	private String usuarioCrea= "UsuarioCrea";
	
	@Column(name = "A_usuarioedita", length = 100)
	private String usuarioEdita;
	
	@Column(name = "FC_registro")
	private LocalDateTime fecha;
	
	@Column(name = "FE_registro")
	private LocalDateTime fechaEdicion;
	
	@Column(name = "A_codbarmaq", length = 15)
	private String codBarrasM = "0000";
	
	@Column(name = "A_codbarhum", length = 15)
	private String codBarraH = "0001";
	
	@Column(name = "A_afecta", length = 4)
	private String afecta= "PROC";
	
	@Column(name = "C_color", length = 12, nullable = false)
	private String color= "&H00FFFFFF&";
	
	@Column(name = "A_codigo", length = 3, nullable = false)
	private String codigo= "0";

	public Parada() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCia() {
		return idCia;
	}

	public void setIdCia(Long idCia) {
		this.idCia = idCia;
	}

	public String getCo() {
		return co;
	}

	public void setCo(String co) {
		this.co = co;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
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

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public LocalDateTime getFechaEdicion() {
		return fechaEdicion;
	}

	public void setFechaEdicion(LocalDateTime fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}

	public String getCodBarrasM() {
		return codBarrasM;
	}

	public void setCodBarrasM(String codBarrasM) {
		this.codBarrasM = codBarrasM;
	}

	public String getCodBarraH() {
		return codBarraH;
	}

	public void setCodBarraH(String codBarraH) {
		this.codBarraH = codBarraH;
	}

	public String getAfecta() {
		return afecta;
	}

	public void setAfecta(String afecta) {
		this.afecta = afecta;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


}
