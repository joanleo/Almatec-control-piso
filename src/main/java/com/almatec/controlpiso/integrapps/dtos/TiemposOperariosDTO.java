package com.almatec.controlpiso.integrapps.dtos;

import java.time.LocalDateTime;
import java.util.Objects;

import com.almatec.controlpiso.integrapps.interfaces.TiemposOperarioInterface;

public class TiemposOperariosDTO {

	private Integer idConfigProceso;
	private Integer idOperario;
	private String nombreOperario;
	private Boolean isActivo;
	private LocalDateTime fechaInicioTurno;
	private LocalDateTime fechaFinTurno;
	private String turno;
	private Float productivo;
	private Float improductivo;
	
	public TiemposOperariosDTO() {
		super();
	}

	public TiemposOperariosDTO(TiemposOperarioInterface tiemposinterface) {
		this.idConfigProceso = tiemposinterface.getC_proconfigproceso_id();
		this.idOperario = tiemposinterface.getC_proconfigproceso_id();
		this.nombreOperario = tiemposinterface.getA_Operario_Nombre();
		this.isActivo = tiemposinterface.getE_activo();
		this.fechaInicioTurno = tiemposinterface.getF_turnoini();
		this.fechaFinTurno = tiemposinterface.getF_turnofin();
		this.turno = tiemposinterface.getturno();
		this.productivo = tiemposinterface.getTmpActivo();
		this.improductivo = tiemposinterface.getN_ssimproductivo();
	}

	public Integer getIdConfigProceso() {
		return idConfigProceso;
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public String getNombreOperario() {
		return nombreOperario;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public LocalDateTime getFechaInicioTurno() {
		return fechaInicioTurno;
	}

	public LocalDateTime getFechaFinTurno() {
		return fechaFinTurno;
	}

	public String getTurno() {
		return turno;
	}

	public Float getProductivo() {
		return productivo;
	}

	public Float getImproductivo() {
		return improductivo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaFinTurno, fechaInicioTurno, idConfigProceso, idOperario, improductivo, isActivo,
				nombreOperario, productivo, turno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TiemposOperariosDTO other = (TiemposOperariosDTO) obj;
		return Objects.equals(fechaFinTurno, other.fechaFinTurno)
				&& Objects.equals(fechaInicioTurno, other.fechaInicioTurno)
				&& Objects.equals(idConfigProceso, other.idConfigProceso)
				&& Objects.equals(idOperario, other.idOperario) && Objects.equals(improductivo, other.improductivo)
				&& Objects.equals(isActivo, other.isActivo) && Objects.equals(nombreOperario, other.nombreOperario)
				&& Objects.equals(productivo, other.productivo) && Objects.equals(turno, other.turno);
	}
	
	
	
	
}
