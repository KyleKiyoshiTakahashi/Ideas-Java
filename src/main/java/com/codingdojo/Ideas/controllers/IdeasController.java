package com.codingdojo.Ideas.controllers;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.codingdojo.Ideas.models.Idea;
import com.codingdojo.Ideas.models.User;
import com.codingdojo.Ideas.services.IdeaService;
import com.codingdojo.Ideas.validators.IdeaValidator;
import com.codingdojo.Ideas.validators.UserValidator;

@Controller
public class IdeasController {
	private final IdeaService ideaService;
	private final UserValidator userValidator;
	private final IdeaValidator ideaValidator;
	
	public IdeasController(IdeaService ideaService, UserValidator userValidator, IdeaValidator ideaValidator) {
		this.ideaService = ideaService;
		this.userValidator = userValidator;
		this.ideaValidator = ideaValidator;
	}
	
	@GetMapping("/")
	public String index(@ModelAttribute("userObj") User user, Model model) {
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("userObj") User user, BindingResult result, Model model, HttpSession session) {
		//validation: checks if the password and confirmPassword match		
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "index.jsp";
		}
	
		boolean isDuplicate = ideaService.duplicateUser(user.getEmail());
		if(isDuplicate) {
			model.addAttribute("error", "Email already in use! Please try again with a different email address!");
			return "index.jsp";
		}
		//If no errors, register user to DB and set userId in session	
		User u = ideaService.registerUser(user);
		session.setAttribute("userId", u.getId());
		System.out.println("registering user "+u.getId() );
		return "redirect:/ideas";
	}
	//login
	@PostMapping("/login")
	public String signIn(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {

		boolean isAuthenticated = ideaService.authenticateUser(email, password);
		
		if(isAuthenticated == false) {
			System.out.println("error1");
			model.addAttribute("error", "Invalid Credentials!");
			System.out.println("error2");
			return "index.jsp";
		}
		else {
			User u = ideaService.findByEmail(email);
			session.setAttribute("userId", u.getId());
			return "redirect:/ideas";
		}
	}
	
	@GetMapping("/ideas")
	public String dashboard(@Valid @ModelAttribute("ideaObj") Idea idea, BindingResult result, HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}

		User user = ideaService.findUserById((Long) session.getAttribute("userId"));
		model.addAttribute("user", user);
		List<Idea> ideas = ideaService.allIdeas(); 
		model.addAttribute("ideas", ideas);

		return "ideas.jsp";	
	}
	
	@GetMapping("/ideas/new")
	public String addIdea(@Valid @ModelAttribute("ideaObj") Idea idea, BindingResult result, HttpSession session, Model model) {
	if(session.getAttribute("userId") == null) {
		return "redirect:/";
	}
	User user = ideaService.findUserById((Long) session.getAttribute("userId"));
	model.addAttribute("user", user);
	return "new.jsp";
	}
	
	@PostMapping("/addIdea")
	public String createIdea(@Valid @ModelAttribute("ideaObj") Idea idea, BindingResult result, HttpSession session, Model model) {
		if(result.hasErrors()) {
			return "new.jsp";
		}
		else {
			ideaService.addIdea(idea);
			System.out.println("added an idea "+ idea);
			return "redirect:/ideas";	
		}	
	}
	@GetMapping("/ideas/{id}")
	public String viewIdea(@PathVariable("id") Long id,  Model model, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		User user = ideaService.findUserById((Long) session.getAttribute("userId"));
		Idea idea = ideaService.findIdeaById(id);
		model.addAttribute("idea", idea);
		model.addAttribute("user", user);
		return "show.jsp";
	}
	@GetMapping("/ideas/{id}/edit")
	public String editPage(@PathVariable("id") Long id, @ModelAttribute("idea") Idea idea, Model model, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		User user = ideaService.findUserById((Long)session.getAttribute("userId"));
		if(ideaService.findIdeaById(id).getUser().getId() == user.getId()) {
			model.addAttribute("idea", ideaService.findIdeaById(id));
			return "edit.jsp";
		}
		else {
			return "redirect:/ideas";
		}
	}
	
	@PutMapping("/ideas/{id}/edit")
	public String editIdea( @PathVariable("id") Long id, @Valid @ModelAttribute("idea") Idea idea, BindingResult result, Model model, HttpSession session) {
		User user = ideaService.findUserById((Long)session.getAttribute("userId"));
		
//		if(ideaService.findIdeaById(id).getUser().getId() == user.getId()) {
			if(result.hasErrors()) {
				model.addAttribute("errors", result);
				System.out.println("there are errors&&&&&&&&(&(&();&()(&*(&"+ result);
				return "edit.jsp";
			}
			else {
					
				Idea ideaEdit = ideaService.findIdeaById(id);
					
				model.addAttribute("idea", ideaEdit);
					
				model.addAttribute("user", user);
							
				idea.setUser(user);
			
				
			
				ideaService.updateIdea(idea);
				return "redirect:/ideas";
			}
//		}
//		else {
//			return "redirect:/";
//		}
	}
	
	@RequestMapping("/ideas/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		
		ideaService.deleteIdea(id);
		return "redirect:/ideas";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
