package com.almatec.controlpiso.utils.refactoring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionDocumentosVersion01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionItemsVersion01;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;
import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;
import com.almatec.controlpiso.utils.refactoring.conectores.DetalleOpPapaService;
import com.almatec.controlpiso.utils.refactoring.conectores.EdicionCostoService;
import com.almatec.controlpiso.utils.refactoring.conectores.EncabezadoOpPapaService;
import com.almatec.controlpiso.utils.refactoring.conectores.ItemParametrosService;

@Component
public class CrearOpPapaService extends ErpServiceAbstract {

	@Autowired
	private EncabezadoOpPapaService encabezadoOpPapaService;
	
	@Autowired
	private DetalleOpPapaService detalleOpPapaService;
	
	@Autowired
	private ItemParametrosService itemParametros;
	
	@Autowired
	private EdicionCostoService edicionCosto;
	
	@Autowired
    private GeneradorArchivo archivoManager;
	
	
	public String crearOrdenProduccionPapa(List<VistaItemPedidoErp> items) throws IOException {
        List<Conector> costosParametros = crearCostoStandarItem(items.get(0).getReferencia(), parametros.getBodegaEntregaItemFacturable());

        List<Conector> ordenProduccion = new ArrayList<>();

        ordenProduccion.addAll(costosParametros);
        DoctoordenesdeproduccionDocumentosVersion01 encabezadoOp = encabezadoOpPapaService.crearEncabezadoOpPapa(items.get(0).getNoPedido(), parametros);
        ordenProduccion.add(encabezadoOp);
        List<DoctoordenesdeproduccionItemsVersion01> detallesOp = detalleOpPapaService.crearDetalleOpPapa(items, parametros);
        ordenProduccion.addAll(detallesOp);

        String response = postImportarXML(ordenProduccion);
        String consecutivo = "OP_PV-" + items.get(0).getNoPedido();
        archivoManager.crearArchivo(ordenProduccion, consecutivo);

        if(RESPUESTA_OK.equals(response)) {
            return "Orden creada exitosamente";
        } else {
            return response;
        }
    }

	
	private List<Conector> crearCostoStandarItem(String referencia, String bodega) {
		List<Conector> costosParametros = new ArrayList<>();
		Conector parametrosItem = itemParametros.crearParametrosItem(parametros, referencia, bodega);
		costosParametros.add(parametrosItem);
		Conector costo = edicionCosto.crearConectorEdicionCosto(parametros, referencia);
		costosParametros.add(costo);
		return costosParametros;
	}
}
