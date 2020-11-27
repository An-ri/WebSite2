package com.example.securingweb.registration;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.example.securingweb.exceptions.UserAlreadyExistException;
import com.example.securingweb.persistence.model.User;

@Controller
public class RegistrationController {


    @GetMapping("/register")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

   /* public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto userDto,
            HttpServletRequest request, Errors errors) {

    }*/
    
    @ModelAttribute(value = "myEntity")
    public UserDto newEntity()
    {
        return new UserDto();
    }
    
    @RequestMapping(value = "/successRegister", method = RequestMethod.POST)
    public View action(Model model, @ModelAttribute("myEntity") User myEntity)
    {
        // save the entity or do whatever you need

        return new RedirectView("/user/ranks");
    }
    
    @RequestMapping(value = "/successRegister", method = RequestMethod.GET)
    public ModelAndView getRanks(Model model, HttpServletRequest request)
    {
        String view = "the-view-name";
        return new ModelAndView(view, "command", model);
    }
    
    
    @PostMapping("/register")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto userDto,
            HttpServletRequest request, Errors errors) {
    		
    	
    	System.out.println("On est passé par là!");
       /* try {
            User registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }*/

        return new ModelAndView("successRegister", "user", userDto);
    }
}