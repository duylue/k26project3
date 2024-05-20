package com.example.K2426Project3.controller;

import com.example.K2426Project3.model.Customer;
import com.example.K2426Project3.model.Zone;
import com.example.K2426Project3.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String get(Model model){
        List<Customer> list = customerService.list();
        model.addAttribute("list",list);
        return "customer/list";
    }

    @GetMapping("/detail")
    public String detail(Model model,@RequestParam int cid){
        Customer customer = customerService.findById(cid);
        model.addAttribute(customer);
        return "customer/profile";
    }
    @GetMapping("/edit")
    public String edit(Model model,@RequestParam int cid){
        Customer customer = customerService.findById(cid);
        List<Zone> zones = customerService.listZone();
        model.addAttribute("customer",customer);
        model.addAttribute("listZone" ,zones);
        return "customer/save";
    }
    @GetMapping("/delete")
    public String delete(Model model,@RequestParam int cid){
        customerService.delete(cid);
        return "customer/delete";
    }
    @GetMapping("/create")
    public String create(Model model){
        List<Zone> zones = customerService.listZone();
        model.addAttribute("customer",new Customer());
        model.addAttribute("listZone" ,zones);
        return "customer/save";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Customer customer){
        customerService.save(customer);
        return "redirect:/customer";
    }

}
