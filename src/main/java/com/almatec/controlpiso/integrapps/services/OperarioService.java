package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.almatec.controlpiso.exceptions.ServiceException;
import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.integrapps.entities.Operario;
import com.almatec.controlpiso.produccion.dtos.OperarioGeneralDTO;
import com.almatec.controlpiso.utils.dto.OperarioBarCodeDTO;

public interface OperarioService {

	Operario obtenerOperario(Integer numCedula);

	Operario buscarOperarioPorId(Integer idOperario);

	List<OperarioBarCodeDTO> obtenerDataBarCodeOperarios();

	List<OperarioGeneralDTO> obtenerOperariosGeneral() throws ServiceException;

	Page<OperarioGeneralDTO> obtenerOperariosGeneralPaginados(int page, int size, String sortBy, String sortDir, String search) throws ServiceException;

	OperarioGeneralDTO obtenerOperarioGeneralPorId(Integer id) throws ServiceException;

	ResponseMessage guardarOperario(OperarioGeneralDTO operario) throws ServiceException;

	ResponseMessage desactivarOperario(Integer id) throws ServiceException;

	ResponseMessage toggleEstadoOperario(Integer id) throws ServiceException;

}
