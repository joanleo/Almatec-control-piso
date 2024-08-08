package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.almatec.controlpiso.exceptions.ServiceException;
import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.integrapps.entities.Operario;
import com.almatec.controlpiso.produccion.dtos.OperarioGeneralDTO;
import com.almatec.controlpiso.utils.dto.OperarioBarCodeDTO;

public interface OperarioService {

	Operario obtenerOperario(Integer numCedula);

	Operario buscarOperarioPorId(Integer idOperario);

	List<OperarioBarCodeDTO> obtenerDataBarCodeOperarios();

	List<OperarioGeneralDTO> obtenerOperariosGeneral() throws ServiceException;

	Page<OperarioGeneralDTO> obtenerOperariosGeneralPaginados(int page, int size, String sortBy, String sortDir) throws ServiceException;

	OperarioGeneralDTO obtenerOperarioGeneralPorId(Integer id) throws ServiceException;

	ErrorMensaje guardarOperario(OperarioGeneralDTO operario) throws ServiceException;

	ErrorMensaje desactivarOperario(Integer id) throws ServiceException;

	ErrorMensaje toggleEstadoOperario(Integer id) throws ServiceException;

}
