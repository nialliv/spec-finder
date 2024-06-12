package ru.aprtemev.specfinder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class PrinterWebController {

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("tasks", Arrays.asList("1", "2", "3"));
        return "index";
    }
}
