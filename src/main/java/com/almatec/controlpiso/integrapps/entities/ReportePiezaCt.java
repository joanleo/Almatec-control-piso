package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pro_reporte_pieza_ct")
public class ReportePiezaCt {
	
	public enum Estado {
        PENDIENTE,          // Reporte guardado inicialmente
        PROCESANDO,         // Durante el envío al ERP
        COMPLETO,           // proceso exitoso
        ERROR              // Error en algún paso
    }
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reporte_id")
	private Integer id;
	
	@Column(name = "Item_fab_id")
	private Integer idItemFab;
	
	@Column(name = "item_parte_id")
	private Integer idParte;
	
	@Column(name = "C_centrotrabajo_id")
	private Integer idCentroT;
	
	@Column(name = "C_prooperario_id")
	private Integer idOperario;
	
	private Integer cant;
	
	@Column(name = "doc_erp")
	private Integer docErp=0;
	
	@Column(name = "fecha_erp")
	private LocalDateTime fechaErp; 
	
	@Column(name = "fecha_ceacion")
	private LocalDateTime fechaCreacion;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@Column(name = "item_id")
	private Long itemId;
	
	private String lote;
	
	@Column(name = "is_consume")
	private Boolean isConsume=false;
	
	@Column(name = "is_tep")
	private Boolean isTep=false;
	
	@Column(name = "id_configproceso")
	private Integer idConfigProceso;
	
	@Column(name = "mensaje_error")
	private String mensajeError;  

	@Column(name = "ultimo_intento")
    private LocalDateTime ultimoIntento;
	
	@Column(name = "es_reproceso")
	private Boolean esReproceso=false;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdItemFab() {
		return idItemFab;
	}

	public void setIdItemFab(Integer idItemFab) {
		this.idItemFab = idItemFab;
	}

	public Integer getIdCentroT() {
		return idCentroT;
	}

	public void setIdCentroT(Integer idCentroT) {
		this.idCentroT = idCentroT;
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
	}

	public Integer getCant() {
		return cant;
	}

	public void setCant(Integer cant) {
		this.cant = cant;
	}

	public Integer getDocErp() {
		return docErp;
	}

	public void setDocErp(Integer docErp) {
		this.docErp = docErp;
	}

	public LocalDateTime getFechaErp() {
		return fechaErp;
	}

	public void setFechaErp(LocalDateTime fechaErp) {
		this.fechaErp = fechaErp;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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

	public Boolean getIsConsume() {
		return isConsume;
	}

	public void setIsConsume(Boolean isConsume) {
		this.isConsume = isConsume;
	}

	public Boolean getIsTep() {
		return isTep;
	}

	public void setIsTep(Boolean isTep) {
		this.isTep = isTep;
	}

	public Integer getIdConfigProceso() {
		return idConfigProceso;
	}

	public void setIdConfigProceso(Integer idConfigProceso) {
		this.idConfigProceso = idConfigProceso;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public LocalDateTime getUltimoIntento() {
		return ultimoIntento;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public void setUltimoIntento(LocalDateTime ultimoIntento) {
		this.ultimoIntento = ultimoIntento;
	}

	public Boolean getEsReproceso() {
		return esReproceso != null && esReproceso;
	}

	public void setEsReproceso(Boolean esReproceso) {
		this.esReproceso = esReproceso;
	}

	public ReportePiezaCt() {
		super();
	}
	
	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("ReportePiezaCt [id=").append(id).append(", idItemFab=").append(idItemFab).append(", idParte=")
				.append(idParte).append(", idCentroT=").append(idCentroT).append(", idOperario=").append(idOperario)
				.append(", cant=").append(cant).append(", docErp=").append(docErp).append(", fechaErp=")
				.append(fechaErp).append(", fechaCreacion=").append(fechaCreacion).append(", estado=").append(estado)
				.append(", itemId=").append(itemId).append(", lote=").append(lote).append(", isConsume=")
				.append(isConsume).append(", isTep=").append(isTep).append(", idConfigProceso=").append(idConfigProceso)
				.append(", mensajeError=").append(mensajeError).append(", ultimoIntento=").append(ultimoIntento)
				.append(", esReproceso=").append(esReproceso).append("]");
		return builder2.toString();
	}

	public static class Builder {
        private ReportePiezaCt reporte = new ReportePiezaCt();

        public Builder idItemFab(Integer idItemFab) {
            reporte.setIdItemFab(idItemFab);
            return this;
        }

        public Builder idParte(Integer idParte) {
        	if (idParte != null) {
                reporte.setIdParte(idParte);
            }
            return this;
        }

        public Builder idCentroT(Integer idCentroT) {
            reporte.setIdCentroT(idCentroT);
            return this;
        }

        public Builder idOperario(Integer idOperario) {
            reporte.setIdOperario(idOperario);
            return this;
        }

        public Builder cant(Integer cant) {
            reporte.setCant(cant);
            return this;
        }

        public Builder fechaCreacion(LocalDateTime fechaCreacion) {
            reporte.setFechaCreacion(fechaCreacion);
            return this;
        }

        public Builder itemId(Long itemId) {
            reporte.setItemId(itemId);
            return this;
        }

        public Builder lote(String lote) {
            reporte.setLote(lote);
            return this;
        }

        public Builder idConfigProceso(Integer idConfigProceso) {
            reporte.setIdConfigProceso(idConfigProceso);
            return this;
        }

        public ReportePiezaCt build() {
            return reporte;
        }
    }
}
