package com.example.app.controller;

import com.example.app.entity.Client;
import com.example.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients") 
public class ClientController {
    
    @Autowired
    private ClientService clientService;
    
    @GetMapping
    public String list(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients/list";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("client", new Client());
        return "clients/add";
    }
    
    @PostMapping("/add")
    public String add(@ModelAttribute Client client) {
        clientService.createClient(client);
        return "redirect:/clients";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Client client = clientService.getClientById(id)
            .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        model.addAttribute("client", client);
        return "clients/edit";
    }
    
    @PostMapping("/update")
    public String update(@ModelAttribute Client client) {
        clientService.updateClient(client.getIdClient(), client);
        return "redirect:/clients";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }
}