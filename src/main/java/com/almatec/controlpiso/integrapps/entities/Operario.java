package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "pro_operario")
public class Operario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_prooperario_id")
	private Integer id;
	
	@Column(name = "C_ciaorg_id")
	private Integer cia = 22;
	
	@Column(name = "A_co")
	private String co = "001";
	
	@OneToOne
	@JoinColumn(name = "Per_Id", referencedColumnName = "Per_Id", nullable = false)
	private Persona persona;
		
	@Column(name = "A_Usuariocrea")
	private String usuarioCrea;
	
	@Column(name = "FC_Registro")
	private LocalDateTime fechaCreacion;
	
	@Column(name = "C_centrotrabajo_id")
	private Long idCentroT = 0L;
	
	@Column(name = "F_configuracion")
	private LocalDateTime fechaConfig;
	
	@Column(name = "C_proturnos_id")
	private Long idTurno;
	
	@Column(name = "E_activo")
	private Boolean isActivo = true;
	
	@Column(name = "C_estadoproceso_id")
	private Integer idEstadoProceso = 1;
	
	@Column(name = "A_Usuarioedita")
	private String usuarioEdita;
	
	@Column(name = "FE_Registro")
	private LocalDateTime fechaEdicion;
	
	@Column(name = "A_Operario_Nombre")
	private String nombre;
	
	@Column(name = "id_frente")
	private Integer idFrente = 100;
	
	@OneToMany(mappedBy = "operario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<PoliFuncion> poliFunciones = new HashSet<>();
	
	@PrePersist
	protected void onCreate() {
		LocalDateTime now = LocalDateTime.now();
		this.fechaCreacion = now;
	}

	public Operario() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCia() {
		return cia;
	}

	public void setCia(Integer cia) {
		this.cia = cia;
	}

	public String getCo() {
		return co;
	}

	public void setCo(String co) {
		this.co = co;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getIdCentroT() {
		return idCentroT;
	}

	public void setIdCentroT(Long idCentroT) {
		this.idCentroT = idCentroT;
	}

	public LocalDateTime getFechaConfig() {
		return fechaConfig;
	}

	public void setFechaConfig(LocalDateTime fechaConfig) {
		this.fechaConfig = fechaConfig;
	}

	public Long getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(Long idTurno) {
		this.idTurno = idTurno;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	public Integer getIdEstadoProceso() {
		return idEstadoProceso;
	}

	public void setIdEstadoProceso(Integer idEstadoProceso) {
		this.idEstadoProceso = idEstadoProceso;
	}

	public String getUsuarioEdita() {
		return usuarioEdita;
	}

	public void setUsuarioEdita(String usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
	}

	public LocalDateTime getFechaEdicion() {
		return fechaEdicion;
	}

	public void setFechaEdicion(LocalDateTime fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getIdFrente() {
		return idFrente;
	}

	public void setIdFrente(Integer idFrente) {
		this.idFrente = idFrente;
	}

	public Set<PoliFuncion> getPoliFunciones() {
		return poliFunciones;
	}

	public void setPoliFunciones(Set<PoliFuncion> poliFunciones) {
		this.poliFunciones = poliFunciones;
	}

	public void addCentroTrabajo(CentroTrabajo centroTrabajo) {
        PoliFuncion poliFuncion = new PoliFuncion();
        poliFuncion.setOperario(this);
        poliFuncion.setCentroTrabajo(centroTrabajo);
        this.poliFunciones.add(poliFuncion);
    }

    public void removeCentroTrabajo(CentroTrabajo centroTrabajo) {
        this.poliFunciones.removeIf(pf -> pf.getCentroTrabajo().equals(centroTrabajo));
    }

	@Override
	public String toString() {
		return "Operario [id=" + id + ", cia=" + cia + ", co=" + co +  
				", usuarioCrea=" + usuarioCrea + ", fechaCreacion=" + fechaCreacion + ", IdCentroT="
				+ idCentroT + ", fechaConfig=" + fechaConfig + ", idTurno=" + idTurno + ", isActivo=" + isActivo
				+ ", idEstadoProceso=" + idEstadoProceso + ", usuarioEdita=" + usuarioEdita + ", fechaEdicion="
				+ fechaEdicion + ", nombre=" + nombre + "]";
	}

}
