package com.almatec.controlpiso.integrapps.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import com.almatec.controlpiso.integrapps.interfaces.ListaMInterface;

public class ListaMDTO {

	private Integer idListaM;	
	private Integer idOpIntegrapps;	
	private Integer codigoErp;	
	private String um;	
	private BigDecimal cantRequeridaInicial;	
	private BigDecimal cantRequeridaActualizada;	
	private BigDecimal cantEntregada;	
	private BigDecimal cantExistencia;	
	private BigDecimal pesoUnitario;	
	private Integer idUsuario;
	private String tipoItem;
	private LocalDateTime fecha;	
	private Integer estado;	
	private Boolean enviado;
	private String descripcion;
	
	public ListaMDTO() {
		super();
	}

	public ListaMDTO(ListaMInterface itemI) {
		this.cantEntregada = itemI.getCant_Entrega();
		this.cantExistencia = itemI.getCant_Existen();
		this.cantRequeridaActualizada = itemI.getCant_Req_Act();
		this.cantRequeridaInicial = itemI.getCant_Req_Ini();
		this.codigoErp = itemI.getCod_Erp();
		this.enviado = itemI.getEnv_LM();
		this.estado = itemI.getEstado();
		this.fecha = itemI.getFecha();
		this.idListaM = itemI.getId_Mp_Op();
		this.idOpIntegrapps = itemI.getid_op_ia();
		this.idUsuario = itemI.getId_Usu_Sol();
		this.pesoUnitario = itemI.getPeso_Unt();
		this.tipoItem = itemI.getTipo();
		this.um = itemI.getUnd_Erp();
		this.descripcion = itemI.getf120_descripcion();
		
	}

	public Integer getIdListaM() {
		return idListaM;
	}

	public Integer getIdOpIntegrapps() {
		return idOpIntegrapps;
	}

	public Integer getCodigoErp() {
		return codigoErp;
	}

	public String getUm() {
		return um;
	}

	public BigDecimal getCantRequeridaInicial() {
		return cantRequeridaInicial;
	}

	public BigDecimal getCantRequeridaActualizada() {
		return cantRequeridaActualizada;
	}

	public BigDecimal getCantEntregada() {
		return cantEntregada;
	}

	public BigDecimal getCantExistencia() {
		return cantExistencia;
	}

	public BigDecimal getPesoUnitario() {
		return pesoUnitario;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public String getTipoItem() {
		return tipoItem;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public Integer getEstado() {
		return estado;
	}

	public Boolean getEnviado() {
		return enviado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setIdListaM(Integer idListaM) {
		this.idListaM = idListaM;
	}

	public void setIdOpIntegrapps(Integer idOpIntegrapps) {
		this.idOpIntegrapps = idOpIntegrapps;
	}

	public void setCodigoErp(Integer codigoErp) {
		this.codigoErp = codigoErp;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public void setCantRequeridaInicial(BigDecimal cantRequeridaInicial) {
		this.cantRequeridaInicial = cantRequeridaInicial;
	}

	public void setCantRequeridaActualizada(BigDecimal cantRequeridaActualizada) {
		this.cantRequeridaActualizada = cantRequeridaActualizada;
	}

	public void setCantEntregada(BigDecimal cantEntregada) {
		this.cantEntregada = cantEntregada;
	}

	public void setCantExistencia(BigDecimal cantExistencia) {
		this.cantExistencia = cantExistencia;
	}

	public void setPesoUnitario(BigDecimal pesoUnitario) {
		this.pesoUnitario = pesoUnitario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public void setEnviado(Boolean enviado) {
		this.enviado = enviado;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "ListaMDTO [idListaM=" + idListaM + ", idOpIntegrapps=" + idOpIntegrapps + ", codigoErp=" + codigoErp
				+ ", um=" + um + ", cantRequeridaInicial=" + cantRequeridaInicial + ", cantRequeridaActualizada="
				+ cantRequeridaActualizada + ", cantEntregada=" + cantEntregada + ", cantExistencia=" + cantExistencia
				+ ", pesoUnitario=" + pesoUnitario + ", idUsuario=" + idUsuario + ", tipoItem=" + tipoItem + ", fecha="
				+ fecha + ", estado=" + estado + ", enviado=" + enviado + ", descripcion=" + descripcion + "]";
	}
}
