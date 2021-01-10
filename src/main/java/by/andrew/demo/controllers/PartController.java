package by.andrew.demo.controllers;

import by.andrew.demo.entity.parts.Part;
import by.andrew.demo.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/part")
public class PartController {
    @Autowired
    private PartService partService;
@GetMapping(path = "/add")
public ModelAndView add(ModelAndView modelAndView){
    modelAndView.setViewName("addPart");
    modelAndView.addObject("part", new Part());
    return modelAndView;
}
    @PostMapping(path = "/add")
    public ModelAndView add(@Valid @ModelAttribute("part") Part part, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("addPart");
        } else {
            modelAndView.setViewName("redirect:/");
            partService.add(part);
        }
        return modelAndView;


    }

}
