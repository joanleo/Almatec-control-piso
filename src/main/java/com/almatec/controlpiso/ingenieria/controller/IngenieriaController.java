	package com.almatec.controlpiso.ingenieria.controller;
	
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

	import com.almatec.controlpiso.integrapps.dtos.ItemOpDatable;
	import com.almatec.controlpiso.integrapps.entities.ItemOp;
	import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
	import com.almatec.controlpiso.integrapps.paging.PageArray;
	import com.almatec.controlpiso.integrapps.paging.PagingRequest;
	import com.almatec.controlpiso.integrapps.services.ItemOpService;
	import com.almatec.controlpiso.integrapps.services.OrdenPvService;
	import com.almatec.controlpiso.utils.ReporteOpsPdf;
	import com.lowagie.text.DocumentException;
	
	@Controller
	@RequestMapping("/ingenieria")
	public class IngenieriaController {		
		
		@Autowired
		private ItemOpService itemOpService;
		
		@Autowired
		private OrdenPvService ordenPvService;	
		
		
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
			List<VistaOrdenPv> proyectos = null;
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
