package com.almatec.controlpiso.ingenieria.services;

import java.util.List;

import com.almatec.controlpiso.ingenieria.MemoWithOP;
import com.almatec.controlpiso.ingenieria.dtos.MemoDTO;
import com.almatec.controlpiso.integrapps.entities.MemoDetalle;
import com.almatec.controlpiso.security.entities.Usuario;

public interface MemoService {

	MemoDTO guardarMemo(MemoDTO memoDTO);

	List<MemoWithOP> obtenerMemosSinAprobar();

	List<MemoDetalle> obtenerDetalleMemo(Long idMemo);

	MemoDTO aprobarMemo(Long idMemo, Usuario usuario);

}
