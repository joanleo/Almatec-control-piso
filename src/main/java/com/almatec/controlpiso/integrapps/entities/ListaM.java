package com.almatec.controlpiso.integrapps.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Lista_MP_Ops")
public class ListaM {
	
	@Id
	@Column(name = "Id_Mp_Op")
	private Integer idListaM;
	
	@Column(name = "id_op_ia")
	private Integer idOpIntegrapps;
	
	@Column(name = "Cod_Erp")
	private Integer codigoErp;
	
	@Column(name = "Und_Erp")
	private String um;
	
	@Column(name = "Cant_Req_Ini")
	private BigDecimal cantRequeridaInicial;
	
	@Column(name = "Cant_Req_Act")
	private BigDecimal cantRequeridaActualizada;
	
	@Column(name = "Cant_Entrega")
	private BigDecimal cantEntregada;
	
	@Column(name = "Cant_Existen")
	private BigDecimal cantExistencia;
	
	@Column(name = "Peso_Unt")
	private BigDecimal pesoUnitario;
	
	@Column(name = "Id_Usu_Sol")
	private Integer idUsuario;
	
	@Column(name = "Tipo")
	private String tipoItem;
	
	@Column(name = "Fecha")
	private LocalDateTime fecha;
	
	@Column(name = "Estado")
	private Integer estado;
	
	@Column(name = "Env_LM")
	private Boolean enviado;

	public ListaM() {
		super();
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

	@Override
	public String toString() {
		return "ListaMaterial [idListaM=" + idListaM + ", idOpIntegrapps=" + idOpIntegrapps + ", codigoErp=" + codigoErp
				+ ", um=" + um + ", cantRequeridaInicial=" + cantRequeridaInicial + ", cantRequeridaActualizada="
				+ cantRequeridaActualizada + ", cantEntregada=" + cantEntregada + ", cantExistencia=" + cantExistencia
				+ ", pesoUnitario=" + pesoUnitario + ", idUsuario=" + idUsuario + ", tipoItem=" + tipoItem + ", fecha="
				+ fecha + ", estado=" + estado + ", enviado=" + enviado + "]";
	}
	
	
	


}
