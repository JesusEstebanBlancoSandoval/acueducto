package com.usta.proyecto.controllers;

import com.usta.proyecto.entities.Datos_Login_Entity;
import com.usta.proyecto.entities.RolEntity;
import com.usta.proyecto.models.service.IPersonasService;
import com.usta.proyecto.models.service.Idatos_LoginService;
import com.usta.proyecto.models.service.IrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
public class datos_loginController {
    @Autowired
    private Idatos_LoginService idatos_loginService;

    @Autowired
    private IPersonasService iPersonasService;
    @Autowired
    private IrolService irolService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/ListarDatos_login")
    public String ListarDatos_login(Model model) {
        model.addAttribute("Datos_login", idatos_loginService.findAll());
        model.addAttribute("titulos", "Listado Datos Login");
        return "/Listar/ListarDatos_login";
    }

    @GetMapping("/crearDatos_login")
    public String crearDatos_login(Model model) {
        model.addAttribute("titulo", "Crear Datos login");
        model.addAttribute("datos_login", new Datos_Login_Entity());
        model.addAttribute("personas", iPersonasService.selectOnePer());
        return "Crear/crearDatos_login";
    }

    @PostMapping(value = "/crearDatos_login")
    public String guardarDatos_login(@Valid Datos_Login_Entity datos_login, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "error/error";
        }
        datos_login.setEstado(true);
        datos_login.setPassword(passwordEncoder.encode(datos_login.getPassword()));
        RolEntity rol = new RolEntity();
        rol.setNombre_rol("ROLE_Usuario");
        rol.setId_datos_login(datos_login);
        datos_login.addAuthority(rol);
        status.setComplete();
        idatos_loginService.save(datos_login);
        return "redirect:/ListarDatos_login";
    }

    @RequestMapping(value = "/eliminarDatos_login/{id}")
    public String eliminarById(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            idatos_loginService.remove(id);
        } else {
            return "redirect:/error500";
        }
        return "redirect:/ListarDatos_login";
    }
    @RequestMapping(value = "/cambiarEstadoDatos_Login/{id}")
    public String cambiarEstadodatos_login(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            idatos_loginService.changeState(id);
        } else {
            return "redirect:/error500";
        }
        return "redirect:/ListarDatos_login";
    }

    @GetMapping("/editarDatos_login/{id}")
    public String mostrarFormularioDatos_login(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("titulo", "Editar Datos Login");
        model.addAttribute("datos_loginActualizar", idatos_loginService.findOne(id));
        model.addAttribute("personas", iPersonasService.selectOnePer());
        return "Editar/editarDatos_login";
    }

    @PostMapping("editarDatos_login/{id}")
    public String actualizarDatos_login(@PathVariable(value = "id") Long id, @ModelAttribute("datos_loginActualizar") Datos_Login_Entity datos_loginActualizar) {
        Datos_Login_Entity datos_loginExistente = idatos_loginService.findOne(id);
        datos_loginExistente.setCorreo(datos_loginActualizar.getCorreo());
        datos_loginExistente.setPassword(passwordEncoder.encode(datos_loginActualizar.getPassword()));
        datos_loginExistente.setId_persona(datos_loginActualizar.getId_persona());
        idatos_loginService.updateDatosLogin(datos_loginExistente);
        return "redirect:/ListarDatos_login";
    }



}
