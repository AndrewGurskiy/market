package by.andrew.demo.controllers;

import by.andrew.demo.dto.UserAuthDTO;
import by.andrew.demo.entity.user.User;
import by.andrew.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping(path = "/registration")
    public ModelAndView userRegistration(ModelAndView modelAndView) {
        modelAndView.setViewName("registration");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping(path = "/registration")
    public ModelAndView userRegistration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            modelAndView.setViewName("redirect:/");
            userService.save(user);
        }
        return modelAndView;
    }

    @GetMapping(path = "/authorization")
    public ModelAndView userAuthorization(ModelAndView modelAndView) {
        modelAndView.setViewName("authorization");
        modelAndView.addObject("user", new UserAuthDTO());
        return modelAndView;
    }
    @PostMapping(path = "/authorization")
    public ModelAndView userAuthorization(@Valid @ModelAttribute("user") UserAuthDTO userAuthDTO, BindingResult bindingResult, ModelAndView modelAndView, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("authorization");
        } else {
            User userByLogin = userService.getByUsername(userAuthDTO.getUsername());
            if (userByLogin != null) {
                if (userByLogin.getPassword().equals(userAuthDTO.getPassword())) {
                    httpSession.setAttribute("user", userByLogin);
                }
            }
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }
    @GetMapping(path = "/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession httpSession) {
        httpSession.invalidate();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}
