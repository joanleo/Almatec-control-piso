package com.almatec.controlpiso.programacion.dtos;

import java.math.BigDecimal;

public interface VistaOpItemsMaterialesRutaInterface {
	
	 Integer getidOpIntegrapps();
     String getop();
     Long getitem_op_id();
     Integer getitem_id();
     Integer getitemCentroTId();
     String getcliente();
     String getun();
     String getesquema_pintura();
     String getitemDescripcion();
     BigDecimal getitem_peso();
     BigDecimal getitem_long();
     String getitem_color();
     BigDecimal getcantReq();
     BigDecimal getcant_cumplida();
     String getitem_centro_t_nombre();
     String getzona();
     String getgrupo();
     String getmarca();
     Integer getprioridad();

}
