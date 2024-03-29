package com.almatec.controlpiso.integrapps.dtos;

import java.math.BigDecimal;
import java.util.Objects;

public class PiezaCTProcesoDTO {
	
	private String cliente;
	private String proyecto;
	private Boolean isActive;
	private Integer idOperario;
	private Integer idConfigProceso;
	private Boolean isPerfil;
	private Integer idPerfil;
	private String descripcion;
	private BigDecimal peso;
	private Integer cant;
	private Long idItem;
	private Integer idItemFab;
	private String tipoOp;
	private Integer numOp;
	private BigDecimal tiempoStd;
	private Integer tiempoTrancurrido;
	private Integer tiempoReproceso;
	private String nombreOperario;
	
	public PiezaCTProcesoDTO() {
		super();
	}

	public String getCliente() {
		return cliente;
	}

	public String getProyecto() {
		return proyecto;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getCant() {
		return cant;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public Long getIdItem() {
		return idItem;
	}

	public Integer getIdItemFab() {
		return idItemFab;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public Integer getIdConfigProceso() {
		return idConfigProceso;
	}

	public Boolean getIsPerfil() {
		return isPerfil;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCant(Integer cant) {
		this.cant = cant;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public void setIdItemFab(Integer idItemFab) {
		this.idItemFab = idItemFab;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
	}

	public void setIdConfigProceso(Integer idConfigProceso) {
		this.idConfigProceso = idConfigProceso;
	}

	public void setIsPerfil(Boolean isPerfil) {
		this.isPerfil = isPerfil;
	}

	public String getTipoOp() {
		return tipoOp;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public void setTipoOp(String tipoOp) {
		this.tipoOp = tipoOp;
	}

	public void setNumOp(Integer numOp) {
		this.numOp = numOp;
	}

	public BigDecimal getTiempoStd() {
		return tiempoStd;
	}

	public Integer getTiempoTrancurrido() {
		return tiempoTrancurrido;
	}

	public Integer getTiempoReproceso() {
		return tiempoReproceso;
	}

	public void setTiempoStd(BigDecimal tiempoStdItem) {
		this.tiempoStd = tiempoStdItem;
	}

	public void setTiempoTrancurrido(Integer tiempoTrancurrido) {
		this.tiempoTrancurrido = tiempoTrancurrido;
	}

	public void setTiempoReproceso(Integer tiempoReproceso) {
		this.tiempoReproceso = tiempoReproceso;
	}

	public String getNombreOperario() {
		return nombreOperario;
	}

	public void setNombreOperario(String nombreOperario) {
		this.nombreOperario = nombreOperario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cant, cliente, descripcion, idConfigProceso, idItem, idItemFab, idOperario, idPerfil,
				isActive, isPerfil, numOp, peso, proyecto, tipoOp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PiezaCTProcesoDTO other = (PiezaCTProcesoDTO) obj;
		return Objects.equals(cant, other.cant) && Objects.equals(cliente, other.cliente)
				&& Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(idConfigProceso, other.idConfigProceso) && Objects.equals(idItem, other.idItem)
				&& Objects.equals(idItemFab, other.idItemFab) && Objects.equals(idOperario, other.idOperario)
				&& Objects.equals(idPerfil, other.idPerfil) && Objects.equals(isActive, other.isActive)
				&& Objects.equals(isPerfil, other.isPerfil) && Objects.equals(numOp, other.numOp)
				&& Objects.equals(peso, other.peso) && Objects.equals(proyecto, other.proyecto)
				&& Objects.equals(tipoOp, other.tipoOp);
	}

	@Override
	public String toString() {
		return "PiezaCTProcesoDTO [cliente=" + cliente + ", proyecto=" + proyecto + ", isActive=" + isActive
				+ ", idOperario=" + idOperario + ", idConfigProceso=" + idConfigProceso + ", isPerfil=" + isPerfil
				+ ", idPerfil=" + idPerfil + ", descripcion=" + descripcion + ", peso=" + peso + ", cant=" + cant
				+ ", idItem=" + idItem + ", idItemFab=" + idItemFab + ", tipoOp=" + tipoOp + ", numOp=" + numOp
				+ ", tiempoStd=" + tiempoStd + ", tiempoTrancurrido=" + tiempoTrancurrido + ", tiempoReproceso="
				+ tiempoReproceso + ", nombreOperario=" + nombreOperario + "]";
	}
}
