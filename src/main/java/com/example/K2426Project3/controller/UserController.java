package com.example.K2426Project3.controller;

import com.example.K2426Project3.model.User;
import com.example.K2426Project3.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(Model model, HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer checkR = (Integer) session.getAttribute("checkR");
        String mess1 = "";
        if (checkR != null && checkR == 2) {
            mess1 = "Tai khoan da ton tai";
        }
        session.setAttribute("checkR", 1);
        model.addAttribute("user", new User());
        model.addAttribute("mess1", mess1);
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {

            userService.register(user);
        } catch (Exception e) {
            session.setAttribute("checkR", 2);
            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model,HttpServletResponse response,HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer checkL = (Integer) session.getAttribute("checkL");
        String mess2 = "";
        if (checkL != null && checkL == 2) {
            mess2 = "Tai khoan hoac mat khau khong hop le";
        }
        session.setAttribute("checkR", 1);
        model.addAttribute("mess2", mess2);
        return "user/login";
    }
    @PostMapping("/login")
    public String loginPost(@RequestParam String username, @RequestParam String password, HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = userService.findByUsernameAndPassword(username,password);
        if (user == null){
            session.setAttribute("checkL", 2);
            return "redirect:/login";
        }
        session.setAttribute("name",user.getName());
        session.setMaxInactiveInterval(15);
        return "redirect:/product";
    }

    @GetMapping("/logout")
    public String logout(Model model,HttpServletResponse response,HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(0);
        session.removeAttribute("name");
        return "user/logout";
    }
}
