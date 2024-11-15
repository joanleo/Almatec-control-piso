package com.almatec.controlpiso.integrapps.dtos;


import java.math.BigDecimal;

import com.almatec.controlpiso.integrapps.entities.Operario;

public class ReporteDTO {
	private String proyecto;
	private Integer numOp;
	private String ref;
	private Integer cantSol;
	private Integer cantFab;
	private String centroTrabajo;
	private Operario operario;
	private Integer idCentroTrabajo;
	private Integer idItemFab;
	private Integer idParte;
	private Long idItem;
	private Integer cantReportar;
	private String lote;
	private String color;
	private Integer idConfigProceso;
	private BigDecimal peso = new BigDecimal(1);
	private BigDecimal pesoPintura = BigDecimal.ZERO;
	
	public String getProyecto() {
		return proyecto;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public String getRef() {
		return ref;
	}

	public Integer getCantSol() {
		return cantSol;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public void setNumOp(Integer numOp) {
		this.numOp = numOp;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public void setCantSol(Integer cant) {
		this.cantSol = cant;
	}

	public Integer getCantFab() {
		return cantFab;
	}

	public void setCantFab(Integer cantFab) {
		this.cantFab = cantFab;
	}

	public String getCentroTrabajo() {
		return centroTrabajo;
	}

	public void setCentroTrabajo(String centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

	public Operario getOperario() {
		return operario;
	}

	public void setOperario(Operario operario) {
		this.operario = operario;
	}

	public ReporteDTO() {
		super();
	}

	public Integer getIdCentroTrabajo() {
		return idCentroTrabajo;
	}

	public void setIdCentroTrabajo(Integer idCentroTrabajo) {
		this.idCentroTrabajo = idCentroTrabajo;
	}

	public Integer getIdItemFab() {
		return idItemFab;
	}

	public void setIdItemFab(Integer idItemFab) {
		this.idItemFab = idItemFab;
	}

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public Integer getCantReportar() {
		return cantReportar;
	}

	public void setCantReportar(Integer cantReportar) {
		this.cantReportar = cantReportar;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public Integer getIdParte() {
		return idParte;
	}

	public void setIdParte(Integer idParte) {
		this.idParte = idParte;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getIdConfigProceso() {
		return idConfigProceso;
	}

	public void setIdConfigProceso(Integer idConfigProceso) {
		this.idConfigProceso = idConfigProceso;
	}
	
	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getPesoPintura() {
		return pesoPintura;
	}

	public void setPesoPintura(BigDecimal pesoPintura) {
		this.pesoPintura = pesoPintura;
	}

	public static class Builder {
        private ReporteDTO reporteDTO;

        public Builder() {
            reporteDTO = new ReporteDTO();
        }

        public Builder from(ReporteDTO original) {
            reporteDTO.proyecto = original.proyecto;
            reporteDTO.numOp = original.numOp;
            reporteDTO.ref = original.ref;
            reporteDTO.cantSol = original.cantSol;
            reporteDTO.cantFab = original.cantFab;
            reporteDTO.centroTrabajo = original.centroTrabajo;
            reporteDTO.operario = original.operario; // Nota: considera una copia profunda si Operario es mutable
            reporteDTO.idCentroTrabajo = original.idCentroTrabajo;
            reporteDTO.idItemFab = original.idItemFab;
            reporteDTO.idParte = original.idParte;
            reporteDTO.idItem = original.idItem;
            reporteDTO.cantReportar = original.cantReportar;
            reporteDTO.lote = original.lote;
            reporteDTO.color = original.color;
            reporteDTO.idConfigProceso = original.idConfigProceso;
            reporteDTO.pesoPintura = original.pesoPintura;
            return this;
        }

        public Builder setProyecto(String proyecto) {
            reporteDTO.proyecto = proyecto;
            return this;
        }

        public Builder setNumOp(Integer numOp) {
            reporteDTO.numOp = numOp;
            return this;
        }

        public Builder setRef(String ref) {
            reporteDTO.ref = ref;
            return this;
        }

        public Builder setCantSol(Integer cantSol) {
            reporteDTO.cantSol = cantSol;
            return this;
        }

        public Builder setCantFab(Integer cantFab) {
            reporteDTO.cantFab = cantFab;
            return this;
        }

        public Builder setCentroTrabajo(String centroTrabajo) {
            reporteDTO.centroTrabajo = centroTrabajo;
            return this;
        }

        public Builder setOperario(Operario operario) {
            reporteDTO.operario = operario;
            return this;
        }

        public Builder setIdCentroTrabajo(Integer idCentroTrabajo) {
            reporteDTO.idCentroTrabajo = idCentroTrabajo;
            return this;
        }

        public Builder setIdItemFab(Integer idItemFab) {
            reporteDTO.idItemFab = idItemFab;
            return this;
        }

        public Builder setIdParte(Integer idParte) {
            reporteDTO.idParte = idParte;
            return this;
        }

        public Builder setIdItem(Long idItem) {
            reporteDTO.idItem = idItem;
            return this;
        }

        public Builder setCantReportar(Integer cantReportar) {
            reporteDTO.cantReportar = cantReportar;
            return this;
        }

        public Builder setLote(String lote) {
            reporteDTO.lote = lote;
            return this;
        }

        public Builder setColor(String color) {
            reporteDTO.color = color;
            return this;
        }
        
        public Builder setIdConfigProceso(Integer idConfigProceso) {
            reporteDTO.idConfigProceso = idConfigProceso;
            return this;
        }
        
        public Builder setPeso(BigDecimal peso) {
            reporteDTO.peso = peso;
            return this;
        }
        
        public Builder setPesoPintura(BigDecimal pesoPintura) {
        	reporteDTO.pesoPintura = pesoPintura;
        	return this;
        }

        public ReporteDTO build() {
            return reporteDTO;
        }
    }

	@Override
	public String toString() {
		return "ReporteDTO [proyecto=" + proyecto + ", numOp=" + numOp + ", ref=" + ref + ", cantSol=" + cantSol
				+ ", cantFab=" + cantFab + ", centroTrabajo=" + centroTrabajo + ", operario=" + operario
				+ ", idCentroTrabajo=" + idCentroTrabajo + ", idItemFab=" + idItemFab + ", idParte=" + idParte
				+ ", idItem=" + idItem + ", cantReportar=" + cantReportar + ", lote=" + lote + ", color=" + color
				+ ", idConfigProceso=" + idConfigProceso + ", peso=" + peso + ", pesoPintura=" + pesoPintura + "]";
	}

}
