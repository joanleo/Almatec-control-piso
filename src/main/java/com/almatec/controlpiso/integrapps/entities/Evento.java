package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "Eventos_Erp")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_Evento")
	private Integer id;
	
	@Column(name = "Evento_Tipo")
	private String tipoEvento="EVENTO";
	
	@Column(name = "Evento_Param1")
	private String param1="0";
	
	@Column(name = "Evento_Param2")
	private String param2="0";
	
	@Column(name = "Evento_Param3")
	private String param3="0";
	
	@Column(name = "Evento_Param4")
	private Integer param4=0;
	
	@Column(name = "Evento_Param5")
	private Integer param5=0;
	
	@Column(name = "Evento_Param6")
	private Integer param6=0;
	
	@Column(name = "Evento_Estado")
	private Integer estado=0;
	
	@Column(name = "Fecha_Crea")
	private LocalDateTime fechaCreacion;
	
	@Column(name = "Evento_Fecha_Eje")
	private LocalDateTime fechaEjecucion;
	
	@Column(name = "Evento_pruebas")
	private Integer	prueba=0;
		
	@Column(name = "evento_resp_ws")
	private String respuestaWeb;
	
	@Column(name = "Evento_SC_A_OC")
	private Integer scAoc=0;
	
	@Column(name = "Evento_Act_Usr")
	private boolean acAusr=false;
	
	@Column(name = "Evento_Temp")
	private String temp="0";
	
	@Column(name = "Evento_documento")
	private String documento="0";

	public Evento() {
		super();
	}
	
	@PrePersist
    public void prePersist() {
		fechaCreacion = LocalDateTime.now();
    }

	public Integer getId() {
		return id;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public String getParam1() {
		return param1;
	}

	public String getParam2() {
		return param2;
	}

	public String getParam3() {
		return param3;
	}

	public Integer getParam4() {
		return param4;
	}

	public Integer getParam5() {
		return param5;
	}

	public Integer getParam6() {
		return param6;
	}

	public Integer getEstado() {
		return estado;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public LocalDateTime getFechaEjecucion() {
		return fechaEjecucion;
	}

	public Integer getPrueba() {
		return prueba;
	}

	public String getRespuestaWeb() {
		return respuestaWeb;
	}

	public Integer getScAoc() {
		return scAoc;
	}

	public boolean isAcAusr() {
		return acAusr;
	}

	public String getTemp() {
		return temp;
	}

	public String getDocumento() {
		return documento;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}

	public void setParam4(Integer param4) {
		this.param4 = param4;
	}

	public void setParam5(Integer param5) {
		this.param5 = param5;
	}

	public void setParam6(Integer param6) {
		this.param6 = param6;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public void setFechaEjecucion(LocalDateTime fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	public void setPrueba(Integer prueba) {
		this.prueba = prueba;
	}

	public void setRespuestaWeb(String respuestaWeb) {
		this.respuestaWeb = respuestaWeb;
	}

	public void setScAoc(Integer scAoc) {
		this.scAoc = scAoc;
	}

	public void setAcAusr(boolean acAusr) {
		this.acAusr = acAusr;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	
	

}
