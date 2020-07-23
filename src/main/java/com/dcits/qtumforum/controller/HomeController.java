package com.dcits.qtumforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/22 13:49
 */

@Controller
public class HomeController {
    @GetMapping("/home")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "column", defaultValue = "2") Integer column2
                        //@RequestParam(name = "size", defaultValue = "15") Integer size,
                        //@RequestParam(name = "search", defaultValue = "") String search
                        ) {
        model.addAttribute("column", column2);
        model.addAttribute("page", page);
        model.addAttribute("navtype", "homenav");

        return "home/index";
    }

    @GetMapping("/home/message")
    public String message(Model model) {
        model.addAttribute("navtype", "msgnav");
        return "home/message";
    }

}
