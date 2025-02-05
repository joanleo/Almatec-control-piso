package com.almatec.controlpiso.erp.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.almatec.controlpiso.erp.entities.ListaMaterial;
import com.almatec.controlpiso.erp.interfaces.DataConsumoInterface;
import com.almatec.controlpiso.erp.interfaces.DataCostoStandarInterface;
import com.almatec.controlpiso.erp.interfaces.DataTEP;
import com.almatec.controlpiso.erp.interfaces.DetalleTransferenciaInterface;
import com.almatec.controlpiso.erp.interfaces.RutaInterface;
import com.almatec.controlpiso.erp.interfaces.TarifaCostosSegmentoItem;
import com.almatec.controlpiso.erp.repositories.ListaMaterialRepository;
import com.almatec.controlpiso.erp.services.ListaMaterialService;
import com.almatec.controlpiso.erp.webservices.interfaces.ConsultaItemOpCreado;
import com.almatec.controlpiso.erp.webservices.interfaces.TipoServicioYGrupoImpositivo;
import com.almatec.controlpiso.exceptions.RutaItemException;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;

@Service
public class ListaMaterialServiceImpl implements ListaMaterialService {

	@Autowired
	private ListaMaterialRepository listaMaterialRepo;
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public List<ListaMaterial> obtenerListaActual(Integer f820_id) {
		return listaMaterialRepo.obtenerListaActual(f820_id);
	}

	@Override
	public List<RutaInterface> obtenerRutasActual(String f808_id) {
		return listaMaterialRepo.obtenerRutas(f808_id);
	}

	@Override
	public Integer obtenerUltimoIdRef() {
		return listaMaterialRepo.obtenerUltimoIdRefItem();
	}

	@Override
	public ConsultaItemOpCreado obtenerRowIdOpItemOp(String f120_id) {
		return listaMaterialRepo.obtenerRowIdOpItemOp(Integer.valueOf(f120_id));
	}

	@Override
	public DetalleTransferenciaInterface obtenerDetalleTransferencia(String idSolIntegrapps) {
		try {
			DetalleTransferenciaInterface detalle = listaMaterialRepo.obtenerDetalleTransferencia(idSolIntegrapps);
			return detalle;
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@Override
	public DataConsumoInterface obtenerDataParaConsumo(Integer idOp) {
		DataConsumoInterface data = listaMaterialRepo.obtenerDataParaConsumo(idOp);

		return data;
	}

	@Override
	public String obtenerConsecutivoNotasTransferencia(String idOp) {
		return listaMaterialRepo.obtenerConsecutivoNotasTransferencia(idOp);
	}

	@Override
	public DataTEP obtenerDataTEP(String idRuta, String idCentroTrabajo) {
		System.out.println("Ruta: " + idRuta);
		if (StringUtils.isEmpty(idRuta)) {
            throw new RutaItemException(
                "El ID de ruta no puede estar vacío",
                "TEP_001",
                "Parámetro idRuta es null o vacío"
            );
        }
        System.out.println("Centro de trabajo: " +idCentroTrabajo);
        if (StringUtils.isEmpty(idCentroTrabajo)) {
            throw new RutaItemException(
                "El ID de centro de trabajo no puede estar vacío",
                "TEP_002",
                "Parámetro idCentroTrabajo es null o vacío"
            );
        }
		try {
			
			DataTEP resultado = listaMaterialRepo.obtenerDataTEP(idRuta, idCentroTrabajo);

            // Validación del resultado
            if (resultado == null) {
                throw new RutaItemException(
                    "No se encontraron datos para la ruta id:" + idRuta + 
                    " y centro de trabajo id:" + idCentroTrabajo,
                    "TEP_003",
                    "La consulta retornó null"
                );
            }
            
            return resultado;		
		} catch (RutaItemException e) {
	        // Propagamos la excepción RutaItemException sin modificar
	        throw e;
		} catch (DataAccessException e) {
            // Error específico de acceso a datos
            String mensaje = "Error al acceder a los datos TEP";
            log.error("{}: {}", mensaje, e.getMessage(), e);
            throw new RutaItemException(
                mensaje,
                "TEP_004",
                "Error de acceso a datos: " + e.getMessage()
            );
            
        } catch (Exception e) {
            // Cualquier otra excepción no prevista
        	String mensajeDetallado = e.getMessage();
            log.error("Error inesperado al obtener datos TEP: {}", mensajeDetallado, e);
            throw new RutaItemException(
                mensajeDetallado,  // Usamos el mensaje original aquí
                "TEP_999",
                "Error inesperado: " + e.getMessage()
            );
        }
	}

	@Override
	public Integer obtenerItemOp(VistaOrdenPv ordenIFPapa) {
		
		return listaMaterialRepo.obtenerItemOp(ordenIFPapa);
	}

	@Override
	public DataCostoStandarInterface obtenerCostoStandar(Integer idItem) {
		return listaMaterialRepo.obtenerCostoStandar(idItem);
	}

	@Override
	public List<TarifaCostosSegmentoItem> obtenerCostosSegmentos(String ref) {
		List<TarifaCostosSegmentoItem>  costos =listaMaterialRepo.encontrarCostosSegmentosItemPorRef(ref);
		return costos;
	}

	@Override
	public TipoServicioYGrupoImpositivo obtenerTipoServicioYGrupoImpositivoItem(Integer itemIFId, String tipoOp, Integer numOp) {
		TipoServicioYGrupoImpositivo grupo = listaMaterialRepo.encontrarTipoServicioYGrupoImpositivoItem(itemIFId, tipoOp, numOp);
		return grupo; 
	}
}
