package com.usta.proyecto.controllers;

import com.usta.proyecto.entities.Datos_Login_Entity;
import com.usta.proyecto.entities.PersonasEntity;
import com.usta.proyecto.entities.RolEntity;
import com.usta.proyecto.models.service.IPersonasService;
import com.usta.proyecto.models.service.Idatos_LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.util.Map;

@Controller
public class signupController {
    @Autowired
    private IPersonasService iusuarioService; // Usuario

    @Autowired
    private Idatos_LoginService idatos_loginService; // Datos login

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @RequestMapping(value = "/signup")
    public String crearUsuario(Map<String, Object> model) {
        PersonasEntity usuario = new PersonasEntity();
        model.put("usuario", usuario);
        model.put("titulo", "Crear Datos personales usuario");
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String guardar(@Valid PersonasEntity usuario,
                          BindingResult result,
                          Model model,
                          RedirectAttributes flash,
                          SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Crear datos personales Usuario");
            return "signup";
        }
        String mensajeFlash = (usuario.getId_persona() != null) ? "Docente editado con éxito" : "Docente creado con éxito";
        usuario.setEstado(true);
        iusuarioService.save(usuario);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/signupdos";
    }

    @RequestMapping(value = "/signupdos")
    public String crearDatos_login(Map<String, Object> model) {
        Datos_Login_Entity datos_login = new Datos_Login_Entity();
        model.put("usuarioRegistrar", datos_login);
        model.put("titulo", "Crear datos de logeo del usuario");
        return "signupdos";
    }

    @RequestMapping(value = "/signupdos", method = RequestMethod.POST)
    public String crearUsuarioDos(@Valid Datos_Login_Entity datos_login, BindingResult result, Model model, RedirectAttributes flash,
                                  SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Crear datos logeo usuario");
            return "signupdos";
        }
        datos_login.setId_persona(iusuarioService.findOne(iusuarioService.findLastCorreo().getId_persona()));
        datos_login.setPassword(passwordEncoder.encode(datos_login.getPassword()));
        datos_login.setEstado(true);
        RolEntity rol = new RolEntity();
        rol.setNombre_rol("ROLE_Persona");
        rol.setId_datos_login(datos_login);
        datos_login.addAuthority(rol);
        idatos_loginService.save(datos_login);
        status.setComplete();
        return "redirect:/login";
    }
}
