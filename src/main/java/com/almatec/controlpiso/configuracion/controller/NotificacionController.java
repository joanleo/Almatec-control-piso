package com.almatec.controlpiso.configuracion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.almatec.controlpiso.comunicador.dtos.TipoNotificacionDTO;
import com.almatec.controlpiso.comunicador.services.impl.TipoNotificacionService;

@Controller
@RequestMapping("/notificaciones")
public class NotificacionController {

	@Autowired
    private TipoNotificacionService service;
    
    @GetMapping
    public String showConfigPage(Model model) {
        model.addAttribute("configs", service.getAllConfigs());
        model.addAttribute("newConfig", new TipoNotificacionDTO());
        return "configuracion/notificaciones/listar-notificaciones";
    }
    
    @PostMapping
    public String saveConfig(@ModelAttribute TipoNotificacionDTO config) {
        service.saveConfig(config);
        return "redirect:/notificaciones";
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConfig(@PathVariable Integer id) {
        service.deleteConfig(id);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}/email/{email}")
    public ResponseEntity<?> deleteEmail(@PathVariable Integer id, @PathVariable String email) {
    	TipoNotificacionDTO config = service.getConfig(id);
        config.getEmails().remove(email);
        service.saveConfig(config);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TipoNotificacionDTO> getConfig(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getConfig(id));
    }
}
