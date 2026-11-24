package com.madrianzen.clientes.controller;

import com.madrianzen.clientes.model.Cliente;
import com.madrianzen.clientes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClienteWebController {

    private final ClienteService service;

    public ClienteWebController(ClienteService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/clientes";
    }

    @GetMapping("/clientes")
    public String lista(Model model) {
        model.addAttribute("clientes", service.listar());
        model.addAttribute("cliente", new Cliente());
        return "clientes";
    }

    @PostMapping("/clientes")
    public String registrar(@Valid @ModelAttribute("cliente") Cliente cliente,
                            BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("clientes", service.listar());
            return "clientes";
        }
        cliente.setId(null);
        service.guardar(cliente);
        ra.addFlashAttribute("mensaje", "Cliente registrado correctamente");
        return "redirect:/clientes";
    }

    @PostMapping("/clientes/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        service.eliminar(id);
        ra.addFlashAttribute("mensaje", "Cliente eliminado");
        return "redirect:/clientes";
    }
}
