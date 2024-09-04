package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.exceptions.ServiceException;
import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.entities.Operario;
import com.almatec.controlpiso.integrapps.entities.Persona;
import com.almatec.controlpiso.integrapps.repositories.CentroTrabajoRepository;
import com.almatec.controlpiso.integrapps.repositories.OperarioRepository;
import com.almatec.controlpiso.integrapps.repositories.PersonaRepository;
import com.almatec.controlpiso.integrapps.services.OperarioService;
import com.almatec.controlpiso.produccion.dtos.OperarioGeneralDTO;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.services.UsuarioService;
import com.almatec.controlpiso.utils.dto.OperarioBarCodeDTO;

@Service
public class OperarioServiceImpl  implements OperarioService{
	
	@Autowired
	private OperarioRepository operarioRepo;
	
	@Autowired
	private PersonaRepository personaRepo;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CentroTrabajoRepository centroTrabajoRepo;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Operario obtenerOperario(Integer numCedula) {
		Integer idOperario = operarioRepo.obtenerIdOperario(String.valueOf(numCedula));
		if (idOperario == null) {
            throw new ResourceNotFoundException("No se encontró operario con la cédula: " + numCedula);
        }
		return operarioRepo.findById(idOperario)
				.orElseThrow(()-> new ResourceNotFoundException("No se encontro operario con la cedula: " + numCedula));
	}

	@Override
	public Operario buscarOperarioPorId(Integer idOperario) {
		return operarioRepo.findById(idOperario)
				.orElseThrow(()-> new ResourceNotFoundException("No se encontro operario con el id: " + idOperario));
	}

	@Override
	public List<OperarioBarCodeDTO> obtenerDataBarCodeOperarios() {
		
		List<Object[]> results = operarioRepo.buscarDataBarCodeOperarios();
        
        return results.stream()
            .map(result -> new OperarioBarCodeDTO(
                (String) result[0],  // A_Operario_Nombre
                (String) result[1]   // Barcode
            ))
            .collect(Collectors.toList());

	}

	@Override
	public List<OperarioGeneralDTO> obtenerOperariosGeneral() throws ServiceException {
		try {
            List<OperarioGeneralDTO> operarios = operarioRepo.buscarOperariosGeneral();
            if (operarios.isEmpty()) {
            	logger.warn("No se encontraron operarios");
            }
            return operarios;
        } catch (Exception e) {
        	logger.error("Error al obtener operarios generales", e);
            throw new ServiceException("Error al obtener la lista de operarios", e);
        }
	}

	@Override
	public Page<OperarioGeneralDTO> obtenerOperariosGeneralPaginados(int page, int size, String sortBy, String sortDir, String search) throws ServiceException {
		try {
            Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                        Sort.by(sortBy).descending();
            
            Pageable pageable = PageRequest.of(page, size, sort);
            
            if (search != null && !search.trim().isEmpty()) {
                return operarioRepo.buscarOperariosGeneralPaginadosConFiltro(search.trim(), pageable);
            } else {
                return operarioRepo.buscarOperariosGeneralPaginados(pageable);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al obtener operarios paginados", e);
        }
    }

	@Override
	@Transactional
	public OperarioGeneralDTO obtenerOperarioGeneralPorId(Integer id) throws ServiceException {
		try {
            OperarioGeneralDTO operario = operarioRepo.buscarOperarioGeneralPorId(id);
            if (operario == null) {
            	logger.warn("No se encontraro el operario");
            }
            Operario oper = operarioRepo.findById(id).orElseThrow();
            if(oper != null && !oper.getPoliFunciones().isEmpty()) {
            	operario.setCentroTrabajoIds(oper.getPoliFunciones().stream()
            			.map(pf-> pf.getCentroTrabajo().getId())
            			.collect(Collectors.toList()));
            }
            return operario;
        } catch (Exception e) {
        	logger.error("Error al obtener operario", e);
            throw new ServiceException("Error al obtener el operario", e);
        }
	}

	@Transactional
	@Override
	public ResponseMessage guardarOperario(OperarioGeneralDTO operarioDTO) throws ServiceException {
		try {
            Persona persona;
            Operario operario;
            if (operarioDTO.getId() == null) {
                // Crear nueva persona
            	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            	Usuario usuarioP = usuarioService.ObtenerUsuarioPorNombreUsuario(authentication.getName());
                persona = new Persona();
        		persona.setUsuarioCrea(usuarioP.getId());
                operario = new Operario();
                operario.setUsuarioCrea(usuarioP.getNombreUsuario());

            } else {
                // Actualizar persona existente
                operario = operarioRepo.findById(operarioDTO.getId())
                    .orElseThrow(() -> new ServiceException("Operario no encontrado"));
                persona = operario.getPersona();
            }

            // Actualizar datos de la persona
            actualizarDatosPersona(persona, operarioDTO);
            persona = personaRepo.save(persona);

            // Actualizar datos del operario
            actualizarDatosOperario(operario, operarioDTO, persona);
            operario = operarioRepo.save(operario);

            // Actualizar centros de trabajo
            operario.getPoliFunciones().clear();
            for (Integer centroTrabajoId : operarioDTO.getCentroTrabajoIds()) {
                CentroTrabajo centroTrabajo = centroTrabajoRepo.findById(centroTrabajoId)
                    .orElseThrow(() -> new ServiceException("Centro de trabajo no encontrado"));
                operario.addCentroTrabajo(centroTrabajo);
            }

            operario = operarioRepo.save(operario);

            String mensaje = (operarioDTO.getId() == null) ? "Operario creado exitosamente" : "Operario actualizado exitosamente";
            return new ResponseMessage(false, mensaje);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar el operario: " + e.getMessage());
        }
	}
	
	private void actualizarDatosPersona(Persona persona, OperarioGeneralDTO dto) {
        persona.setNombres(dto.getNombres());
        persona.setApellidos(dto.getApellidos());
        persona.setNumDoc(dto.getNumDoc());
        persona.setDireccion(dto.getDireccion());
        persona.setCelular(dto.getCelular());
        persona.setEmail(dto.getEmail());
        persona.setSexo(dto.getSexo());
        persona.setBarcode("USU"+dto.getNumDoc());
        
        
    }
	
	private void actualizarDatosOperario(Operario operario, OperarioGeneralDTO dto, Persona persona) {
        operario.setPersona(persona);
        operario.setNombre(dto.getNombres() + " " + dto.getApellidos());
        // Actualizar otros campos necesarios
    }

	@Override
	@Transactional
    public ResponseMessage desactivarOperario(Integer id) throws ServiceException {
        try {
            Operario operario = operarioRepo.findById(id)
                .orElseThrow(() -> new ServiceException("Operario no encontrado"));
            
            operario.setIsActivo(false);
            operarioRepo.save(operario);
            
            return new ResponseMessage(false, "Operario desactivado exitosamente");
        } catch (Exception e) {
            throw new ServiceException("Error al desactivar el operario: " + e.getMessage());
        }
    }

	@Override
	@Transactional
	public ResponseMessage toggleEstadoOperario(Integer id) throws ServiceException {
	    try {
	        Operario operario = operarioRepo.findById(id)
	            .orElseThrow(() -> new ServiceException("Operario no encontrado"));
	        
	        operario.setIsActivo(!operario.getIsActivo());
	        operarioRepo.save(operario);
	        
	        String accion = operario.getIsActivo() ? "activado" : "desactivado";
	        return new ResponseMessage(false, "Operario " + accion + " exitosamente");
	    } catch (Exception e) {
	        throw new ServiceException("Error al cambiar el estado del operario: " + e.getMessage());
	    }
	}
}
