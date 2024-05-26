	package com.almatec.controlpiso.controllers;
	
	import java.io.IOException;
	import java.text.DateFormat;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	import java.util.List;
	
	import javax.servlet.http.HttpServletResponse;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.data.repository.query.Param;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.ResponseBody;
	import org.springframework.web.servlet.mvc.support.RedirectAttributes;

	import com.almatec.controlpiso.erp.webservices.XmlService;
import com.almatec.controlpiso.integrapps.dtos.ItemOpDatable;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
	import com.almatec.controlpiso.integrapps.entities.OrdenPv;
	import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;
	import com.almatec.controlpiso.integrapps.entities.VistaPedidosErp;
	import com.almatec.controlpiso.integrapps.entities.VistaPedidosOpErp;
	import com.almatec.controlpiso.integrapps.paging.PageArray;
	import com.almatec.controlpiso.integrapps.paging.PagingRequest;
	import com.almatec.controlpiso.integrapps.services.ItemOpService;
	import com.almatec.controlpiso.integrapps.services.OrdenPvService;
	import com.almatec.controlpiso.integrapps.services.VistaItemPedidoErpService;
	import com.almatec.controlpiso.integrapps.services.VistaPedidosErpService;
	import com.almatec.controlpiso.integrapps.services.VistaPedidosOpErpService;
	import com.almatec.controlpiso.utils.ReporteOpsPdf;
	import com.lowagie.text.DocumentException;
	
	@Controller
	@RequestMapping("/ingenieria")
	public class IngenieriaController {		
		
		@Autowired
		private VistaPedidosOpErpService vistaPedidosOpErpService;
		
		@Autowired
		private ItemOpService itemOpService;
		
		@Autowired
		private OrdenPvService ordenPvService;	
		
		
		
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
		
		@GetMapping("/op/{idOp}/descarga")
		public void exportToPdfOp(HttpServletResponse response,
				@PathVariable String idOp) throws DocumentException, IOException{
			
			response.setContentType("application/pdf");
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String currentDateTime = dateFormatter.format(new Date());
			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=reporteOp_" + idOp + "_" + currentDateTime + ".pdf";
			response.setHeader(headerKey, headerValue);
			
			List<ItemOp> listaItemOp = itemOpService.obtenerItemsOp(Integer.valueOf(idOp));
			ReporteOpsPdf exportar = new ReporteOpsPdf(listaItemOp, itemOpService);
			exportar.export(response);
			
		}
		
		@GetMapping("/status/proyectos")
		public String estadoProyectos(Model modelo, @Param("keyword") String keyword) {
			List<OrdenPv> proyectos = null;
			if(keyword == null) {
				proyectos = ordenPvService.buscarProyectos();				
			}else {
				proyectos = ordenPvService.buscarProyectos(keyword);
			}
			modelo.addAttribute("proyectos", proyectos);
			return "ingenieria/status-proyectos.html";
		}
		
		@PostMapping("/op/{numOp}/detalle")
		@ResponseBody
		public PageArray detalleOp(@RequestBody PagingRequest pagingRequest,
									  @PathVariable Integer numOp) {
			return itemOpService.obtenerItemsOpArray(pagingRequest, numOp);
		}
		
		@GetMapping("/op/{idOp}/detalle")
		@ResponseBody
		public List<ItemOpDatable> detalleOp(@PathVariable Integer idOp) {			
			return itemOpService.obtenerItemsOpDataTable(idOp);
		}
				
	}
