package com.almatec.controlpiso.ingenieria.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.almatec.controlpiso.ingenieria.MemoWithOP;
import com.almatec.controlpiso.ingenieria.dtos.MemoDTO;
import com.almatec.controlpiso.ingenieria.dtos.MemoDetalleDTO;
import com.almatec.controlpiso.ingenieria.services.MemoService;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.entities.Memo;
import com.almatec.controlpiso.integrapps.entities.MemoDetalle;
import com.almatec.controlpiso.integrapps.repositories.ItemOpRepository;
import com.almatec.controlpiso.integrapps.repositories.MemoRepository;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.services.UsuarioService;


@Service
@Transactional
public class MemoServiceImpl implements MemoService {
	
	private final MemoRepository memoRepo;
	private final UsuarioService usuarioService;
	private final ItemOpRepository itemOpRepo;
		
	private Logger logger = LoggerFactory.getLogger(getClass());

	public MemoServiceImpl(MemoRepository memoRepo, 
			UsuarioService usuarioService, 
			ItemOpRepository itemOpRepo) {
		super();
		this.memoRepo = memoRepo;
		this.usuarioService = usuarioService;
		this.itemOpRepo = itemOpRepo;
		
	}
	


	@Transactional
	@Override
	public MemoDTO guardarMemo(MemoDTO memoDTO) {
		ModelMapper mapper = new ModelMapper();
		try {
			Usuario usuario = usuarioService.buscarUsuarioPorId(memoDTO.getIdUsuario());
			Memo memo = mapper.map(memoDTO, Memo.class);
			List<MemoDetalle> detalles = memoDTO.getDetalles().stream()
					.map(detalleDTO->{
						MemoDetalle detalle = new MemoDetalle();
						ItemOp item = itemOpRepo.findById(detalleDTO.getIdItemOp()).orElseThrow();
						detalle.setItemOp(item);
						detalle.setCantidad(detalleDTO.getCantidad());
						detalle.setOperacion(detalleDTO.getOperacion());
						detalle.setObservacion(memoDTO.getObservacion());
						detalle.setMemo(memo);
						return detalle;
					}).collect(Collectors.toList());
			memo.setDetalles(detalles);
			memo.setUsuarioCrea(usuario);
			Memo memoSaved = memoRepo.save(memo);
			
			PropertyMap<Memo, MemoDTO> detalleMap = new PropertyMap<Memo, MemoDTO>() {				
				@Override
				protected void configure() {
					map().setIdUsuario(source.getUsuarioCrea().getId());
					map().setIdOpIntegrapps(source.getIdOpIntegrapps());
				}
			};
			mapper.addMappings(detalleMap);			
			
			return mapper.map(memoSaved, MemoDTO.class);			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<MemoWithOP> obtenerMemosSinAprobar() {
		try {
			return memoRepo.findByIdEstadoSinAprobar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MemoDetalleDTO> obtenerDetalleMemo(Long idMemo) {
		//StopWatch stopWatch = new StopWatch();
        //stopWatch.start();
        List<Object[]> results = memoRepo.findByMemoId(idMemo);
		List<MemoDetalleDTO> detalles = results.stream()
				.map(result -> new MemoDetalleDTO(
						((Number) result[0]).longValue(), 
						(Integer) result[1],
						(String) result[2],
						(String) result[3],
						(String) result[4]
				))
				.collect(Collectors.toList());
		//stopWatch.stop();
		//logger.info("Tiempo de ejecución: {} ms", stopWatch.getTotalTimeMillis());
		return detalles;
	}

	@Override
	public MemoDTO aprobarMemo(Long idMemo, Usuario usuarioAprueba) {
		ModelMapper mapper = new ModelMapper();
		Memo memo = memoRepo.findById(idMemo).orElseThrow(() -> new EntityNotFoundException("Memo no encontrado con ID: " + idMemo));
		memo.setIdEstado(1);
		memo.setUsuarioAprueba(usuarioAprueba);
		memo = memoRepo.saveAndFlush(memo);
		actualizarCantItemOp(memo);

		PropertyMap<Memo, MemoDTO> memoMap = new PropertyMap<Memo, MemoDTO>() {
            @Override
            protected void configure() {
                map().setIdUsuario(source.getUsuarioCrea().getId());
                map().setId(source.getId());
            }
        };
        mapper.addMappings(memoMap);
        return mapper.map(memo, MemoDTO.class);
	}

	private void actualizarCantItemOp(Memo memo) {
		for (MemoDetalle detalle : memo.getDetalles()) {
            ItemOp item = detalle.getItemOp();
            //Double cantidadOriginal = item.getCant();
            
            if ("adicionar".equalsIgnoreCase(detalle.getOperacion())) {
                item.setCant(item.getCant() + detalle.getCantidad());
            } else if ("restar".equalsIgnoreCase(detalle.getOperacion())) {
                item.setCant(item.getCant() - detalle.getCantidad());
            }
            
            itemOpRepo.save(item);
        }
	}

	@Override
	public List<MemoWithOP> obtenerMemos() {
		return memoRepo.buscarTodos();
	}

	@Override
	public Page<MemoWithOP> obtenerMemosPaginados(String keyword, Pageable pageable) {
        return memoRepo.findAllWithSearch(keyword, pageable);
    }

}
