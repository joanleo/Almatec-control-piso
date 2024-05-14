package com.almatec.controlpiso.integrapps.dtos;

import com.almatec.controlpiso.integrapps.interfaces.DetalleSolicDesItemInterface;

public class DetalleSolicitudDTO {

	private Integer id;
	private Integer idSol;
	private Integer codigoErp;
	private String undErp;
	private Double cantSol;
	private Double cantEntregada;
	private String loteErp;
	private Integer estado;
	private String bodegaEntrega;
	private Integer idUsuarioSol;
	private Integer idUsuarioErp;
	private String fecha;
	private String descripcionItem;
	private String bodegaOrigen;
	private String bodegaDestino;
	
	public DetalleSolicitudDTO() {
		super();
	}

	public DetalleSolicitudDTO(DetalleSolicDesItemInterface item) {
		this.id = item.getid_sol_mp_det();
		this.idSol = item.getid_sol_mp();
		this.codigoErp = item.getCod_Erp();
		this.undErp = item.getUnd_Erp();
		this.cantSol = item.getCant_Sol();
		this.cantEntregada = item.getCant_Entrega();
		this.loteErp = item.getLotes_Erp();
		this.estado = item.getEstado_Item();
		this.bodegaEntrega = item.getBodega_Entrega();
		this.idUsuarioSol = item.getId_Usu_Sol();
		this.idUsuarioErp = item.getId_Usu_Erp();
		this.fecha = item.getfecha();
		this.descripcionItem = item.getf120_descripcion();
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdSol() {
		return idSol;
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

	public Double getCantEntregada() {
		return cantEntregada;
	}

	public String getLoteErp() {
		return loteErp;
	}

	public Integer getEstado() {
		return estado;
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

	public String getFecha() {
		return fecha;
	}

	public String getDescripcionItem() {
		return descripcionItem;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdSol(Integer idSol) {
		this.idSol = idSol;
	}

	public void setCodigoErp(Integer codErp) {
		this.codigoErp = codErp;
	}

	public void setUndErp(String undErp) {
		this.undErp = undErp;
	}

	public void setCantSol(Double cantSol) {
		this.cantSol = cantSol;
	}

	public void setCantEntregada(Double cantEntregada) {
		this.cantEntregada = cantEntregada;
	}

	public void setLoteErp(String lote) {
		this.loteErp = lote;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
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

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setDescripcionItem(String descripcionItem) {
		this.descripcionItem = descripcionItem;
	}

	public String getBodegaOrigen() {
		return bodegaOrigen;
	}

	public String getBodegaDestino() {
		return bodegaDestino;
	}

	public void setBodegaOrigen(String bodegaOrigen) {
		this.bodegaOrigen = bodegaOrigen;
	}

	public void setBodegaDestino(String bodegaDestino) {
		this.bodegaDestino = bodegaDestino;
	}

	
	
}
