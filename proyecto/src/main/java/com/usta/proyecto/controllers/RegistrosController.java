package com.usta.proyecto.controllers;

import com.usta.proyecto.entities.RegistrosEntity;
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
public class RegistrosController {
    @Autowired
    private IRegistrosService iRegistrosService;

    @Autowired
    private IPersonasService iPersonasService;



    @GetMapping("Listar/ListarRegistros")
    public String listarRegistros(Model model){
        model.addAttribute("registrosTabla",iRegistrosService.findAll());
        return "Listar/ListarRegistros";
    }

    @GetMapping("Crear/registrosCrear")
    public String crearRegistro(Model model){
        model.addAttribute("titulo","crear registro");
        model.addAttribute("registro",new RegistrosEntity());
        model.addAttribute("personas",iPersonasService.findAll());
        return "Crear/registrosCrear";
    }
    @PostMapping(value = "Crear/registrosCrear")
    public String guardarRegistro(@Valid RegistrosEntity registros, BindingResult result, SessionStatus status){
        if(result.hasErrors()){
            return "error500";
        }
        registros.setEstado(true);
        iRegistrosService.save(registros);
        status.setComplete();
        return "redirect:/Listar/ListarRegistros";
    }
    @RequestMapping(value ="/eliminarRegistro/{id}")
    public String eliminarById(@PathVariable(value="id")Long id){
        if(id>0){
            iRegistrosService.remove(id);
        }else{
            return "redirect:/error500";
        }
        return "redirect:/Listar/ListarRegistros";
    }
    @RequestMapping(value = "/cambiarEstadoRegistros/{id}")
    public String cambiarEstadoSeccional(@PathVariable(value = "id")Long id){
        if(id>0){
            iRegistrosService.changeState(id);
        }else{
            return "redirect:/error500";
        }
        return "redirect:/ListarDetalles";
    }


    @GetMapping("/editarRegistros/{id}")
    public String mostrarFormularioRegistro(
            @PathVariable(value = "id") Long id,Model model){
        model.addAttribute("titulo","editar Registros");
        model.addAttribute("registroActualizar",iRegistrosService.findOne(id));
        model.addAttribute("personas",iPersonasService.findAll());
        return "Editar/editarRegistros";
    }

    @PostMapping("editarRegistro/{id}")
    public String actualizarRegistro(
            @PathVariable(value = "id") Long id,
            @ModelAttribute("registroActualizar")
            RegistrosEntity registro

    ){
        RegistrosEntity registroExistente =
                iRegistrosService.findOne(id);
        registroExistente.setEstado(true);
        registroExistente.setFechaMedicion(registro.getFechaMedicion());
        registroExistente.setId_persona(registro.getId_persona());
        iRegistrosService.updateRegistro(registroExistente);
        return "redirect:Listar/ListarRegistros";
    }

}
