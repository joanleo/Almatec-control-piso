package com.almatec.controlpiso.integrapps.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Items_rutas")
	public class RutaItem {
		
		@Id
		@Column(name = "id_item_ruta")
		private Integer idRuta;
		
		@Column(name = "Item_fab_Id")
		private Integer idItem;
		
		@Column(name = "C_centrotrabajo_id")
		private Integer IdCentroTrabajo;
		
		@Column(name = "valor_aplicar")
		private BigDecimal valor;
		
		@Column(name = "tiempo_estandar")
		private BigDecimal tiempoStd;
		
		@Column(name = "activo")
		private Boolean isActivo;
	
		public RutaItem() {
			super();
		}

	public Integer getIdRuta() {
		return idRuta;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public Integer getIdCentroTrabajo() {
		return IdCentroTrabajo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public BigDecimal getTiempoStd() {
		return tiempoStd;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIdRuta(Integer idRuta) {
		this.idRuta = idRuta;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public void setIdCentroTrabajo(Integer idCentroTrabajo) {
		IdCentroTrabajo = idCentroTrabajo;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setTiempoStd(BigDecimal tiempoStd) {
		this.tiempoStd = tiempoStd;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	@Override
	public String toString() {
		return "RutaItem [idRuta=" + idRuta + ", idItem=" + idItem + ", IdCentroTrabajo=" + IdCentroTrabajo + ", valor="
				+ valor + ", tiempoStd=" + tiempoStd + ", isActivo=" + isActivo + "]";
	}
	
	
	
	


}
