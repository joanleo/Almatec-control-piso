package com.almatec.controlpiso.erp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t820_mf_lista_material ")
public class ListaMaterial {
	
	@Id
	@Column(name = "f820_rowid")
	private Integer rowId;
	
	@Column(name = "f820_id_metodo")
	private String metodo;
	
	@Column(name = "f820_secuencia")
	private Integer secuencia;
	
	@Column(name = "id_hijo")
	private Integer idHijo;
	
	public ListaMaterial() {
		super();
	}

	public ListaMaterial(Integer rowId, String metodo, Integer secuencia, Integer rowIdItemExtHijo) {
		super();
		this.rowId = rowId;
		this.metodo = metodo;
		this.secuencia = secuencia;
		this.idHijo = rowIdItemExtHijo;
	}

	public Integer getRowId() {
		return rowId;
	}

	public String getMetodo() {
		return metodo;
	}

	public Integer getSecuencia() {
		return secuencia;
	}

	public Integer getIdHijo() {
		return idHijo;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}

	public void setIdHijo(Integer rowIdItemExtHijo) {
		this.idHijo = rowIdItemExtHijo;
	}

	@Override
	public String toString() {
		return "ListaMaterial [rowId=" + rowId + ", metodo=" + metodo + ", secuencia=" + secuencia
				+ ", rowIdItemExtHijo=" + idHijo + "]";
	}
	
}
