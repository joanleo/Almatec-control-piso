package com.almatec.controlpiso.integrapps.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VistaTiemposOperariosId implements Serializable{
	
	private static final long serialVersionUID = 4060221958148229120L;

	@Column(name = "C_proconfigproceso_id")
	private Integer idConfigProceso;
	
	@Column(name = "C_prooperario_id")
	private Integer idOperario;

	public VistaTiemposOperariosId() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idConfigProceso, idOperario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VistaTiemposOperariosId other = (VistaTiemposOperariosId) obj;
		return Objects.equals(idConfigProceso, other.idConfigProceso) && Objects.equals(idOperario, other.idOperario);
	}

	public Integer getIdConfigProceso() {
		return idConfigProceso;
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public void setIdConfigProceso(Integer idConfigProceso) {
		this.idConfigProceso = idConfigProceso;
	}

	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
	}

	@Override
	public String toString() {
		return "VistaTiemposOperariosId [idConfigProceso=" + idConfigProceso + ", idOperario=" + idOperario + "]";
	}
	
	

}
