package com.almatec.controlpiso.integrapps.interfaces;

import java.time.LocalDateTime;

public interface TiemposOperarioInterface {
	Integer getC_proconfigproceso_id();
	Integer getC_prooperario_id();
	String getA_Operario_Nombre();
	Boolean getE_activo();
	LocalDateTime getF_turnoini();
	LocalDateTime getF_turnofin();
	String getA_turno();
	String getturno();
	Float getTmpActivo();
	Float getN_ssimproductivo();


}
