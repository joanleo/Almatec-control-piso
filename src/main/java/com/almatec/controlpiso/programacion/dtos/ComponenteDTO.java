package com.almatec.controlpiso.programacion.dtos;

import java.math.BigDecimal;
import java.util.Objects;


public class ComponenteDTO {
	
	private Integer materialId;
	private String materialDescripcion;
	private BigDecimal materialPeso;
	private BigDecimal materialLongitud;
	private BigDecimal materialCant;
	private Integer materialCentroTId;
	private String materialCentroTNombre;
	private Integer materiaPrimaId;
	private String materiaPrimaDescripcion;
	private BigDecimal materiaPrimaCant;
	private Integer cantReportadaMaterial;
	private BigDecimal cantPendienteMaterial;

	public ComponenteDTO() {
		super();
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public String getMaterialDescripcion() {
		return materialDescripcion;
	}

	public BigDecimal getMaterialPeso() {
		return materialPeso;
	}

	public BigDecimal getMaterialLongitud() {
		return materialLongitud;
	}

	public BigDecimal getMaterialCant() {
		return materialCant;
	}

	public Integer getMaterialCentroTId() {
		return materialCentroTId;
	}

	public String getMaterialCentroTNombre() {
		return materialCentroTNombre;
	}

	public Integer getMateriaPrimaId() {
		return materiaPrimaId;
	}

	public String getMateriaPrimaDescripcion() {
		return materiaPrimaDescripcion;
	}

	public BigDecimal getMateriaPrimaCant() {
		return materiaPrimaCant;
	}

	public Integer getCantReportadaMaterial() {
		return cantReportadaMaterial;
	}

	public BigDecimal getCantPendienteMaterial() {
		return cantPendienteMaterial;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public void setMaterialDescripcion(String materialDescripcion) {
		this.materialDescripcion = materialDescripcion;
	}

	public void setMaterialPeso(BigDecimal materialPeso) {
		this.materialPeso = materialPeso;
	}

	public void setMaterialLongitud(BigDecimal materialLongitud) {
		this.materialLongitud = materialLongitud;
	}

	public void setMaterialCant(BigDecimal materialCant) {
		this.materialCant = materialCant;
	}

	public void setMaterialCentroTId(Integer materialCentroTId) {
		this.materialCentroTId = materialCentroTId;
	}

	public void setMaterialCentroTNombre(String materialCentroTNombre) {
		this.materialCentroTNombre = materialCentroTNombre;
	}

	public void setMateriaPrimaId(Integer materiaPrimaId) {
		this.materiaPrimaId = materiaPrimaId;
	}

	public void setMateriaPrimaDescripcion(String materiaPrimaDescripcion) {
		this.materiaPrimaDescripcion = materiaPrimaDescripcion;
	}

	public void setMateriaPrimaCant(BigDecimal materiaPrimaCant) {
		this.materiaPrimaCant = materiaPrimaCant;
	}

	public void setCantReportadaMaterial(Integer cantReportadaMaterial) {
		this.cantReportadaMaterial = cantReportadaMaterial;
	}

	public void setCantPendienteMaterial(BigDecimal cantPendienteMaterial) {
		this.cantPendienteMaterial = cantPendienteMaterial;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantPendienteMaterial, cantReportadaMaterial, materiaPrimaCant, materiaPrimaDescripcion,
				materiaPrimaId, materialCant, materialCentroTId, materialCentroTNombre, materialDescripcion, materialId,
				materialLongitud, materialPeso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComponenteDTO other = (ComponenteDTO) obj;
		return Objects.equals(cantPendienteMaterial, other.cantPendienteMaterial)
				&& Objects.equals(cantReportadaMaterial, other.cantReportadaMaterial)
				&& Objects.equals(materiaPrimaCant, other.materiaPrimaCant)
				&& Objects.equals(materiaPrimaDescripcion, other.materiaPrimaDescripcion)
				&& Objects.equals(materiaPrimaId, other.materiaPrimaId)
				&& Objects.equals(materialCant, other.materialCant)
				&& Objects.equals(materialCentroTId, other.materialCentroTId)
				&& Objects.equals(materialCentroTNombre, other.materialCentroTNombre)
				&& Objects.equals(materialDescripcion, other.materialDescripcion)
				&& Objects.equals(materialId, other.materialId)
				&& Objects.equals(materialLongitud, other.materialLongitud)
				&& Objects.equals(materialPeso, other.materialPeso);
	}

	@Override
	public String toString() {
		return "ComponenteDTO [materialId=" + materialId + ", materialDescripcion=" + materialDescripcion
				+ ", materialPeso=" + materialPeso + ", materialLongitud=" + materialLongitud + ", materialCant="
				+ materialCant + ", materialCentroTId=" + materialCentroTId + ", materialCentroTNombre="
				+ materialCentroTNombre + ", materiaPrimaId=" + materiaPrimaId + ", materiaPrimaDescripcion="
				+ materiaPrimaDescripcion + ", materiaPrimaCant=" + materiaPrimaCant + ", cantReportadaMaterial="
				+ cantReportadaMaterial + ", cantPendienteMaterial=" + cantPendienteMaterial + "]";
	}
	
	

}
