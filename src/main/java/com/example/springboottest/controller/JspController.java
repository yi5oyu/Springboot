package com.example.springboottest.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JspController {
    @Autowired
    private ServletContext context;

    @GetMapping("/jsp")
    public String jsp(HttpServletRequest request, HttpSession session, Model model){
        // Scope
        request.setAttribute("request", "리퀘스트");
        session.setAttribute("session", "세션");
        context.setAttribute("application", "어플리케이션");
        // HttpServletRequest 사용됨
        model.addAttribute("model", "모델");
        return "jsp";
    }

    @GetMapping("/page")
    public String page(){
        return "jsp";
    }

    @GetMapping("/redirect")
    public String redirect(HttpServletRequest request, HttpSession session, Model model) {
        request.setAttribute("request", "리퀘스트-리다이렉트");
        session.setAttribute("session", "세션-리다이렉트");
        context.setAttribute("application", "어플리케이션-리다이렉트");
        model.addAttribute("model", "모델-리다이렉트");
        return "redirect:/jsp";
    }

    @GetMapping("/forward")
    public String forward(HttpServletRequest request, HttpSession session, Model model) {
        request.setAttribute("request", "리퀘스트-포워드");
        session.setAttribute("session", "세션-포워드");
        context.setAttribute("application", "어플리케이션-포워드");
        model.addAttribute("model", "모델-포워드");
        return "forward:/jsp";
    }
}