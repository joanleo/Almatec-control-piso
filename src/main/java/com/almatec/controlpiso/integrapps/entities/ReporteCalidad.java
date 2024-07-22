package com.almatec.controlpiso.integrapps.entities;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "reporte_calidad")
public class ReporteCalidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "num_op")
    private Integer numOp;
    
    @Column(name = "zona")
    private String zona;
    
    @Column(name = "item_id")
    private Long idItem;

    @Column(name = "id_centro_trabajo")
    private Integer idCentroTrabajo;

    @Column(name = "centro_trabajo")
    private String centroTrabajo;

    @Column(name = "fecha_doc")
    private LocalDateTime fechaDoc;

    @Column(name = "proyecto")
    private String proyecto;

    @Column(name = "pedido")
    private String pedido;

    @Column(name = "item_descripcion")
    private String descripcionItem;
    
    @Column(name = "marca")
    private String marca;

    @Column(name = "cant_sol")
    private Integer cantSol;

    @Column(name = "lote")
    private String lote;

    @Column(name = "ancho")
    private Double ancho;

    @Column(name = "altura")
    private Double altura;

    @Column(name = "longitud")
    private Double longitud;

    @Column(name = "ral")
    private String ral;

    @Column(name = "pestana")
    private Boolean pestana;

    @Column(name = "aleta")
    private Boolean aleta;

    @Column(name = "perf_borde")
    private Boolean perfBorde;

    @Column(name = "perforaciones")
    private Boolean perforaciones;

    @Column(name = "troquelado")
    private Boolean troquelado;

    @Column(name = "cuadratura")
    private Boolean cuadratura;

    @Column(name = "corona")
    private Boolean corona;

    @Column(name = "flecha_h")
    private Boolean flechaH;

    @Column(name = "flecha_v")
    private Boolean flechaV;

    @Column(name = "corte")
    private Boolean corte;

    @Column(name = "punzonado")
    private Boolean punzonado;

    @Column(name = "granallado")
    private Boolean granallado;

    @Column(name = "pasa_prueba")
    private Boolean pasaPrueba;

    @Column(name = "media_1")
    private Double media1;

    @Column(name = "media_2")
    private Double media2;

    @Column(name = "media_3")
    private Double media3;

    @Column(name = "media_4")
    private Double media4;

    @Column(name = "media_5")
    private Double media5;

    @Column(name = "media_6")
    private Double media6;

    @Column(name = "media_7")
    private Double media7;

    @Column(name = "media_8")
    private Double media8;

    @Column(name = "media_9")
    private Double media9;

    @Column(name = "media_10")
    private Double media10;

    @Column(name = "media_11")
    private Double media11;

    @Column(name = "media_12")
    private Double media12;

	public ReporteCalidad() {
		super();
	}
	
	@PrePersist
	protected void onCreate() {
		LocalDateTime now = LocalDateTime.now();
		this.fechaDoc = now;
	}

	public Long getId() {
		return id;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public String getZona() {
		return zona;
	}

	public Long getIdItem() {
		return idItem;
	}

	public Integer getIdCentroTrabajo() {
		return idCentroTrabajo;
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

	public String getPedido() {
		return pedido;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setNumOp(Integer numOp) {
		this.numOp = numOp;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public void setIdCentroTrabajo(Integer idCentroTrabajo) {
		this.idCentroTrabajo = idCentroTrabajo;
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

	public void setPedido(String pedido) {
		this.pedido = pedido;
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
    
}

