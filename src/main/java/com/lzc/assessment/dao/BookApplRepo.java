package com.lzc.assessment.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lzc.assessment.entity.BookApplication;

public interface BookApplRepo extends JpaRepository<BookApplication, Integer>{
	
	@Query("SELECT ba FROM BookApplication ba WHERE ba.start_time = :start_time AND ba.end_time = :end_time")
	BookApplication findBookApplByDate(Date start_time, Date end_time);

}
