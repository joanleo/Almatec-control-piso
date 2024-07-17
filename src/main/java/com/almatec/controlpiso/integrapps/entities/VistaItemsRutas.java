package com.almatec.controlpiso.integrapps.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "view_items_rutas")
public class VistaItemsRutas {

	@EmbeddedId
	private VistaItemsRutasId id;
	@Column(name = "item_id")
	private Long idItem;
	
	private String tipoOp;
	private Integer numOp;
	private String cliente;
	private String proyecto;
	private Integer itemFabId;
	private String descripcionConjunto;
	private Integer cantOp;
	//@Column(name = "C_centrotrabajo_id")
	//private Integer idCentroTrabajoConjunto;
	private String centroTrabajoConjunto;
	private Integer idPerfil;
	private String descripcionPerfil;
	private Integer cantListaMateriales;
	// private Integer idCentroTrabajoPerfil;
	private String centroTrabajoPerfil;
	@Column(name = "valor_aplicar")
	private BigDecimal longitud;
	@Column(name = "item_peso_b")
	private BigDecimal pesoConjunto;
	@Column(name = "item_perf_peso")
	private BigDecimal pesoPerfil;
	@Column(name = "Fecha_contractual")
	private Date fechaContraActual;
	private String pintura;
	@Column(name = "Esq_Pintura")
	private String esquemaPintura;
	private Integer prioridad;
	

	public VistaItemsRutas() {
		super();
	}

	public VistaItemsRutasId getId() {
		return id;
	}

	public Integer getCodErp() {
		return this.id.getCodErp();
	}

	public String getTipoOp() {
		return tipoOp;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public String getCliente() {
		return cliente;
	}

	public String getProyecto() {
		return proyecto;
	}

	public Integer getItemFabId() {
		return itemFabId;
	}

	public String getDescripcionConjunto() {
		return descripcionConjunto;
	}

	public Integer getCantOp() {
		return cantOp;
	}

	public Integer getIdCentroTrabajoConjunto() {
		return this.id.getIdCentroTrabajoConjunto();
	}

	public String getCentroTrabajoConjunto() {
		return centroTrabajoConjunto;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public String getDescripcionPerfil() {
		return descripcionPerfil;
	}

	public Integer getCantListaMateriales() {
		return cantListaMateriales;
	}

	public String getCentroTrabajoPerfil() {
		return centroTrabajoPerfil;
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public BigDecimal getPesoConjunto() {
		return pesoConjunto;
	}

	public BigDecimal getPesoPerfil() {
		return pesoPerfil;
	}

	public Date getFechaContraActual() {
		return fechaContraActual;
	}

	public String getEsquemaPintura() {
		return esquemaPintura;
	}

	public String getPintura() {
		return pintura;
	}

	public void setPintura(String pintura) {
		this.pintura = pintura;
	}

	public Integer getIdCentroTrabajoPerfil() {
		return this.id.getIdCentroTrabajoPerfil();
	}
	
	public Long getIdItem() {
		return idItem;
	}

	/*public Integer getPrioridad() {
		return prioridad;
	}*/

	@Override
	public int hashCode() {
		return Objects.hash(cantListaMateriales, cantOp, centroTrabajoConjunto, centroTrabajoPerfil, cliente,
				descripcionConjunto, descripcionPerfil, esquemaPintura, fechaContraActual, id, idItem, idPerfil,
				itemFabId, longitud, numOp, pesoConjunto, pesoPerfil, pintura, prioridad, proyecto, tipoOp);
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
				&& Objects.equals(cliente, other.cliente)
				&& Objects.equals(descripcionConjunto, other.descripcionConjunto)
				&& Objects.equals(descripcionPerfil, other.descripcionPerfil)
				&& Objects.equals(esquemaPintura, other.esquemaPintura)
				&& Objects.equals(fechaContraActual, other.fechaContraActual) && Objects.equals(id, other.id)
				&& Objects.equals(idItem, other.idItem) && Objects.equals(idPerfil, other.idPerfil)
				&& Objects.equals(itemFabId, other.itemFabId) && Objects.equals(longitud, other.longitud)
				&& Objects.equals(numOp, other.numOp) && Objects.equals(pesoConjunto, other.pesoConjunto)
				&& Objects.equals(pesoPerfil, other.pesoPerfil) && Objects.equals(pintura, other.pintura)
				&& Objects.equals(prioridad, other.prioridad) && Objects.equals(proyecto, other.proyecto)
				&& Objects.equals(tipoOp, other.tipoOp);
	}

	@Override
	public String toString() {
		return "VistaItemsRutas [id=" + id + ", idItem=" + idItem + ", tipoOp=" + tipoOp + ", numOp=" + numOp
				+ ", cliente=" + cliente + ", proyecto=" + proyecto + ", itemFabId=" + itemFabId
				+ ", descripcionConjunto=" + descripcionConjunto + ", cantOp=" + cantOp + ", centroTrabajoConjunto="
				+ centroTrabajoConjunto + ", idPerfil=" + idPerfil + ", descripcionPerfil=" + descripcionPerfil
				+ ", cantListaMateriales=" + cantListaMateriales + ", centroTrabajoPerfil=" + centroTrabajoPerfil
				+ ", longitud=" + longitud + ", pesoConjunto=" + pesoConjunto + ", pesoPerfil=" + pesoPerfil
				+ ", fechaContraActual=" + fechaContraActual + ", pintura=" + pintura + ", esquemaPintura="
				+ esquemaPintura +"]";
	}

	

	
}
