package com.almatec.controlpiso.ingenieria.dtos;

import java.math.BigDecimal;

public class RutaItemDTO {
    //private Integer orden;
	private Integer id;
    private String centroTrabajo;
    private BigDecimal tiempoEstandar;
    private Boolean activo;
    private String codigoCT;
    private Integer idCentroTrabajoErp;
    private Integer idCentroTrabajo;
    private String um;
    
	public RutaItemDTO() {
		super();
	}

	/*public Integer getOrden() {
		return orden;
	}*/

	public RutaItemDTO(Integer id, 
			String centroTrabajo, 
			String codigoCT, 
			BigDecimal tiempoEstandar,
			Boolean isActivo,
			Integer idCentroTrabajoErp,
			Integer idCentroTrabajo,
			String um) {
		this.id= id;
		this.centroTrabajo=centroTrabajo;
		this.codigoCT=codigoCT;
		this.tiempoEstandar=tiempoEstandar;
		this.activo=isActivo;
		this.idCentroTrabajoErp=idCentroTrabajoErp;
		this.idCentroTrabajo=idCentroTrabajo;
		this.um=um;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCentroTrabajo() {
		return centroTrabajo;
	}

	public BigDecimal getTiempoEstandar() {
		return tiempoEstandar;
	}

	/*public void setOrden(Integer orden) {
		this.orden = orden;
	}*/

	public void setCentroTrabajo(String centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

	public void setTiempoEstandar(BigDecimal tiempoEstandar) {
		this.tiempoEstandar = tiempoEstandar;
	}

	public Boolean getActivo() {
		return activo;
	}

	public String getCodigoCT() {
		return codigoCT;
	}

	public Integer getIdCentroTrabajoErp() {
		return idCentroTrabajoErp;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public void setCodigoCT(String codigoCT) {
		this.codigoCT = codigoCT;
	}

	public void setIdCentroTrabajoErp(Integer idCentroTrabajoErp) {
		this.idCentroTrabajoErp = idCentroTrabajoErp;
	}

	public Integer getIdCentroTrabajo() {
		return idCentroTrabajo;
	}

	public String getUm() {
		return um;
	}

	public void setIdCentroTrabajo(Integer idCentroTrabajo) {
		this.idCentroTrabajo = idCentroTrabajo;
	}

	public void setUm(String um) {
		this.um = um;
	}   
    
}
