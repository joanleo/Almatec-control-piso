package com.almatec.controlpiso.integrapps.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VistaItemsRutasId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "C_centrotrabajo_id")
    private Integer idCentroTrabajoConjunto;
    private Integer idCentroTrabajoPerfil;
    private Integer codErp;
    private Integer idOp;


    public Integer getIdCentroTrabajoConjunto() {
		return idCentroTrabajoConjunto;
	}

	public Integer getIdCentroTrabajoPerfil() {
		return idCentroTrabajoPerfil;
	}

	public Integer getCodErp() {
		return codErp;
	}

	public Integer getIdOp() {
		return idOp;
	}

	@Override
	public String toString() {
		return "VistaItemsRutasId [idCentroTrabajoConjunto=" + idCentroTrabajoConjunto + ", idCentroTrabajoPerfil="
				+ idCentroTrabajoPerfil + ", codErp=" + codErp + ", idOp=" + idOp + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codErp, idCentroTrabajoConjunto, idCentroTrabajoPerfil, idOp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VistaItemsRutasId other = (VistaItemsRutasId) obj;
		return Objects.equals(codErp, other.codErp)
				&& Objects.equals(idCentroTrabajoConjunto, other.idCentroTrabajoConjunto)
				&& Objects.equals(idCentroTrabajoPerfil, other.idCentroTrabajoPerfil)
				&& Objects.equals(idOp, other.idOp);
	}
}
