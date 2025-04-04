package com.almatec.controlpiso.produccion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.almatec.controlpiso.integrapps.entities.Parada;
import com.almatec.controlpiso.integrapps.services.ParadaService;
import com.almatec.controlpiso.produccion.dtos.ParadaDTO;
import com.almatec.controlpiso.produccion.service.ParadaPdfService;
import com.google.zxing.WriterException;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/paradas")
public class ParadaController {

    @Autowired
    private ParadaService paradaService;
    
    @Autowired
    private ParadaPdfService paradaPdfService;

    @GetMapping
    public ResponseEntity<List<Parada>> obtenerParadas() {
        return ResponseEntity.ok(paradaService.obtenerParadas());
    }

    @GetMapping("/listar")
    public String listarParadas(Model model) {
        model.addAttribute("paradas", paradaService.obtenerParadas());
        return "produccion/paradas/lista";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        ParadaDTO parada = new ParadaDTO();
        parada.setIsActivo(true);
        model.addAttribute("parada", parada);
        return "produccion/paradas/formulario";
    }

    @PostMapping("/crear")
    public String crearParada(@Valid @ModelAttribute("parada") ParadaDTO paradaDTO,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "produccion/paradas/formulario";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        paradaService.crear(paradaDTO, auth.getName());
        redirectAttributes.addFlashAttribute("mensaje", "Parada creada exitosamente");
        return "redirect:/paradas/listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Parada parada = paradaService.obtenerPorId(id);
        ParadaDTO paradaDTO = new ParadaDTO();
        BeanUtils.copyProperties(parada, paradaDTO);
        model.addAttribute("parada", paradaDTO);
        return "produccion/paradas/formulario";
    }

    @PostMapping("/editar/{id}")
    public String actualizarParada(@PathVariable Long id,
                                  @Valid @ModelAttribute("parada") ParadaDTO paradaDTO,
                                  BindingResult result,
                                  RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "produccion/paradas/formulario";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        paradaService.actualizar(id, paradaDTO, auth.getName());
        redirectAttributes.addFlashAttribute("mensaje", "Parada actualizada exitosamente");
        return "redirect:/paradas/listar";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarParada(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        paradaService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Parada eliminada exitosamente");
        return "redirect:/paradas/listar";
    }
    
    @GetMapping("/exportar-pdf")
	public void generarBarCodeOperarios(HttpServletResponse response) throws DocumentException, IOException, WriterException {
    	paradaPdfService.generarBarCodeParadas(response);
    }
}