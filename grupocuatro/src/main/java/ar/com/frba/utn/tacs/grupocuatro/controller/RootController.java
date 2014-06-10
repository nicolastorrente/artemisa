package ar.com.frba.utn.tacs.grupocuatro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

	@RequestMapping("/")
    public String index(Model model) {
        return "index";
    }
}
