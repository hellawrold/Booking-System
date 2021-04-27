package com.lzc.assessment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lzc.assessment.entity.MeetingRoom;

public interface MtRoomRepo extends JpaRepository<MeetingRoom, Integer>{

	MeetingRoom findById(int id);
}
