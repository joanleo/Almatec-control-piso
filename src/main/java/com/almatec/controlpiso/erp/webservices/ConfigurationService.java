package com.almatec.controlpiso.erp.webservices;

import com.almatec.controlpiso.integrapps.entities.Parametro;
import com.almatec.controlpiso.integrapps.services.ParametroService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Service
public class ConfigurationService {

    private final ParametroService parametroService;

    private Integer CIA;
    private String C_O;
    private String INSTALACION;
    private String METODO;
    private String TIPO_DOC_OP_PAPA;
    private String TIPO_DOC_OP_HIJO;
    private String CLASE_OP_PAPA;
    private String CLASE_OP_HIJO;
    private String TIPO_DOC_CONSUMO;
    private String TIPO_DOC_TEP;
    private String TIPO_DOC_TRANSFERENCIA;
    private String TIPO_DOC_ENTREGA;
    private String BODEGA_MATERIAL;
    private String BODEGA_ENTREGA_ITEM_FACTURABLE;
    private String BODEGA_ENTREGA_ITEM_HIJO;
    private String BODEGA_ENTREGA_TRANSFERENCIA;
    private String MOTIVO_TRANSFERENCIA;
    private String MOTIVO_CONSUMO;
    private String MOTIVO_ENTREGA;
    private String PLANIFICADOR;

    public ConfigurationService(ParametroService parametroService) {
        this.parametroService = parametroService;
    }

    @PostConstruct
    public void init() {
        asignarParametros();
    }

    private void asignarParametros() {
        Map<String, Consumer<Parametro>> acciones = new HashMap<>();

        acciones.put("compania", parametro -> this.CIA = Integer.valueOf(parametro.getValor()));
        acciones.put("centro operacion", parametro -> this.C_O = parametro.getValor());
        acciones.put("instalacion", parametro -> this.INSTALACION = parametro.getValor());
        acciones.put("metodo", parametro -> this.METODO = parametro.getValor());
        acciones.put("tipo doc op papa", parametro -> this.TIPO_DOC_OP_PAPA = parametro.getValor());
        acciones.put("tipo doc op hijo", parametro -> this.TIPO_DOC_OP_HIJO = parametro.getValor());
        acciones.put("clase op papa", parametro -> this.CLASE_OP_PAPA = parametro.getValor());
        acciones.put("clase op hijo", parametro -> this.CLASE_OP_HIJO = parametro.getValor());
        acciones.put("tipo doc consumo", parametro -> this.TIPO_DOC_CONSUMO = parametro.getValor());
        acciones.put("tipo doc tep", parametro -> this.TIPO_DOC_TEP = parametro.getValor());
        acciones.put("tipo doc transferencia", parametro -> this.TIPO_DOC_TRANSFERENCIA = parametro.getValor());
        acciones.put("tipo doc entrega", parametro -> this.TIPO_DOC_ENTREGA = parametro.getValor());
        acciones.put("bodega de material", parametro -> this.BODEGA_MATERIAL = parametro.getValor());
        acciones.put("bodega de entrega de item facturable", parametro -> this.BODEGA_ENTREGA_ITEM_FACTURABLE = parametro.getValor());
        acciones.put("bodega entrega item hijo", parametro -> this.BODEGA_ENTREGA_ITEM_HIJO = parametro.getValor());
        acciones.put("bodega entrega transferencia", parametro -> this.BODEGA_ENTREGA_TRANSFERENCIA = parametro.getValor());
        acciones.put("motivo transferencia", parametro -> this.MOTIVO_TRANSFERENCIA = parametro.getValor());
        acciones.put("motivo consumo", parametro -> this.MOTIVO_CONSUMO = parametro.getValor());
        acciones.put("motivo entrega", parametro -> this.MOTIVO_ENTREGA = parametro.getValor());
        acciones.put("planificador", parametro -> this.PLANIFICADOR = parametro.getValor());

        List<Parametro> parametros = parametroService.obtenerParametros();
        parametros.forEach(parametro -> acciones.entrySet().stream()
                .filter(entry -> parametro.getNombre().contains(entry.getKey()))
                .forEach(entry -> entry.getValue().accept(parametro)));
    }


    public Integer getCIA() {
        return CIA;
    }

    public String getC_O() {
        return C_O;
    }

    public String getINSTALACION() {
        return INSTALACION;
    }

    public String getMETODO() {
        return METODO;
    }

    public String getTIPO_DOC_OP_PAPA() {
        return TIPO_DOC_OP_PAPA;
    }

    public String getTIPO_DOC_OP_HIJO() {
        return TIPO_DOC_OP_HIJO;
    }

    public String getCLASE_OP_PAPA() {
        return CLASE_OP_PAPA;
    }

    public String getCLASE_OP_HIJO() {
        return CLASE_OP_HIJO;
    }

    public String getTIPO_DOC_CONSUMO() {
        return TIPO_DOC_CONSUMO;
    }

    public String getTIPO_DOC_TEP() {
        return TIPO_DOC_TEP;
    }

    public String getTIPO_DOC_TRANSFERENCIA() {
        return TIPO_DOC_TRANSFERENCIA;
    }

    public String getTIPO_DOC_ENTREGA() {
        return TIPO_DOC_ENTREGA;
    }

    public String getBODEGA_MATERIAL() {
        return BODEGA_MATERIAL;
    }

    public String getBODEGA_ENTREGA_ITEM_FACTURABLE() {
        return BODEGA_ENTREGA_ITEM_FACTURABLE;
    }

    public String getBODEGA_ENTREGA_ITEM_HIJO() {
        return BODEGA_ENTREGA_ITEM_HIJO;
    }

    public String getBODEGA_ENTREGA_TRANSFERENCIA() {
        return BODEGA_ENTREGA_TRANSFERENCIA;
    }

    public String getMOTIVO_TRANSFERENCIA() {
        return MOTIVO_TRANSFERENCIA;
    }

    public String getMOTIVO_CONSUMO() {
        return MOTIVO_CONSUMO;
    }

    public String getMOTIVO_ENTREGA() {
        return MOTIVO_ENTREGA;
    }


    public String getPLANIFICADOR() {
        return PLANIFICADOR;
    }

}
