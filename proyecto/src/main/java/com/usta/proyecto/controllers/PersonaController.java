package com.usta.proyecto.controllers;


import com.usta.proyecto.entities.PersonasEntity;
import com.usta.proyecto.models.service.IPersonasService;
import com.usta.proyecto.models.service.IRegistrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
public class PersonaController {

    @Autowired
    private IPersonasService iPersonasService;

    @Autowired
    private IRegistrosService iRegistrosService;


    @GetMapping("/Listar/ListarPersonas")
    public String ListarSeccionales (Model model){
        model.addAttribute("personasTabla",iPersonasService.findAll());
        return "Listar/ListarPersonas";
    }

    @GetMapping("/Crear/personasCrear")
    public String crearPersona(Model model){
        model.addAttribute("titulo","Crear Persona");
        model.addAttribute("persona",new PersonasEntity());
        model.addAttribute("registros", iRegistrosService.findAll());
        return "/Crear/personasCrear";
    }

    @PostMapping(value = "Crear/personasCrear")
    public String GuardarPersona(@Valid PersonasEntity personas, BindingResult result, SessionStatus status){
        if(result.hasErrors()){
            return "error500";
        }
        personas.setEstado(true);
        personas.setFuncionario(false);
        iPersonasService.save(personas);
        status.setComplete();
        return"redirect:/Listar/ListarPersonas";
    }

    @RequestMapping(value ="/eliminarPersona/{id}")
    public String eliminarById(@PathVariable(value="id")Long id){
        if(id>0){
            iPersonasService.remove(id);
        }else{
            return "redirect:/error500";
        }
        return "redirect:/ListarPersonas";
    }

    @RequestMapping(value = "gestorTablas/cambiarEstadoPersona/{id}")
    public String cambiarEstadoPersona(@PathVariable(value = "id")Long id){
        if(id>0){
            iPersonasService.changeState(id);
        }else{
            return "redirect:/error500";
        }
        return "redirect:/ListarPersonas";
    }


    @GetMapping("/editarPersona/{id}")
    public String mostarFormularioPersona(@PathVariable(value="id") Long id,Model model){
        model.addAttribute("titulo","editar Persona");
        model.addAttribute("personaActualizar",iPersonasService.findOne(id));
        model.addAttribute("registros", iRegistrosService.findAll());
        return "Editar/editarPersona";

    }
    @PostMapping("editarPersona/{id}")
    public String actualizarPersona(@PathVariable(value = "id") Long id, @ModelAttribute("personaActualizar") PersonasEntity personas){
        PersonasEntity personaExistente = iPersonasService.findOne(id);
        personaExistente.setEstado(true);
        personaExistente.setNombre(personas.getNombre());
        personaExistente.setApellido(personas.getApellido());
        personaExistente.setCedula(personas.getCedula());
        personaExistente.setEstrato(personas.getEstrato());
        personaExistente.setPuntosDeAgua(personas.getPuntosDeAgua());
        personaExistente.setTelefono(personas.getTelefono());
        iPersonasService.updatePersonas(personas);
        return "redirect:/Listar/ListarPersonas";
    }

}
