package com.example.app.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        model.addAttribute("status", request.getAttribute("jakarta.servlet.error.status_code"));
        model.addAttribute("uri", request.getAttribute("jakarta.servlet.error.request_uri"));
        
        Exception exception = (Exception) request.getAttribute("jakarta.servlet.error.exception");
        model.addAttribute("exception", exception != null ? exception.toString() : "Non disponible");
        
        return "error";
    }
}