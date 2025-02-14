package com.almatec.controlpiso.integrapps.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pro_centrostrabajo")
public class CentroTrabajo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_centrotrabajo_id")
	private Integer id;
	
	@Column(name = "C_ciaorg_id")
	private Integer idCia;
	
	@Column(name = "A_co")
	private String centroOperacion;
	
	@Column(name = "A_nombre")
	private String nombre;
	
	@Column(name = " A_CT")
	private String aCT;
	
	@Column(name = "A_Unidad")
	private String um;
	
	@Column(name = "C_centrabcod")
	private Integer codigo;

	@Column(name = "D_centrot")
	private String centroCostos;
	
	@Column(name = "Id_Ct_Padre")
	private String idCentroTrabajoPadre;
	
	@Column(name = "E_activo")
	private Boolean isActivo=true;
	
	@Column(name = "A_usuariocrea")
	private String usuarioCrea;
	
	@Column(name = "A_usuarioedita")
	private String usuarioEdita;
	
	@Column(name = "FC_registro")
	private Date fechaCreacion;
	
	@Column(name = "FE_registro")
	private Date fechaEdicion;
	
	@Column(name = "A_codbarmaq")
	private String codigoBarraMaquina = "0";
	
	@Column(name = "A_codbarhum")
	private String codigoBarraHum;
	
	@Column(name = "C_estacion_id")
	private String idEstacion;
	
	@Column(name = "C_Disponible")
	private Integer tiempoDisponible;
	
	@Column(name = "E_ctrl_pieza")
	private Boolean isControlPieza =false;
	
	@Column(name = "E_servicio")
	private Boolean isControlServicio =false;
	
	@Column(name = "is_show")
	private Boolean isShow =true;
	
	@Column(name = "f808_id")
	private Integer idCentroTrabajoErp;

	public CentroTrabajo() {
		super();
	}

	public CentroTrabajo(Integer id, Integer idCia, String centroOperacion, String nombre, String um, Integer codigo,
			String centroCostos, String idCentroTrabajoPadre, Boolean isActivo, String usuarioCrea, String usuarioEdita,
			Date fechaCreacion, Date fechaEdicion, String codigoBarraMaquina, String codigoBarraHum, String idEstacion,
			Integer tiempoDisponible, Boolean isControlPieza, Boolean isControlServicio, Boolean isShow,
			Integer idCentroTrabajoErp) {
		super();
		this.id = id;
		this.idCia = idCia;
		this.centroOperacion = centroOperacion;
		this.nombre = nombre;
		this.um = um;
		this.codigo = codigo;
		this.centroCostos = centroCostos;
		this.idCentroTrabajoPadre = idCentroTrabajoPadre;
		this.isActivo = isActivo;
		this.usuarioCrea = usuarioCrea;
		this.usuarioEdita = usuarioEdita;
		this.fechaCreacion = fechaCreacion;
		this.fechaEdicion = fechaEdicion;
		this.codigoBarraMaquina = codigoBarraMaquina;
		this.codigoBarraHum = codigoBarraHum;
		this.idEstacion = idEstacion;
		this.tiempoDisponible = tiempoDisponible;
		this.isControlPieza = isControlPieza;
		this.isControlServicio = isControlServicio;
		this.isShow = isShow;
		this.idCentroTrabajoErp = idCentroTrabajoErp;
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

	public String getCentroOperacion() {
		return centroOperacion;
	}

	public void setCentroOperacion(String centroOperacion) {
		this.centroOperacion = centroOperacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCentroCostos() {
		return centroCostos;
	}

	public void setCentroCostos(String centroCostos) {
		this.centroCostos = centroCostos;
	}

	public String getIdCentroTrabajoPadre() {
		return idCentroTrabajoPadre;
	}

	public void setIdCentroTrabajoPadre(String idCentroTrabajoPadre) {
		this.idCentroTrabajoPadre = idCentroTrabajoPadre;
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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaEdicion() {
		return fechaEdicion;
	}

	public void setFechaEdicion(Date fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}

	public String getCodigoBarraMaquina() {
		return codigoBarraMaquina;
	}

	public void setCodigoBarraMaquina(String codigoBarraMaquina) {
		this.codigoBarraMaquina = codigoBarraMaquina;
	}

	public String getCodigoBarraHum() {
		return codigoBarraHum;
	}

	public void setCodigoBarraHum(String codigoBarraHum) {
		this.codigoBarraHum = codigoBarraHum;
	}

	public String getIdEstacion() {
		return idEstacion;
	}

	public void setIdEstacion(String idEstacion) {
		this.idEstacion = idEstacion;
	}

	public Integer getTiempoDisponible() {
		return tiempoDisponible;
	}

	public void setTiempoDisponible(Integer tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}

	public Boolean getIsControlPieza() {
		return isControlPieza;
	}

	public void setIsControlPieza(Boolean isControlPieza) {
		this.isControlPieza = isControlPieza;
	}

	public Boolean getIsControlServicio() {
		return isControlServicio;
	}

	public void setIsControlServicio(Boolean isControlServicio) {
		this.isControlServicio = isControlServicio;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	public Integer getIdCentroTrabajoErp() {
		return idCentroTrabajoErp;
	}

	public void setIdCentroTrabajoErp(Integer idCentroTrabajoErp) {
		this.idCentroTrabajoErp = idCentroTrabajoErp;
	}

	public String getaCT() {
		return aCT;
	}

	public void setaCT(String aCT) {
		this.aCT = aCT;
	}

	@Override
	public String toString() {
		return "CentroTrabajo [id=" + id + ", idCia=" + idCia + ", centroOperacion=" + centroOperacion + ", nombre="
				+ nombre + ", um=" + um + ", codigo=" + codigo + ", centroCostos=" + centroCostos
				+ ", idCentroTrabajoPadre=" + idCentroTrabajoPadre + ", isActivo=" + isActivo + ", usuarioCrea="
				+ usuarioCrea + ", usuarioEdita=" + usuarioEdita + ", fechaCreacion=" + fechaCreacion
				+ ", fechaEdicion=" + fechaEdicion + ", codigoBarraMaquina=" + codigoBarraMaquina + ", codigoBarraHum="
				+ codigoBarraHum + ", idEstacion=" + idEstacion + ", tiempoDisponible=" + tiempoDisponible
				+ ", isControlPieza=" + isControlPieza + ", isControlServicio=" + isControlServicio + ", isShow="
				+ isShow + ", idCentroTrabajoErp=" + idCentroTrabajoErp + "]";
	}

	
}
