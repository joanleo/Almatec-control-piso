package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.almatec.controlpiso.integrapps.interfaces.SolicitudUsuarioInterface;

@Entity
@Table(name = "Mp_Sol")
public class SolicitudMateriaPrima {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sol_mp")
	private Integer id;
	@Column(name = "cia")
	private Integer idCia = 22;
	@Column(name = "Bodega_Erp")
	private String bodegaErp = "BOD";
	@Column(name = "Tipo_doc")
	private String tipoDoc = "ST";
	@Column(name = "Num_doc")
	private Integer numDoc = 0;
	@Column(name = "Fecha_Doc")
	private Date fechaDoc;
	@Column(name = "Estado_Doc")
	private Integer idEstado = 0;
	@Column(name = "id_op_ia")
	private Integer idOp = 0;
	@Column(name = "Tipo_Op")
	private String tipoOp = "OP";
	@Column(name = "Num_Op")
	private Integer numOp = 0;
	@Column(name = "Id_Usu_Sol")
	private Integer idUsuarioSol = 0;
	@Column(name = "Id_Usu_Aprueba")
	private Integer idUsuarioAprueba = 0;
	@Column(name = "Tipo_doc_Erp")
	private String tipoDocErp = "TRA";
	@Column(name = "Num_Doc_Erp")
	private Integer numDocErp = 0;
	@Column(name = "Fecha_Doc_Erp")
	private LocalDateTime fechaDocEp;
	@Column(name = "Barcode")
	private String barcode;
	
	@PrePersist
    public void prePersist() {
        if (fechaDoc == null) {
            fechaDoc = new Date();
        }
	}
	
	public SolicitudMateriaPrima() {
		super();
	}

	public SolicitudMateriaPrima(SolicitudUsuarioInterface sol) {
		this.id = sol.getid_sol_mp();
		this.idCia = sol.getcia();
		this.bodegaErp = sol.getBodega_Erp();
		this.tipoDoc = sol.getTipo_doc();
		this.numDoc = sol.getNum_doc();
		this.fechaDoc = sol.getFecha_Doc();
		this.idEstado = sol.getEstado_Doc();
		this.idOp = sol.getid_op_ia();
		this.tipoOp = sol.getTipo_Op();
		this.numOp = sol.getNum_Op();
		this.idUsuarioSol = sol.getId_Usu_Sol();
		this.idUsuarioAprueba = sol.getId_Usu_Aprueba();
		this.tipoDocErp = sol.getTipo_doc_Erp();
		this.numDocErp = sol.getNum_Doc_Erp();
		this.fechaDocEp = sol.getFecha_Doc_Erp();
		this.barcode = sol.getBarcode();
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdCia() {
		return idCia;
	}

	public String getBodegaErp() {
		return bodegaErp;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public Integer getNumDoc() {
		return numDoc;
	}

	public Date getFechaDoc() {
		return fechaDoc;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public Integer getIdOp() {
		return idOp;
	}

	public String getTipoOp() {
		return tipoOp;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public Integer getIdUsuarioSol() {
		return idUsuarioSol;
	}

	public Integer getIdUsuarioAprueba() {
		return idUsuarioAprueba;
	}

	public String getTipoDocErp() {
		return tipoDocErp;
	}

	public Integer getNumDocErp() {
		return numDocErp;
	}

	public LocalDateTime getFechaDocEp() {
		return fechaDocEp;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdCia(Integer idCia) {
		this.idCia = idCia;
	}

	public void setBodegaErp(String bodegaErp) {
		this.bodegaErp = bodegaErp;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public void setNumDoc(Integer numDoc) {
		this.numDoc = numDoc;
	}

	public void setFechaDoc(Date fechaDoc) {
		this.fechaDoc = fechaDoc;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public void setIdOp(Integer idOp) {
		this.idOp = idOp;
	}

	public void setTipoOp(String tipoOp) {
		this.tipoOp = tipoOp;
	}

	public void setNumOp(Integer numOp) {
		this.numOp = numOp;
	}

	public void setIdUsuarioSol(Integer idUsuarioSol) {
		this.idUsuarioSol = idUsuarioSol;
	}

	public void setIdUsuarioAprueba(Integer idUsuarioErp) {
		this.idUsuarioAprueba = idUsuarioErp;
	}

	public void setTipoDocErp(String tipoDocErp) {
		this.tipoDocErp = tipoDocErp;
	}

	public void setNumDocErp(Integer numDocErp) {
		this.numDocErp = numDocErp;
	}

	public void setFechaDocEp(LocalDateTime fechaDocEp) {
		this.fechaDocEp = fechaDocEp;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Override
	public String toString() {
		return "SolicitudMateriaPrima [id=" + id + ", idCia=" + idCia + ", bodegaErp=" + bodegaErp + ", tipoDoc="
				+ tipoDoc + ", numDoc=" + numDoc + ", fechaDoc=" + fechaDoc + ", idEstado=" + idEstado + ", idOp="
				+ idOp + ", tipoOp=" + tipoOp + ", numOp=" + numOp + ", idUsuarioSol=" + idUsuarioSol
				+ ", idUsuarioAprueba=" + idUsuarioAprueba + ", tipoDocErp=" + tipoDocErp + ", numDocErp=" + numDocErp
				+ ", fechaDocEp=" + fechaDocEp + ", barcode=" + barcode + "]";
	}
	
	
	

}
