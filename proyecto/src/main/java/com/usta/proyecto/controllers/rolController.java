package com.usta.proyecto.controllers;

import com.usta.proyecto.entities.RolEntity;
import com.usta.proyecto.models.service.Idatos_LoginService;
import com.usta.proyecto.models.service.IrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
public class rolController {
    @Autowired
    private IrolService irolService;
    @Autowired
    private Idatos_LoginService idatos_loginService;

    @GetMapping("/ListarRoles")
    public String ListarRoles(Model model) {
        model.addAttribute("Roles", irolService.findAll());
        model.addAttribute("titulos", "Listado Roles");
        return "/Listar/ListarRoles";
    }

    @GetMapping("/crearRol")
    public String crearRol(Model model) {
        model.addAttribute("titulo", "Crear Roles");
        model.addAttribute("rol", new RolEntity());
        model.addAttribute("datos_login",idatos_loginService.findAll());
        return "Crear/crearRol";
    }

    @PostMapping(value = "/crearRol")
    public String guardarRol(@Valid RolEntity rol, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "error/error";
        }
        irolService.save(rol);
        status.setComplete();
        return "redirect:/ListarRoles";
    }

    @RequestMapping(value = "/eliminarRol/{id}")
    public String eliminarById(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            irolService.remove(id);
        } else {
            return "redirect:/error500";
        }
        return "redirect:/ListarRoles";
    }

    @GetMapping("/editarRol/{id}")
    public String mostrarFormularioRol(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("titulo", "Editar Rol");
        model.addAttribute("rolActualizar", irolService.findOne(id));
        model.addAttribute("datos_login",idatos_loginService.findAll());
        return "Editar/editarRol";
    }

    @PostMapping("editarRol/{id}")
    public String actualizarRol(@PathVariable(value = "id") Long id, @ModelAttribute("rolActualizar") RolEntity rolActualizar) {
        RolEntity rolExistente = irolService.findOne(id);
        rolExistente.setNombre_rol(rolActualizar.getNombre_rol());
        irolService.updateRol(rolExistente);
        return "redirect:/ListarRoles";
    }
}
