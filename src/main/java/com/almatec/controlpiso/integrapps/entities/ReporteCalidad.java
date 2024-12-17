package com.almatec.controlpiso.integrapps.entities;

import javax.persistence.*;

import com.almatec.controlpiso.security.entities.Usuario;

import java.time.LocalDateTime;

@Entity
@Table(name = "reporte_calidad")
public class ReporteCalidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    @Column(name = "prefijo")
    private String prefijo = "FORCAL";    
    @Column(name = "id_operario")
    private Integer idOperario;    
    @Column(name = "nombre_operario")
	private String nombreOperario;    
    @Column(name = "num_op")
    private Integer numOp;
    @Column(name = "zona")
    private String zona;
    @Column(name = "item_op_id")
    private Long idItemOp;
    @Column(name = "id_centro_trabajo")
    private Integer idCentroTrabajo;    
    @Column(name = "centro_trabajo")
    private String centroTrabajo;
    @Column(name = "fecha_doc")
    private LocalDateTime fechaDoc;
    @Column(name = "proyecto")
    private String proyecto;
    @Column(name = "item_descripcion")
    private String descripcionItem;    
    @Column(name = "marca")
    private String marca;    
    @Column(name = "cant_sol")
    private Integer cantSol;    
    @Column(name = "lote")
    private String lote;
    @Column(name = "item_conjunto_id")
    private Integer idItem;
    @Column(name = "item_parte_id")
    private Integer idParte;
    
    
    
    @Column(name = "ancho")
    private Double ancho;    
    @Column(name = "altura")
    private Double altura;    
    @Column(name = "longitud")
    private Double longitud;    
    @Column(name = "espesor")
    private Double espesor;       
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
    
    
    @Column(name = "rebaba")
    private Boolean rebaba;
    @Column(name = "angulo_doblez")
    private Boolean anguloDoblez;
    @Column(name = "numero_doblez")
    private Boolean numDoblez;
    @Column(name = "pasa_prueba_visual")
    private Boolean pasaPruebaVisual;
    @Column(name = "diametro")
    private Boolean diametro;
    @Column(name = "pasa_prueba_adherencia")
    private Boolean pasaPruebaAdherencia;    
    

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
    
    @ManyToOne
    @JoinColumn(name = "id_usuario_aprueba")
    private Usuario usuarioAprueba;

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

	public String getPrefijo() {
		return prefijo;
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public String getNombreOperario() {
		return nombreOperario;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public String getZona() {
		return zona;
	}

	public Long getIdItemOp() {
		return idItemOp;
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

	public Integer getIdItem() {
		return idItem;
	}

	public Integer getIdParte() {
		return idParte;
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

	public Double getEspesor() {
		return espesor;
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

	public Boolean getRebaba() {
		return rebaba;
	}

	public Boolean getAnguloDoblez() {
		return anguloDoblez;
	}

	public Boolean getNumDoblez() {
		return numDoblez;
	}

	public Boolean getPasaPruebaVisual() {
		return pasaPruebaVisual;
	}

	public Boolean getDiametro() {
		return diametro;
	}

	public Boolean getPasaPruebaAdherencia() {
		return pasaPruebaAdherencia;
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

	public Usuario getUsuarioAprueba() {
		return usuarioAprueba;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
	}

	public void setNombreOperario(String nombreOperario) {
		this.nombreOperario = nombreOperario;
	}

	public void setNumOp(Integer numOp) {
		this.numOp = numOp;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public void setIdItemOp(Long idItemOp) {
		this.idItemOp = idItemOp;
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

	public void setDescripcionItem(String descripcionItem) {
		this.descripcionItem = descripcionItem;
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

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public void setIdParte(Integer idParte) {
		this.idParte = idParte;
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

	public void setEspesor(Double espesor) {
		this.espesor = espesor;
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

	public void setRebaba(Boolean rebaba) {
		this.rebaba = rebaba;
	}

	public void setAnguloDoblez(Boolean anguloDoblez) {
		this.anguloDoblez = anguloDoblez;
	}

	public void setNumDoblez(Boolean numDoblez) {
		this.numDoblez = numDoblez;
	}

	public void setPasaPruebaVisual(Boolean pasaPruebaVisual) {
		this.pasaPruebaVisual = pasaPruebaVisual;
	}

	public void setDiametro(Boolean diametro) {
		this.diametro = diametro;
	}

	public void setPasaPruebaAdherencia(Boolean pasaPruebaAdherencia) {
		this.pasaPruebaAdherencia = pasaPruebaAdherencia;
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

	public void setUsuarioAprueba(Usuario usuarioAprueba) {
		this.usuarioAprueba = usuarioAprueba;
	}

	@Override
	public String toString() {
		return "ReporteCalidad [id=" + id + ", prefijo=" + prefijo + ", idOperario=" + idOperario + ", nombreOperario="
				+ nombreOperario + ", numOp=" + numOp + ", zona=" + zona + ", idItemOp=" + idItemOp
				+ ", idCentroTrabajo=" + idCentroTrabajo + ", centroTrabajo=" + centroTrabajo + ", fechaDoc=" + fechaDoc
				+ ", proyecto=" + proyecto + ", descripcionItem=" + descripcionItem + ", marca=" + marca + ", cantSol="
				+ cantSol + ", lote=" + lote + ", idItem=" + idItem + ", idParte=" + idParte + ", ancho=" + ancho
				+ ", altura=" + altura + ", longitud=" + longitud + ", espesor=" + espesor + ", ral=" + ral
				+ ", pestana=" + pestana + ", aleta=" + aleta + ", perfBorde=" + perfBorde + ", perforaciones="
				+ perforaciones + ", troquelado=" + troquelado + ", cuadratura=" + cuadratura + ", corona=" + corona
				+ ", flechaH=" + flechaH + ", flechaV=" + flechaV + ", corte=" + corte + ", punzonado=" + punzonado
				+ ", granallado=" + granallado + ", pasaPrueba=" + pasaPrueba + ", rebaba=" + rebaba + ", anguloDoblez="
				+ anguloDoblez + ", numDoblez=" + numDoblez + ", pasaPruebaVisual=" + pasaPruebaVisual + ", diametro="
				+ diametro + ", pasaPruebaAdherencia=" + pasaPruebaAdherencia + ", media1=" + media1 + ", media2="
				+ media2 + ", media3=" + media3 + ", media4=" + media4 + ", media5=" + media5 + ", media6=" + media6
				+ ", media7=" + media7 + ", media8=" + media8 + ", media9=" + media9 + ", media10=" + media10
				+ ", media11=" + media11 + ", media12=" + media12 + ", usuarioAprueba=" + usuarioAprueba + "]";
	}
    
}

