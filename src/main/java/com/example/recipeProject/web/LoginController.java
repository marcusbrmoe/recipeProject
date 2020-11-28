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

	@RequestMapping(value="/signup")
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
		    	
		    	} else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		
			} else {
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

		if (!bindingResult.hasErrors()) { // Validation errors
			
			Login login = lrepository.findByEmail(forgotForm.getEmail());
			
			if(login != null) {    
			    String token = UUID.randomUUID().toString(); // Random token generation
					
			    Date expiryDate = new Date();
			    expiryDate.setTime(expiryDate.getTime() + 86400000); // Pluss 24 Hours!
				    
			    prtrepository.save(new PasswordResetToken(token, login, expiryDate));
				    
			    Email email = new Email();
			    email.sendEmail("noreply.recipe@gmail.com", "Qwerty.789", login.getEmail(), "Reset key", "Secret key: " + "https://marcusrecipeproject.herokuapp.com/updatepassword/" + token);
				    
			   	return "redirect:/login";
			} else {
			    bindingResult.rejectValue("email", "err.email", "E-mail not found"); // E-mail not found err
			    return "forgotpassword";
			}
	    
		} else {
    		return "forgotpassword";
    	}
	}
	
	@RequestMapping(value = "/updatepassword/{token}", method=RequestMethod.GET)
	public String showUpdatePasswordPage(@PathVariable("token") String token, Model model) {
		
		Boolean result = validatePasswordResetToken(token);
		
		if(result == true) {
			UpdateForm updateForm = new UpdateForm();
			updateForm.setToken(token);
			model.addAttribute("updateform", updateForm);
			
			return "updatepassword";
		} else if (result == false) {
			
			prtrepository.delete(prtrepository.findByToken(token));
			return "expiredtoken";
		} else {
			return "login";
		}
		
	}
	
	@RequestMapping(value="/changepassword", method = RequestMethod.POST)
	public String changePassword(@Valid @ModelAttribute("updateform") UpdateForm updateForm, BindingResult bindingResult) {
				
		PasswordResetToken token = prtrepository.findByToken(updateForm.getToken());
		Login login = lrepository.findById(token.getLogin().getId());
		
		if(!bindingResult.hasErrors()) { // validation errors
			Boolean result = validatePasswordResetToken(token.getToken());
		    if(result == null) {
		        
		        return "redirect:/login";
		    } else if(result == false) {
		        
		        return "redirect:/login";
		    } else {
		    	
		    	if(updateForm.getPassword().equals(updateForm.getPasswordCheck())) {
		    		String pwd = updateForm.getPassword();
			    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			    	String hashPwd = bc.encode(pwd);
			    	
			    	login.setPasswordHash(hashPwd);
			    	
			    	lrepository.save(login);
			    	
			    	prtrepository.delete(token);
			    	
			        return "redirect:/login";
		    	} else {
		    		bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match"); //Password match err
		    		return "updatepassword";
		    	}
		    	
		    }
		} else {
			return "updatepassword";
		}
		
	}
	
	
	public Boolean validatePasswordResetToken(String token) { // Checking if token is valid 
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
