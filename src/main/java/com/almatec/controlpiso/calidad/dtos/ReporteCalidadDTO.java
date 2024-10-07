package com.almatec.controlpiso.calidad.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class ReporteCalidadDTO {
	private Long id;
	private String prefijo = "FORCAL";
	private Integer idOperario;
	private String nombreOperario;
	private Integer numOp;
	private String zona;
	private Long idItem;
	private String color;
	private Integer idCentroTrabajo;
    private String centroTrabajo;
    private LocalDateTime fechaDoc;
    private String proyecto;
    private String descripcionItem;
    private String marca;
    private Integer cantSol;
    private String lote;
    
    private Double ancho;
    private Double altura;
    private Double longitud;
    private String ral;
    
    private Boolean pestana;
    private Boolean aleta;
    private Boolean perfBorde;
    private Boolean perforaciones;
    private Boolean troquelado;
    private Boolean cuadratura;
    private Boolean corona;
    private Boolean flechaH;
    private Boolean flechaV;
    private Boolean corte;
    private Boolean punzonado;
    private Boolean granallado;
    private Boolean pasaPrueba;
    
    private Double media1;
    private Double media2;
    private Double media3;
    private Double media4;
    private Double media5;
    private Double media6;
    private Double media7;
    private Double media8;
    private Double media9;
    private Double media10;
    private Double media11;
    private Double media12;
    
	public ReporteCalidadDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumOp() {
		return numOp;
	}
	
	public void setNumOp(Integer numOp) {
		this.numOp = numOp;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getIdCentroTrabajo() {
		return idCentroTrabajo;
	}

	public void setIdCentroTrabajo(Integer idCentroTrabajo) {
		this.idCentroTrabajo = idCentroTrabajo;
	}

	public String getCentroTrabajo() {
		return centroTrabajo;
	}

	public LocalDateTime getFechaDoc() {
		return fechaDoc;
	}

	public String getProyecto() {
		return proyecto;
	}

	public String getDescripcionItem() {
		return descripcionItem;
	}

	public String getMarca() {
		return marca;
	}

	public Integer getCantSol() {
		return cantSol;
	}

	public String getLote() {
		return lote;
	}

	public Double getAncho() {
		return ancho;
	}

	public Double getAltura() {
		return altura;
	}

	public Double getLongitud() {
		return longitud;
	}

	public String getRal() {
		return ral;
	}

	public Boolean getPestana() {
		return pestana;
	}

	public Boolean getAleta() {
		return aleta;
	}

	public Boolean getPerfBorde() {
		return perfBorde;
	}

	public Boolean getPerforaciones() {
		return perforaciones;
	}

	public Boolean getTroquelado() {
		return troquelado;
	}

	public Boolean getCuadratura() {
		return cuadratura;
	}

	public Boolean getCorona() {
		return corona;
	}

	public Boolean getFlechaH() {
		return flechaH;
	}

	public Boolean getFlechaV() {
		return flechaV;
	}

	public Boolean getCorte() {
		return corte;
	}

	public Boolean getPunzonado() {
		return punzonado;
	}

	public Boolean getGranallado() {
		return granallado;
	}

	public Boolean getPasaPrueba() {
		return pasaPrueba;
	}

	public Double getMedia1() {
		return media1;
	}

	public Double getMedia2() {
		return media2;
	}

	public Double getMedia3() {
		return media3;
	}

	public Double getMedia4() {
		return media4;
	}

	public Double getMedia5() {
		return media5;
	}

	public Double getMedia6() {
		return media6;
	}

	public Double getMedia7() {
		return media7;
	}

	public Double getMedia8() {
		return media8;
	}

	public Double getMedia9() {
		return media9;
	}

	public Double getMedia10() {
		return media10;
	}

	public Double getMedia11() {
		return media11;
	}

	public Double getMedia12() {
		return media12;
	}

	public void setCentroTrabajo(String centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

	public void setFechaDoc(LocalDateTime fechaDoc) {
		this.fechaDoc = fechaDoc;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public void setDescripcionItem(String ref) {
		this.descripcionItem = ref;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setCantSol(Integer cantSol) {
		this.cantSol = cantSol;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public void setRal(String ral) {
		this.ral = ral;
	}

	public void setPestana(Boolean pestana) {
		this.pestana = pestana;
	}

	public void setAleta(Boolean aleta) {
		this.aleta = aleta;
	}

	public void setPerfBorde(Boolean perfBorde) {
		this.perfBorde = perfBorde;
	}

	public void setPerforaciones(Boolean perforaciones) {
		this.perforaciones = perforaciones;
	}

	public void setTroquelado(Boolean troquelado) {
		this.troquelado = troquelado;
	}

	public void setCuadratura(Boolean cuadratura) {
		this.cuadratura = cuadratura;
	}

	public void setCorona(Boolean corona) {
		this.corona = corona;
	}

	public void setFlechaH(Boolean flechaH) {
		this.flechaH = flechaH;
	}

	public void setFlechaV(Boolean flechaV) {
		this.flechaV = flechaV;
	}

	public void setCorte(Boolean corte) {
		this.corte = corte;
	}

	public void setPunzonado(Boolean punzonado) {
		this.punzonado = punzonado;
	}

	public void setGranallado(Boolean granallado) {
		this.granallado = granallado;
	}

	public void setPasaPrueba(Boolean pasaPrueba) {
		this.pasaPrueba = pasaPrueba;
	}

	public void setMedia1(Double media1) {
		this.media1 = media1;
	}

	public void setMedia2(Double media2) {
		this.media2 = media2;
	}

	public void setMedia3(Double media3) {
		this.media3 = media3;
	}

	public void setMedia4(Double media4) {
		this.media4 = media4;
	}

	public void setMedia5(Double media5) {
		this.media5 = media5;
	}

	public void setMedia6(Double media6) {
		this.media6 = media6;
	}

	public void setMedia7(Double media7) {
		this.media7 = media7;
	}

	public void setMedia8(Double media8) {
		this.media8 = media8;
	}

	public void setMedia9(Double media9) {
		this.media9 = media9;
	}

	public void setMedia10(Double media10) {
		this.media10 = media10;
	}

	public void setMedia11(Double media11) {
		this.media11 = media11;
	}

	public void setMedia12(Double media12) {
		this.media12 = media12;
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public String getNombreOperario() {
		return nombreOperario;
	}

	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
	}

	public void setNombreOperario(String nombreOperario) {
		this.nombreOperario = nombreOperario;
	}

	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	@Override
	public String toString() {
		return "ReporteCalidadDTO [id=" + id + ", prefijo=" + prefijo + ", idOperario=" + idOperario
				+ ", nombreOperario=" + nombreOperario + ", numOp=" + numOp + ", zona=" + zona + ", idItem=" + idItem
				+ ", color=" + color + ", idCentroTrabajo=" + idCentroTrabajo + ", centroTrabajo=" + centroTrabajo
				+ ", fechaDoc=" + fechaDoc + ", proyecto=" + proyecto + ", descripcionItem="
				+ descripcionItem + ", marca=" + marca + ", cantSol=" + cantSol + ", lote=" + lote + ", ancho=" + ancho
				+ ", altura=" + altura + ", longitud=" + longitud + ", ral=" + ral + ", pestana=" + pestana + ", aleta="
				+ aleta + ", perfBorde=" + perfBorde + ", perforaciones=" + perforaciones + ", troquelado=" + troquelado
				+ ", cuadratura=" + cuadratura + ", corona=" + corona + ", flechaH=" + flechaH + ", flechaV=" + flechaV
				+ ", corte=" + corte + ", punzonado=" + punzonado + ", granallado=" + granallado + ", pasaPrueba="
				+ pasaPrueba + ", media1=" + media1 + ", media2=" + media2 + ", media3=" + media3 + ", media4=" + media4
				+ ", media5=" + media5 + ", media6=" + media6 + ", media7=" + media7 + ", media8=" + media8
				+ ", media9=" + media9 + ", media10=" + media10 + ", media11=" + media11 + ", media12=" + media12 + "]";
	}
    
}
