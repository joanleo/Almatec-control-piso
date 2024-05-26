package com.almatec.controlpiso.utils.refactoring.conectores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionDocumentosVersion01;
import com.almatec.controlpiso.utils.UtilitiesApp;
import com.almatec.controlpiso.utils.refactoring.ParametrosAppService;

@Service
public class EncabezadoOpPapaService {
    
    @Autowired
    private UtilitiesApp util;

    public DoctoordenesdeproduccionDocumentosVersion01 crearEncabezadoOpPapa(Integer noPedido, ParametrosAppService parametroHelper) {
        DoctoordenesdeproduccionDocumentosVersion01 encabezado = new DoctoordenesdeproduccionDocumentosVersion01();
        encabezado.setF_cia(parametroHelper.getCompania());
        encabezado.setF_consec_auto_reg(1);
        encabezado.setF850_id_co(parametroHelper.getCentroOperacion());
        encabezado.setF850_id_tipo_docto(parametroHelper.getTipoDocOpPapa());
        encabezado.setF850_fecha(util.obtenerFechaFormateada());
        encabezado.setF850_ind_estado(1);
        encabezado.setF850_id_clase_docto(701);
        encabezado.setF850_tercero_planificador(parametroHelper.getPlanificador());
        encabezado.setF850_id_instalacion(parametroHelper.getInstalacion());
        encabezado.setF850_clase_op(parametroHelper.getClaseOpPapa());
        encabezado.setF850_id_co_pv(parametroHelper.getCentroOperacion());
        encabezado.setF850_id_tipo_docto_pv("PV");
        encabezado.setF850_consec_docto_pv(noPedido);
        encabezado.setF850_notas(parametroHelper.getBodegaEntregaItemFacturable());
        return encabezado;
    }

}
