package com.almatec.controlpiso.erp.webservices.dto;

public class ConsumoDTO {
	
	private Integer rowid851;
    private Integer idItem120;
    private Integer idItemComp;
    private Double cantBase;
    private String idLote;
	private String notas;
    private Integer idConcepto;
    private String idUnMovto;
    private String idUnidadInventario;

    public ConsumoDTO() {
    	super();
    }

	public Integer getRowid851() {
		return rowid851;
	}

	public Integer getIdItem120() {
		return idItem120;
	}

	public Integer getIdItemComp() {
		return idItemComp;
	}

	public Double getCantBase() {
		return cantBase;
	}

	public String getIdLote() {
		return idLote;
	}

	public String getNotas() {
		return notas;
	}

	public Integer getIdConcepto() {
		return idConcepto;
	}

	public String getIdUnMovto() {
		return idUnMovto;
	}

	public String getIdUnidadInventario() {
		return idUnidadInventario;
	}

	public void setRowid851(Integer rowid851) {
		this.rowid851 = rowid851;
	}

	public void setIdItem120(Integer idItem120) {
		this.idItem120 = idItem120;
	}

	public void setIdItemComp(Integer idItemComp) {
		this.idItemComp = idItemComp;
	}

	public void setCantBase(Double cantBase) {
		this.cantBase = cantBase;
	}

	public void setIdLote(String idLote) {
		this.idLote = idLote;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public void setIdConcepto(Integer idConcepto) {
		this.idConcepto = idConcepto;
	}

	public void setIdUnMovto(String idUnMovto) {
		this.idUnMovto = idUnMovto;
	}

	public void setIdUnidadInventario(String idUnidadInventario) {
		this.idUnidadInventario = idUnidadInventario;
	}
}
