package com.lzc.assessment.controller;

import java.util.Date;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lzc.assessment.dao.BookApplRepo;
import com.lzc.assessment.dao.MtRoomRepo;
import com.lzc.assessment.dao.UserRepo;
import com.lzc.assessment.entity.BookApplication;

@Controller
public class BookingMtrmController {
	@Autowired
	MtRoomRepo mtRmRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	BookApplRepo bookApplRepo;
	
	@GetMapping(value = "/booking/{id}")
	public String showPage(@PathVariable("id") int MtrmID, Model model, BookApplication bookapplication) {
		//System.out.println(MtrmID);
		UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user",userRepo.findByUserName(userDetail.getUsername()));
		model.addAttribute("Bkappl",bookapplication);
		model.addAttribute("mtRm", mtRmRepo.findById(MtrmID));

		
		return "bookingmtrm.html";
	}
	
	@PostMapping("booking/meetingroom/{id}")
	public String bookMtrm(@ModelAttribute("Bkappl") BookApplication bookApplication,
			@PathVariable("id") int MtrmID, RedirectAttributes redirectAttributes) {
		System.out.println("bindingresult"+bookApplication);
		if(bookApplication.getStart_time().equals(bookApplication.getEnd_time())) {
			redirectAttributes.addFlashAttribute("error", "Start time CANNOT same with end time!");
			return "redirect:/booking/{id}";
		}else if (bookApplication.getStart_time().after(bookApplication.getEnd_time())) {
			redirectAttributes.addFlashAttribute("error", "End time CANNOT be earlier than Start time!");
			return "redirect:/booking/{id}";
		}else {
			Optional<BookApplication> appls = bookApplRepo.findAll()
					.stream()
					.filter( ba -> ba.getBkappl_mtrm().getMtrm_id() == MtrmID &&
					((ba.getStart_time().getTime() <= bookApplication.getEnd_time().getTime() &&
						bookApplication.getEnd_time().getTime() <= ba.getEnd_time().getTime()) ||
						(bookApplication.getEnd_time().getTime() > ba.getEnd_time().getTime() && 
						!(bookApplication.getStart_time().getTime() > ba.getEnd_time().getTime()))))
//					!(ba.getEnd_time().getTime() <= bookApplication.getStart_time().getTime()) ||
//					!(ba.getStart_time().getTime() >= bookApplication.getEnd_time().getTime())||
//					(ba.getStart_time().getTime() <= bookApplication.getStart_time().getTime() && 
//					ba.getEnd_time().getTime() >= bookApplication.getEnd_time().getTime()))
					.findAny();
			if(!appls.isEmpty()) {
				redirectAttributes.addFlashAttribute("error", "The time slot has already been placed! The placed meeting start at "
			+appls.get().getStart_time() + ", End at: " + appls.get().getEnd_time());
				return "redirect:/booking/{id}";
			}
			
		}
		
		Date date =bookApplication.getStart_time(); //Mon Apr 26 08:00:00 CST 2021
		System.out.println(date);
		UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		bookApplication.setUser(userRepo.findByUserName(userDetail.getUsername()));
		bookApplication.setBkappl_mtrm(mtRmRepo.findById(MtrmID));
		bookApplRepo.save(bookApplication);
		return "redirect:/";
	}

}
