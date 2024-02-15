package com.almatec.controlpiso.integrapps.entities;

import java.math.BigDecimal;
import java.util.Objects;

import javax.annotation.concurrent.Immutable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Immutable
@Entity
@Table(name = "view_items_rutas")
public class VistaItemsRutas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codErp;
	private Integer idOp;
	private String tipoOp;
	private Integer numOp;
	private String cliente;
	private String proyecto;
	private Integer itemFabId;
	private String descripcionConjunto;
	private Integer cantOp;
	@Column(name = "C_centrotrabajo_id")
	private Integer idCentroTrabajoConjunto;
	private String centroTrabajoConjunto;
	private Integer idPerfil;
	private String descripcionPerfil;
	private String cantListaMateriales;
	private Integer idCentroTrabajoPerfil;
	private String centroTrabajoPerfil;
	@Column(name = "valor_aplicar")
	private BigDecimal longitud;
	
	public VistaItemsRutas() {
		super();
	}

	public Integer getIdOp() {
		return idOp;
	}

	public void setIdOp(Integer idOp) {
		this.idOp = idOp;
	}

	public String getTipoOp() {
		return tipoOp;
	}

	public void setTipoOp(String tipoOp) {
		this.tipoOp = tipoOp;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public void setNumOp(Integer numOp) {
		this.numOp = numOp;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public Integer getItemFabId() {
		return itemFabId;
	}

	public void setItemFabId(Integer itemFabId) {
		this.itemFabId = itemFabId;
	}

	public String getDescripcionConjunto() {
		return descripcionConjunto;
	}

	public void setDescripcionConjunto(String descripcionConjunto) {
		this.descripcionConjunto = descripcionConjunto;
	}

	public Integer getCantOp() {
		return cantOp;
	}

	public void setCantOp(Integer cantOp) {
		this.cantOp = cantOp;
	}

	public Integer getIdCentroTrabajoConjunto() {
		return idCentroTrabajoConjunto;
	}

	public void setIdCentroTrabajoConjunto(Integer idCentroTrabajoConjunto) {
		this.idCentroTrabajoConjunto = idCentroTrabajoConjunto;
	}

	public String getCentroTrabajoConjunto() {
		return centroTrabajoConjunto;
	}

	public void setCentroTrabajoConjunto(String centroTrabajoConjunto) {
		this.centroTrabajoConjunto = centroTrabajoConjunto;
	}

	public Integer getCodErp() {
		return codErp;
	}

	public void setCodErp(Integer codErp) {
		this.codErp = codErp;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getDescripcionPerfil() {
		return descripcionPerfil;
	}

	public void setDescripcionPerfil(String descripcionPerfil) {
		this.descripcionPerfil = descripcionPerfil;
	}

	public String getCantListaMateriales() {
		return cantListaMateriales;
	}

	public void setCantListaMateriales(String cantListaMateriales) {
		this.cantListaMateriales = cantListaMateriales;
	}

	public Integer getIdCentroTrabajoPerfil() {
		return idCentroTrabajoPerfil;
	}

	public void setIdCentroTrabajoPerfil(Integer idCentroTrabajoPerfil) {
		this.idCentroTrabajoPerfil = idCentroTrabajoPerfil;
	}

	public String getCentroTrabajoPerfil() {
		return centroTrabajoPerfil;
	}

	public void setCentroTrabajoPerfil(String centroTrabajoPerfil) {
		this.centroTrabajoPerfil = centroTrabajoPerfil;
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantListaMateriales, cantOp, centroTrabajoConjunto, centroTrabajoPerfil, cliente, codErp,
				descripcionConjunto, descripcionPerfil, idCentroTrabajoConjunto, idCentroTrabajoPerfil, idOp, idPerfil,
				itemFabId, longitud, numOp, proyecto, tipoOp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VistaItemsRutas other = (VistaItemsRutas) obj;
		return Objects.equals(cantListaMateriales, other.cantListaMateriales) && Objects.equals(cantOp, other.cantOp)
				&& Objects.equals(centroTrabajoConjunto, other.centroTrabajoConjunto)
				&& Objects.equals(centroTrabajoPerfil, other.centroTrabajoPerfil)
				&& Objects.equals(cliente, other.cliente) && Objects.equals(codErp, other.codErp)
				&& Objects.equals(descripcionConjunto, other.descripcionConjunto)
				&& Objects.equals(descripcionPerfil, other.descripcionPerfil)
				&& Objects.equals(idCentroTrabajoConjunto, other.idCentroTrabajoConjunto)
				&& Objects.equals(idCentroTrabajoPerfil, other.idCentroTrabajoPerfil)
				&& Objects.equals(idOp, other.idOp) && Objects.equals(idPerfil, other.idPerfil)
				&& Objects.equals(itemFabId, other.itemFabId) && Objects.equals(longitud, other.longitud)
				&& Objects.equals(numOp, other.numOp) && Objects.equals(proyecto, other.proyecto)
				&& Objects.equals(tipoOp, other.tipoOp);
	}

	@Override
	public String toString() {
		return "VistaItemsRutas [codErp=" + codErp + ", idOp=" + idOp + ", tipoOp=" + tipoOp + ", numOp=" + numOp
				+ ", cliente=" + cliente + ", proyecto=" + proyecto + ", itemFabId=" + itemFabId
				+ ", descripcionConjunto=" + descripcionConjunto + ", cantOp=" + cantOp + ", idCentroTrabajoConjunto="
				+ idCentroTrabajoConjunto + ", centroTrabajoConjunto=" + centroTrabajoConjunto + ", idPerfil="
				+ idPerfil + ", descripcionPerfil=" + descripcionPerfil + ", cantListaMateriales=" + cantListaMateriales
				+ ", idCentroTrabajoPerfil=" + idCentroTrabajoPerfil + ", centroTrabajoPerfil=" + centroTrabajoPerfil
				+ ", longitud=" + longitud + "]";
	}	

}
