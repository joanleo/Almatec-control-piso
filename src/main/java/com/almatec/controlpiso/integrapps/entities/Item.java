package com.almatec.controlpiso.integrapps.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items_fabrica")
public class Item {

	@Id
	@Column(name = "Item_fab_Id")
	private Integer idItem;
	
	@Column(name = "item_tipo")
	private String tipo;
	
	@Column(name = "codigo_erp")
	private Integer codErp;
	
	@Column(name = "item_desc")
	private String descripcion;
	
	@Column(name = "item_grp_flia")
	private String familia;
	
	@Column(name = "item_grp_pin")
	private String grupo;
	
	@Column(name = "item_peso_b")
	private BigDecimal pesoBruto;
	
	@Column(name = "item_peso_n")
	private BigDecimal pesoNeto;
	
	@Column(name = "item_plano")
	private String plano;
	
	@Column(name = "imp_etiqueta")
	private Boolean imprimeEtiqueta;
	
	@Column(name = "item_estado")
	private Boolean isActivo;
	
	@Column(name = "item_ue")
	private String unidEmpaque;
	
	@Column(name = "item_long")
	private BigDecimal longitud;
	
	@Column(name = "centro_trabajo_consumo")
	private Integer centroTrabajoConsumo;
	
	@Column(name = "centros_trabajo_tep_reportado")
	private String centrosTrabajoTep;

	public Item() {
		super();
	}

	
	public Integer getIdItem() {
		return idItem;
	}


	public String getTipo() {
		return tipo;
	}


	public Integer getCodErp() {
		return codErp;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public String getFamilia() {
		return familia;
	}


	public String getGrupo() {
		return grupo;
	}


	public BigDecimal getPesoBruto() {
		return pesoBruto;
	}


	public BigDecimal getPesoNeto() {
		return pesoNeto;
	}


	public String getPlano() {
		return plano;
	}


	public Boolean getImprimeEtiqueta() {
		return imprimeEtiqueta;
	}


	public Boolean getIsActivo() {
		return isActivo;
	}


	public String getUnidEmpaque() {
		return unidEmpaque;
	}


	public BigDecimal getLongitud() {
		return longitud;
	}


	public Integer getCentroTrabajoConsumo() {
		return centroTrabajoConsumo;
	}


	public List<Integer> getCentrosTrabajoTep() {
        if (centrosTrabajoTep == null || centrosTrabajoTep.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(centrosTrabajoTep.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));
    }


	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public void setCodErp(Integer codErp) {
		this.codErp = codErp;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public void setFamilia(String familia) {
		this.familia = familia;
	}


	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}


	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}


	public void setPesoNeto(BigDecimal pesoNeto) {
		this.pesoNeto = pesoNeto;
	}


	public void setPlano(String plano) {
		this.plano = plano;
	}


	public void setImprimeEtiqueta(Boolean imprimeEtiqueta) {
		this.imprimeEtiqueta = imprimeEtiqueta;
	}


	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}


	public void setUnidEmpaque(String unidEmpaque) {
		this.unidEmpaque = unidEmpaque;
	}


	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}


	public void setCentroTrabajoConsumo(Integer centroTrabajoConsumo) {
		this.centroTrabajoConsumo = centroTrabajoConsumo;
	}


	public void setCentrosTrabajoTep(List<Integer> centrosTep) {
        this.centrosTrabajoTep = centrosTep.stream()
                                    .map(String::valueOf)
                                    .collect(Collectors.joining(","));
    }


	@Override
	public String toString() {
		return "Item [idItem=" + idItem + ", tipo=" + tipo + ", codErp=" + codErp + ", descripcion=" + descripcion
				+ ", familia=" + familia + ", grupo=" + grupo + ", pesoBruto=" + pesoBruto + ", pesoNeto=" + pesoNeto
				+ ", plano=" + plano + ", imprimeEtiqueta=" + imprimeEtiqueta + ", isActivo=" + isActivo
				+ ", unidEmpaque=" + unidEmpaque + ", longitud=" + longitud + ", centroTrabajoConsumo="
				+ centroTrabajoConsumo + ", centrosTrabajoTep=" + centrosTrabajoTep + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(codErp, descripcion, familia, grupo, idItem, isActivo, longitud, pesoBruto, pesoNeto, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(codErp, other.codErp) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(familia, other.familia) && Objects.equals(grupo, other.grupo)
				&& Objects.equals(idItem, other.idItem) && Objects.equals(isActivo, other.isActivo)
				&& Objects.equals(longitud, other.longitud) && Objects.equals(pesoBruto, other.pesoBruto)
				&& Objects.equals(pesoNeto, other.pesoNeto) && Objects.equals(tipo, other.tipo);
	}
	
	
	
	
	
	


}
