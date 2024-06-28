package com.almatec.controlpiso.ingenieria.remove;

public class Remove {
/*
	@GetMapping("/listar-ops")
	public String listarOps(Model modelo, @Param("keyword") String keyword) {
		List<VistaPedidosOpErp> pedidosOp = null;
		if(keyword == null) {
			pedidosOp = vistaPedidosOpErpService.buscarOps();
		}else {
			pedidosOp = vistaPedidosOpErpService.buscarOps(keyword);			
		}
		
		modelo.addAttribute("pedidosOp", pedidosOp); 
		return "ingenieria/listar-ops";
	}
	
	@GetMapping("/op/{idOp}") //detalle
	public String verDetalleOp(@PathVariable String idOp, Model modelo) {
		List<ItemOp> listaItemOp = itemOpService.obtenerItemsOp(Integer.valueOf(idOp));
		modelo.addAttribute("listaItemOp", listaItemOp);
		modelo.addAttribute("idOp", idOp);
		return "ingenieria/op-grupo2";
	}
	
	@GetMapping("/op/grupo/{idGrupo}") //Primer nivel
	public String verDetalleOpNivel1(@PathVariable String idGrupo,
									Model modelo) {
		List<ItemOp> listaItemOp = itemOpService.obtenerItemsOpC2(idGrupo);
		modelo.addAttribute("listaItemOp", listaItemOp);
		return "ingenieria/op-grupo1";
	}
	*/
}
