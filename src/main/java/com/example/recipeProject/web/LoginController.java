package com.example.recipeProject.web;

import java.util.UUID;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.recipeProject.domain.Email;
import com.example.recipeProject.domain.ForgotForm;
import com.example.recipeProject.domain.Login;
import com.example.recipeProject.domain.LoginRepository;
import com.example.recipeProject.domain.PasswordResetToken;
import com.example.recipeProject.domain.PasswordResetTokenRepository;
import com.example.recipeProject.domain.SignupForm;
import com.example.recipeProject.domain.UpdateForm;

@Controller
public class LoginController {

	@Autowired
	private LoginRepository lrepository;
	
	@Autowired
	private PasswordResetTokenRepository prtrepository;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value="signup")
	public String signUp(Model model){
		model.addAttribute("signupform", new SignupForm());
		return "signup";
	}
	
	@RequestMapping(value="saveuser", method=RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // validation errors
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // Check password match		
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	Login newUser = new Login();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
		    	newUser.setEmail(signupForm.getEmail());
		    	if (lrepository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
		    		lrepository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login";    	
    }
	
	@RequestMapping(value="forgotpassword", method=RequestMethod.GET)
	public String forgotPassword(Model model, ForgotForm forgotForm){
		model.addAttribute("forgotform", forgotForm);
		return "forgotpassword";
	}
	
	@RequestMapping(value="resetpassword", method=RequestMethod.POST)
	public String resetPassword(@Valid @ModelAttribute("forgotform") ForgotForm forgotForm, BindingResult bindingResult) {

		if (!bindingResult.hasErrors()) {
			try {
				Login login = lrepository.findByEmail(forgotForm.getEmail());
				if(login != null) {    
				    String token = UUID.randomUUID().toString();
					
				    Date expiryDate = new Date();
				    expiryDate.setTime(expiryDate.getTime() + 86400000); //Pluss 24 Hours!
				    
				    prtrepository.save(new PasswordResetToken(token, login, expiryDate));
				    
				    System.out.println(token.toString());
				    System.out.println(login.getEmail());
				    Email email = new Email();
				    email.sendEmail("noreply.recipe@gmail.com", "Qwerty.789", login.getEmail(), "Reset key", "Secret key: " + "http://localhost:8080/updatepassword/" + token);
				    
			    	return "redirect:/login";
				} else {
					System.out.println("PRINT EEEROORO");
			    	//bindingResult.rejectValue("email", "err.email", "E-mail not found"); 
			    	return "forgotpassword";
				}
			} catch(Exception e){
				System.out.println(e.getMessage());
				System.out.println("User not found!");
		    	//bindingResult.rejectValue("email", "err.email", "E-mail not found");    	
    			return "forgotpassword";
			}
			
	    	
		} else {
    		System.out.println("BINDING ERROR");
    		System.out.println(bindingResult.getAllErrors());
    		return "login";
    	}
	}
	
	@RequestMapping(value = "/updatepassword/{token}", method=RequestMethod.GET)
	public String showUpdatePasswordPage(@PathVariable("token") String token, Model model) {
		
		Boolean result = validatePasswordResetToken(token);
		
		if(result == true) {
			System.out.println("TOKEN OK");
			UpdateForm updateForm = new UpdateForm();
			updateForm.setToken(token);
			model.addAttribute("updateform", updateForm);
			
			return "updatepassword";
		} else if (result == false) {
			System.out.println("TOKEN == FALSE, NOT VALID");
			return "login";
		} else {
			System.out.println("TOKEN == NULL, NOT FOUND");
			return "login";
		}
		
	}
	
	@RequestMapping(value="/changepassword", method = RequestMethod.POST)
	public String changePassword(@Valid @ModelAttribute("updateform") UpdateForm updateForm, 
			Model model, BindingResult bindingResult) {
		
		System.out.println(updateForm.getPassword() + ' ' + updateForm.getToken());
		
		PasswordResetToken token = prtrepository.findByToken(updateForm.getToken());
		System.out.println(token);
		
		Login login = lrepository.findById(token.getLogin().getId());
		System.out.println(login.getUsername());
		
		if(!bindingResult.hasErrors()) {
			Boolean result = validatePasswordResetToken(token.getToken());
		    if(result == null) {
		        System.out.println("Token not found!");
		        
		        return "redirect:/login";
		    } else if(result == false) {
		    	System.out.println("invalid token!");
		        
		        return "redirect:/login";
		    } else {
		    	
		    	if(updateForm.getPassword().equals(updateForm.getPasswordCheck())) {
		    		String pwd = updateForm.getPassword();
			    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			    	String hashPwd = bc.encode(pwd);
			    	
			    	login.setPasswordHash(hashPwd);
			    	
			    	lrepository.save(login);
			    	System.out.println("Password changed!");
			    	
			        return "redirect:/login";
		    	} else {
		    		System.out.println("PASSWORD CHECK ERROR");
		    		bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
		    		return "updatepassword";
		    	}
		    	
		    }
		} else {
			System.out.println("BINDING ERROR");
			return "updatepassword";
		}
		
	}
	
	
	public Boolean validatePasswordResetToken(String token) {
	    final PasswordResetToken passToken = prtrepository.findByToken(token);
	    
	    if( passToken != null) {
	    	if (passToken.getExpiryDate().after(new Date())) {
		    	return true;
		    } else {
		    	return false;
		    }
	    } else {
	    	return null;
	    }
	    
	}
	
}
