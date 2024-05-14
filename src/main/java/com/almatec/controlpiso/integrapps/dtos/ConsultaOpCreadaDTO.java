package com.almatec.controlpiso.integrapps.dtos;

import com.almatec.controlpiso.erp.webservices.interfaces.ConsultaItemOpCreado;

public class ConsultaOpCreadaDTO {

	private Integer f850_rowid;
	private String f850_id_tipo_docto;
	private Integer f850_consec_docto;
	private Integer f851_rowid;
	private Integer f120_id;
	public Integer getF850_rowid() {
		return f850_rowid;
	}
	public String getF850_id_tipo_docto() {
		return f850_id_tipo_docto;
	}
	public Integer getF850_consec_docto() {
		return f850_consec_docto;
	}
	public Integer getF851_rowid() {
		return f851_rowid;
	}
	public Integer getF120_id() {
		return f120_id;
	}
	public void setF850_rowid(Integer f850_rowid) {
		this.f850_rowid = f850_rowid;
	}
	public void setF850_id_tipo_docto(String f850_id_tipo_docto) {
		this.f850_id_tipo_docto = f850_id_tipo_docto;
	}
	public void setF850_consec_docto(Integer f850_consec_docto) {
		this.f850_consec_docto = f850_consec_docto;
	}
	public void setF851_rowid(Integer f851_rowid) {
		this.f851_rowid = f851_rowid;
	}
	public void setF120_id(Integer f120_id) {
		this.f120_id = f120_id;
	}
	public ConsultaOpCreadaDTO() {
		super();
	}
	
	public ConsultaOpCreadaDTO(ConsultaItemOpCreado consulta) {
		this.f120_id = consulta.getf120_id();
		this.f850_consec_docto = consulta.getf850_consec_docto();
		this.f850_id_tipo_docto = consulta.getf850_id_tipo_docto();
		this.f850_rowid = consulta.getf850_rowid();
		this.f851_rowid = consulta.getf851_rowid();
	}
}
