package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "personas",uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"Per_Id"},
                name = "uk__persona_id"
            )
        })
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Per_Id")
	private Integer id;
	
	@Column(name = "Comp_Id")
	private Integer idCia = 22;
	
	@Column(name = "Id_Emp_Car")
	private Integer idCargo = 1;
	
	@Column(name = "Id_Sexo")
	private Integer sexo;
	
	@Column(name = "Tip_Doc_Id")
	private Integer tipoDoc = 1;
	
	@Column(name = "Per_Doc_Num", nullable = false, length = 20)
	private String numDoc;
	
	@Column(name = "Per_Nombres", nullable = false, length = 60)
	private String nombres;
	
	@Column(name = "Per_Apellidos", nullable = false, length = 60)
	private String apellidos;
	
	@Column(name = "Per_Dir", nullable = false, length = 60)
	private String direccion;
	
	@Column(name = "Per_Tel_Cel", nullable = false, length = 35)
	private String celular;
	
	@Column(name = "Per_Mail", nullable = false, length = 45)
	private String email;
	
	@Column(name = "Crea_Id")
	private Integer usuarioCrea;
	
	@Column(name = "Fecha_Crea")
	private LocalDateTime fechaCrea;
	
	@Column(name = "Barcode", nullable = false, length = 20)
	private String barcode;
	
	@PrePersist
	protected void onCreate() {
		LocalDateTime now = LocalDateTime.now();
		this.fechaCrea = now;
	}
	
	public Persona() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdCia() {
		return idCia;
	}

	public Integer getIdCargo() {
		return idCargo;
	}

	public Integer getSexo() {
		return sexo;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public String getNombres() {
		return nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getCelular() {
		return celular;
	}

	public String getEmail() {
		return email;
	}

	public Integer getUsuarioCrea() {
		return usuarioCrea;
	}

	public LocalDateTime getFechaCrea() {
		return fechaCrea;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdCia(Integer idCia) {
		this.idCia = idCia;
	}

	public void setIdCargo(Integer idCargo) {
		this.idCargo = idCargo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsuarioCrea(Integer usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public void setFechaCrea(LocalDateTime fechaCrea) {
		this.fechaCrea = fechaCrea;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Integer getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(Integer tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public Persona(Builder builder) {
		this.sexo = builder.sexo;
		this.numDoc = builder.numDoc;
		this.nombres = builder.nombres;
		this.apellidos = builder.apellidos;
		this.direccion = builder.direccion;
		this.celular = builder.celular;
		this.email = builder.email;
		this.usuarioCrea = builder.usuarioCrea;
        this.barcode = "USU" + this.numDoc;
	}

	public static class Builder {
		
		private Integer sexo;
		private String numDoc;
		private String nombres;		
		private String apellidos;		
		private String direccion;
		private String celular;		
		private String email;		
		private Integer usuarioCrea;
		
		public Builder setNumDoc(String numDoc) {
			this.numDoc = numDoc;
			return this;
		}
		public Builder setNombres(String nombres) {
			this.nombres = nombres;
			return this;
		}
		public Builder setApellidos(String apellidos) {
			this.apellidos = apellidos;
			return this;
		}
		public Builder setDireccion(String direccion) {
			this.direccion = direccion;
			return this;
		}
		public Builder setCelular(String celular) {
			this.celular = celular;
			return this;
		}
		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}
		public Builder setUsuarioCrea(Integer usuarioCrea) {
			this.usuarioCrea = usuarioCrea;
			return this;
		}
		
		public Persona build() {
            return new Persona(this);
        }
		
	}
}
