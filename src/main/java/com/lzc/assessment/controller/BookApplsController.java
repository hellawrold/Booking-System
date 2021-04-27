package com.lzc.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lzc.assessment.dao.BookApplRepo;
import com.lzc.assessment.dao.UserRepo;
import com.lzc.assessment.entity.User;
import com.lzc.assessment.service.OtherService;

@Controller
public class BookApplsController {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	BookApplRepo bookApplRepo;
	
	@Autowired
	OtherService otherService;
	
	@GetMapping(value = "bookappls")
	public String showAppls(Model model, RedirectAttributes redirectAttr) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		System.out.println(userDetail);
		User user = userRepo.findByUserName(userDetail.getUsername());
		if(user == null) {
			redirectAttr.addFlashAttribute("error","User is null");
		}
		model.addAttribute("lguser", user);
		model.addAttribute("otherservice",otherService);
		return "showappls.html";
	}
	
	@GetMapping(value = "delete/{id}")
	public String deleteBkappl(@PathVariable("id") int bkapplId) {
		System.out.println(bkapplId);
		bookApplRepo.deleteById(bkapplId);
		return "redirect:/bookappls";
	}

}
