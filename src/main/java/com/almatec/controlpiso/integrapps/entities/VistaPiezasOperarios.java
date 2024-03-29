package com.almatec.controlpiso.integrapps.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_piezas_operarios_proceso")
public class VistaPiezasOperarios {
	
	@Id
	@Column(name = "identificador_unico")
	private String id;
	
	@Column(name = "Reg_Pie_Dia_Id")
	private Integer idRegPieza;
	
	@Column(name = "C_proconfigproceso_id")
	private Integer idProceso;
	
	@Column(name = "C_ciaorg_id")
	private Integer idCia;
	
	private Integer ct;
	
	@Column(name = "C_prooperario_id")
	private Integer idOperario;
	
	@Column(name = "E_activo")
	private Boolean isActivo;
	
	private String cliente;
	
	private String proyecto;
	
	@Column(name = "item_id")
	private Long IdItem;
	
	@Column(name = "itemFabId")
	private Integer idItemFab;
	
	@Column(name = "descripcionConjunto")
	private String descripcionItem;
	
	@Column(name = "ct_conjunto")
	private Integer ctConjunto;
	
	private Integer cantOp;
	
	@Column(name = "item_peso_b")
	private BigDecimal pesoConjunto;
	
	private Integer idPerfil;
	
	private String descripcionPerfil;
	
	@Column(name = "ct_perfil")
	private Integer ctPerfil;
	
	private Integer codErp;
	
	@Column(name = "item_perf_peso")
	private BigDecimal pesoPerfil;
	
	private Integer cantListaMateriales;
	
	@Column(name = "valor_aplicar")
	private BigDecimal longitud;
	
	private Integer prioridad;
	
	private Integer idOp;
	private String tipoOp;
	private Integer numOp;
	@Column(name = "Esq_Pintura")
	private String esquemaPintura;
	
	@Column(name = "tiempo_std_item")
	private BigDecimal tiempoStdItem;
	
	@Column(name = "tiempo_std_perfil")
	private BigDecimal tiempoStdPerfil;
	
	@Column(name = "N_sstranscurrido")
	private Integer tiempoTrancurrido;
	
	@Column(name = "N_ssreproceso")
	private Integer tiempoReproceso;
	
	@Column(name = "A_Operario_Nombre")
	private String nombreOperario;
	
	

	public Integer getIdRegPieza() {
		return idRegPieza;
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public Integer getIdCia() {
		return idCia;
	}

	public Integer getCt() {
		return ct;
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public String getCliente() {
		return cliente;
	}

	public String getProyecto() {
		return proyecto;
	}

	public Long getIdItem() {
		return IdItem;
	}

	public Integer getIdItemFab() {
		return idItemFab;
	}

	public String getDescripcionItem() {
		return descripcionItem;
	}

	public Integer getCtConjunto() {
		return ctConjunto;
	}

	public Integer getCantOp() {
		return cantOp;
	}

	public BigDecimal getPesoConjunto() {
		return pesoConjunto;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public String getDescripcionPerfil() {
		return descripcionPerfil;
	}

	public Integer getCtPerfil() {
		return ctPerfil;
	}

	public Integer getCodErp() {
		return codErp;
	}

	public BigDecimal getPesoPerfil() {
		return pesoPerfil;
	}

	public Integer getCantListaMateriales() {
		return cantListaMateriales;
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public Integer getIdOp() {
		return idOp;
	}

	public String getTipoOp() {
		return tipoOp;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public String getEsquemaPintura() {
		return esquemaPintura;
	}

	public String getId() {
		return id;
	}

	public BigDecimal getTiempoStdItem() {
		return tiempoStdItem;
	}

	public BigDecimal getTiempoStdPerfil() {
		return tiempoStdPerfil;
	}

	public Integer getTiempoTrancurrido() {
		return tiempoTrancurrido;
	}

	public Integer getTiempoReproceso() {
		return tiempoReproceso;
	}

	public String getNombreOperario() {
		return nombreOperario;
	}

	public VistaPiezasOperarios() {
		super();
	}

	@Override
	public String toString() {
		return "VistaPiezasOperarios [id=" + id + ", idRegPieza=" + idRegPieza + ", idProceso=" + idProceso + ", idCia="
				+ idCia + ", ct=" + ct + ", idOperario=" + idOperario + ", isActivo=" + isActivo + ", cliente="
				+ cliente + ", proyecto=" + proyecto + ", IdItem=" + IdItem + ", idItemFab=" + idItemFab
				+ ", descripcionItem=" + descripcionItem + ", ctConjunto=" + ctConjunto + ", cantOp=" + cantOp
				+ ", pesoConjunto=" + pesoConjunto + ", idPerfil=" + idPerfil + ", descripcionPerfil="
				+ descripcionPerfil + ", ctPerfil=" + ctPerfil + ", codErp=" + codErp + ", pesoPerfil=" + pesoPerfil
				+ ", cantListaMateriales=" + cantListaMateriales + ", longitud=" + longitud + ", prioridad=" + prioridad
				+ ", idOp=" + idOp + ", tipoOp=" + tipoOp + ", numOp=" + numOp + ", esquemaPintura=" + esquemaPintura
				+ ", tiempoStdItem=" + tiempoStdItem + ", tiempoStdPerfil=" + tiempoStdPerfil + ", tiempoTrancurrido="
				+ tiempoTrancurrido + ", tiempoReproceso=" + tiempoReproceso + ", nombreOperario=" + nombreOperario
				+ "]";
	}
	
}
