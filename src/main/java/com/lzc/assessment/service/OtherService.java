package com.lzc.assessment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzc.assessment.dao.MtRoomRepo;

@Service
public class OtherService {
	
	@Autowired
	MtRoomRepo mtRoomRepo;
	
	public String getMeetingRoomNameByMrId(int id) {
		return mtRoomRepo.findById(id).getMtrm_name();
	}
	
	

}
