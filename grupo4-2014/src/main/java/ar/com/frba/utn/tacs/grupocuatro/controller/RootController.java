package ar.com.frba.utn.tacs.grupocuatro.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

	@RequestMapping("/")
    public String index(Model model) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        model.addAttribute("date", formatter.format(date));
        return "index";
    }
}
