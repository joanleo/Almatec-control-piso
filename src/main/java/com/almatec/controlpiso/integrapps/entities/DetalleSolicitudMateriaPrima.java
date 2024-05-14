package com.almatec.controlpiso.integrapps.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "Mp_Sol_Det")
public class DetalleSolicitudMateriaPrima {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sol_mp_det")
	private Integer id;
	@Column(name = "id_sol_mp")
	private Integer idSolicitud;
	@Column(name = "Cod_Erp")
	private Integer codigoErp = 0;
	@Column(name = "Und_Erp")
	private String undErp;
	@Column(name = "Cant_Sol")
	private Double cantSol = 0.000;
	@Column(name = "Cant_Entrega")
	private Double cantEntrega = 0.000;
	@Column(name = "Lotes_Erp")
	private String loteErp;
	@Column(name = "Estado_Item")
	private Integer idEstadoItem = 0;
	@Column(name = "Bodega_Entrega")	
	private String bodegaEntrega = "BOD";
	@Column(name = "Id_Usu_Sol")
	private Integer idUsuarioSol = 0;
	@Column(name = "Id_Usu_Erp")
	private Integer idUsuarioErp = 0;

	private Date fecha;
	
	@PrePersist
    public void prePersist() {
        if (fecha == null) {
        	fecha = new Date();
        }
	}

	public DetalleSolicitudMateriaPrima() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdSolicitud() {
		return idSolicitud;
	}

	public Integer getCodigoErp() {
		return codigoErp;
	}

	public String getUndErp() {
		return undErp;
	}

	public Double getCantSol() {
		return cantSol;
	}

	public Double getCantEntrega() {
		return cantEntrega;
	}

	public String getLoteErp() {
		return loteErp;
	}

	public Integer getIdEstadoItem() {
		return idEstadoItem;
	}

	public String getBodegaEntrega() {
		return bodegaEntrega;
	}

	public Integer getIdUsuarioSol() {
		return idUsuarioSol;
	}

	public Integer getIdUsuarioErp() {
		return idUsuarioErp;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdSolicitud(Integer idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public void setCodigoErp(Integer codigoErp) {
		this.codigoErp = codigoErp;
	}

	public void setUndErp(String undErp) {
		this.undErp = undErp;
	}

	public void setCantSol(Double cantSol) {
		this.cantSol = cantSol;
	}

	public void setCantEntrega(Double cantEntrega) {
		this.cantEntrega = cantEntrega;
	}

	public void setLoteErp(String loteErp) {
		this.loteErp = loteErp;
	}

	public void setIdEstadoItem(Integer idEstadoItem) {
		this.idEstadoItem = idEstadoItem;
	}

	public void setBodegaEntrega(String bodegaEntrega) {
		this.bodegaEntrega = bodegaEntrega;
	}

	public void setIdUsuarioSol(Integer idUsuarioSol) {
		this.idUsuarioSol = idUsuarioSol;
	}

	public void setIdUsuarioErp(Integer idUsuarioErp) {
		this.idUsuarioErp = idUsuarioErp;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "DetalleSolicitudMateriaPrima [id=" + id + ", idSolicitud=" + idSolicitud + ", codigoErp=" + codigoErp
				+ ", undErp=" + undErp + ", cantSol=" + cantSol + ", cantEntrega=" + cantEntrega + ", loteErp="
				+ loteErp + ", idEstadoItem=" + idEstadoItem + ", bodegaEntrega=" + bodegaEntrega + ", idUsuarioSol="
				+ idUsuarioSol + ", idUsuarioErp=" + idUsuarioErp + ", fecha=" + fecha + "]";
	}

	
}
