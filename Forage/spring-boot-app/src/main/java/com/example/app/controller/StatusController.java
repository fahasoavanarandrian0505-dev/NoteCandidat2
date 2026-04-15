package com.example.app.controller;

import com.example.app.entity.Status;
import com.example.app.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/status")
public class StatusController {
    
    @Autowired
    private StatusService statusService;
    
    @GetMapping
    public String list(Model model) {
        List<Status> statusList = statusService.getAllStatus();
        model.addAttribute("statusList", statusList);
        return "status/list";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("status", new Status());
        return "status/add";
    }
    
    @PostMapping("/add")
    public String add(@ModelAttribute Status status, RedirectAttributes redirectAttributes) {
        try {
            statusService.createStatus(status);
            redirectAttributes.addFlashAttribute("success", "Statut créé avec succès");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la création du statut");
        }
        return "redirect:/status";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Status status = statusService.getStatusById(id)
            .orElseThrow(() -> new RuntimeException("Statut non trouvé"));
        model.addAttribute("status", status);
        return "status/edit";
    }
    
    @PostMapping("/update")
    public String update(@ModelAttribute Status status, RedirectAttributes redirectAttributes) {
        try {
            statusService.updateStatus(status.getIdStatus(), status);
            redirectAttributes.addFlashAttribute("success", "Statut mis à jour avec succès");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la mise à jour du statut");
        }
        return "redirect:/status";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            statusService.deleteStatus(id);
            redirectAttributes.addFlashAttribute("success", "Statut supprimé avec succès");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression du statut");
        }
        return "redirect:/status";
    }
}
