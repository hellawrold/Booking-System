package com.lzc.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lzc.assessment.dao.MtRoomRepo;
import com.lzc.assessment.dao.UserRepo;
import com.lzc.assessment.entity.MeetingRoom;

@Controller
public class MeetingRoomController {
	
	@Autowired
	MtRoomRepo mtrmRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@GetMapping(value = "/")
	public String showMtrm(Model model, MeetingRoom meetingRoom) {
		UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user",userRepo.findByUserName(userDetail.getUsername()));
		model.addAttribute("mtrooms", mtrmRepo.findAll());
		model.addAttribute("newmtr",meetingRoom);
		System.out.println(mtrmRepo.findAll());
		return "showmtrm.html";
	}
	@PostMapping(value = "/addMtrm")
	public String addMtrm(@ModelAttribute("newmtr")MeetingRoom mtr) {
		System.out.println(mtr);
		mtrmRepo.save(mtr);
		return "redirect:/";
	}
	
	@GetMapping(value = "/deleteMtrm/{id}")
	public String deleteMtrm(@PathVariable("id") int theId) {
		System.out.println(theId);
		
		mtrmRepo.deleteById(theId);
		return "redirect:/";
	}

}
