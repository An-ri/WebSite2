package com.example.securingweb.login;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.securingweb.persistence.model.User;
import com.example.securingweb.registration.IUserService;
import com.example.securingweb.registration.UserDto;

public class LoginController {

/*	@Autowired 
	private IUserService userService;
	
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
    
    @PostMapping("/doLogin")
    public ModelAndView loginUser(
            @ModelAttribute("user") @Valid UserDto userDto,
            HttpServletRequest request, Errors errors) {
    		
    	
    	System.out.println(userDto.getFirstName());
    	//System.out.println(userService.repository);
        User registered = userService.registerNewUserAccount(userDto);
        System.out.println(registered.getFirstName());

        return new ModelAndView("successRegister", "user", userDto);
    } */
}
