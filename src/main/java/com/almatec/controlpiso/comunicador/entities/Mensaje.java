package com.almatec.controlpiso.comunicador.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mensaje")
public class Mensaje{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_Mensaje_id")
    private Integer id;

    @Column(name = "A_asunto")
    private String asunto;

    @Column(name = "C_Remitente_id")
    private Integer remitente = 5;

    @Column(name = "A_destinatario")
    private String destinatarios;

    @Column(name = "A_mensaje")
    private String cuerpo;

    @Column(name = "C_Programacion_id")
    private Integer programacion = 3;
    
    @Column(name = "FC_Mensaje")
    private LocalDateTime fechaCreacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Integer getRemitente() {
        return remitente;
    }

    public void setRemitente(Integer remitente) {
        this.remitente = remitente;
    }

    public String getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(String destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String mensaje) {
        this.cuerpo = mensaje;
    }

    public Integer getProgramacion() {
        return programacion;
    }

    public void setProgramacion(Integer programacion) {
        this.programacion = programacion;
    }

    public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Mensaje() {
    }

    public Mensaje(String asunto, String destinatarios, String mensaje){
        this.asunto = asunto;
        this.destinatarios = destinatarios;
        this.cuerpo = mensaje;
    }

	@Override
	public String toString() {
		return "Mensaje [asunto=" + asunto + ", remitente=" + remitente + ", destinatarios=" + destinatarios
				+ ", mensaje=" + cuerpo + ", programacion=" + programacion + "]";
	}
 }
