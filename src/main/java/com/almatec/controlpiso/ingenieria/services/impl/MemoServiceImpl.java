package com.almatec.controlpiso.ingenieria.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.ingenieria.MemoWithOP;
import com.almatec.controlpiso.ingenieria.dtos.MemoDTO;
import com.almatec.controlpiso.ingenieria.services.MemoService;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.entities.Memo;
import com.almatec.controlpiso.integrapps.entities.MemoDetalle;
import com.almatec.controlpiso.integrapps.repositories.ItemOpRepository;
import com.almatec.controlpiso.integrapps.repositories.MemoRepository;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.services.UsuarioService;

@Service
public class MemoServiceImpl implements MemoService {
	
	@Autowired
	private MemoRepository memoRepo;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ItemOpRepository itemOpRepo;

	@Transactional
	@Override
	public MemoDTO guardarMemo(MemoDTO memoDTO) {
		try {
			ModelMapper mapper = new ModelMapper();
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
			/*memoSaved.getDetalles().forEach(detalle->{
				ItemOp item = detalle.getItemOp();
				if(detalle.getOperacion().equalsIgnoreCase("adicionar")) {
					item.setCant(item.getCant() + detalle.getCantidad());					
				}
				if(detalle.getOperacion().equalsIgnoreCase("restar")) {
					item.setCant(item.getCant() - detalle.getCantidad());					
				}
				itemOpRepo.save(item);
			});*/
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
	public List<MemoDetalle> obtenerDetalleMemo(Long idMemo) {
		Memo memo = memoRepo.findById(idMemo).orElseThrow();
		return memo.getDetalles();
	}

	@Override
	public MemoDTO aprobarMemo(Long idMemo, Usuario usuarioAprueba) {
		ModelMapper mapper = new ModelMapper();
		Memo memo = memoRepo.findById(idMemo).orElseThrow();
		memo.setIdEstado(1);
		memo.setUsuarioAprueba(usuarioAprueba);
		memo = memoRepo.saveAndFlush(memo);
		PropertyMap<Memo, MemoDTO> memoMap = new PropertyMap<Memo, MemoDTO>() {				
			@Override
			protected void configure() {
				map().setIdUsuario(source.getUsuarioCrea().getId());;
				map().setId(source.getId());;					
			}
		};
		mapper.addMappings(memoMap);
		return mapper.map(memo, MemoDTO.class);
	}

}