package com.almatec.controlpiso.utils.refactoring;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.Parametro;

@Service
public class ParametrosAppService {

    private Map<String, String> parametros;

    public ParametrosAppService(List<Parametro> parametros) {
        this.parametros = parametros.stream()
                .collect(Collectors.toMap(Parametro::getNombre, Parametro::getValor));
    }

    public Integer getCompania() {
        return Integer.valueOf(parametros.get("compania"));
    }

    public String getCentroOperacion() {
        return parametros.get("centro operacion");
    }

    public String getInstalacion() {
        return parametros.get("intalacion");
    }

    public String getMmetodo() {
        return parametros.get("metodo");
    }

    public String getTipoDocOpPapa() {
        return parametros.get("tipo doc op papa");
    }

    public String getTipoDocOpHijo() {
        return parametros.get("tipo doc op hijo");
    }

    public String getClaseOpPapa() {
        return parametros.get("clase op papa");
    }

    public String getClaseOpHijo() {
        return parametros.get("clase op hijo");
    }

    public String getTipoDocConsumo() {
        return parametros.get("tipo doc consumo");
    }

    public String getTipoDocTep() {
        return parametros.get("tipo doc tep");
    }

    public String getTipoDocTransferencia() {
        return parametros.get("tipo doc transferencia");
    }

    public String getTipoDocEntrega() {
        return parametros.get("tipo doc entrega");
    }

    public String getBodegaMateria() {
        return parametros.get("bodega de material");
    }

    public String getBodegaEntregaItemFacturable() {
        return parametros.get("bodega de entrega de item facturable");
    }

    public String getBodegaEntregaItemHijo() {
        return parametros.get("bodega entrega item hijo");
    }

    public String getBodegaEntregaTransferencia() {
        return parametros.get("bodega entrega transferencia");
    }

    public String getMotivoTransferencia() {
        return parametros.get("motivo transferencia");
    }

    public String getMotivoConsumo() {
        return parametros.get("motivo consumo");
    }

    public String getMotivoEntrega() {
        return parametros.get("motivo entrega");
    }

    public String getGrupoImpositivo() {
        return parametros.get("grupo impositivo");
    }

    public String getPlanificador() {
        return parametros.get("planificador");
    }

    public String getTipoServicio() {
        return parametros.get("tipo servicio");
    }
}

