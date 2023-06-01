package com.usta.proyecto.controllers;

import com.usta.proyecto.entities.PagosEntity;
import com.usta.proyecto.models.service.IRegistrosService;
import com.usta.proyecto.models.service.IpagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
public class PagosController {
    @Autowired
    private IpagosService ipagosService;


    @Autowired
    private IRegistrosService iRegistrosService;

    @GetMapping("Listar/ListarPagos")
    public String listarPagos (Model model){
        model.addAttribute("PagosTabla",ipagosService.findAll());
        return "Listar/ListarPagos";
    }

    @GetMapping("/Crear/pagosCrear")
    public String crearPagos(Model model){
        model.addAttribute("titulo","crear Detalle");
        model.addAttribute("pago",new PagosEntity());
        model.addAttribute("registros",iRegistrosService.findAll());
        return "/Crear/pagosCrear";

    }

    @PostMapping(value = "/Crear/pagosCrear")
    public String guardarPagos(@Valid PagosEntity detalles, BindingResult result, SessionStatus status){
        if(result.hasErrors()){
            return "error500";
        }
        detalles.setEstado(true);
        ipagosService.save(detalles);
        status.setComplete();
        return "redirect:/Listar/ListarPagos";
    }

    @RequestMapping(value="/eliminarPagos/{id}")
    public String eliminarById(@PathVariable(value = "id")Long id){
        if(id>0){
            ipagosService.remove(id);
        }else{
            return "redirect:/error500";
        }
        return "redirect:/Listar/ListarPagos";

    }
    @RequestMapping(value = "/cambiarEstadoDetalles/{id}")
    public String cambiarEstadoPagos(@PathVariable(value = "id")Long id){
        if(id>0){
            ipagosService.changeState(id);
        }else{
            return "redirect:/error500";
        }
        return "redirect:/listarDetalles";
    }
    @GetMapping("/editarPagos/{id}")
    public String mostrarFormularioPagos(
            @PathVariable(value = "id")Long id, Model model){
        model.addAttribute("titulo","editar Detalle");
        model.addAttribute("pagos",ipagosService.findOne(id));
        model.addAttribute("registros",iRegistrosService.findAll());
        return "Editar/editarPagos";

    }

    @PostMapping("editarPagos/{id}")
    public String actualizarPago(
            @PathVariable(value = "id") Long id,
            @ModelAttribute("detalleActualizar")
            PagosEntity detalles
    ){
        PagosEntity detalleExistente =
                ipagosService.findOne(id);
        detalleExistente.setEstado(true);
        detalleExistente.setMedicionActual(detalles.getMedicionActual());
        detalleExistente.setCostoaPagar(detalles.getCostoaPagar());//en esta parte se debe hacer la regla de tres para que al poner la medicion se calcule el costo a pagar//
        detalleExistente.setMedicionPasada(detalles.getMedicionPasada());
        detalleExistente.setCostoPasado(detalles.getCostoPasado());
        detalleExistente.setDeudaTotal(detalles.getDeudaTotal());
        ipagosService.updatePagos(detalleExistente);
        return "redirect:/Listar/ListarPagos";

    }

}
