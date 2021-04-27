package com.lzc.assessment.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;


@Entity
@Table(name = "bookapplication")
public class BookApplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bkapplid;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@NotNull
	private Date start_time;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date end_time;
	
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "MTRM_MTRMID")
	private MeetingRoom mtrm;
	

	public BookApplication() {
	}

	public BookApplication(User bkapppl_user, Date start_time, Date end_time, MeetingRoom bkapppl_mtrm) {
		this.user = bkapppl_user;
		this.start_time = start_time;
		this.end_time = end_time;
		this.mtrm = bkapppl_mtrm;
	}

	public int getBkapplid() {
		return bkapplid;
	}

	public void setBkapplid(int bkapplid) {
		this.bkapplid = bkapplid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public MeetingRoom getBkappl_mtrm() {
		return mtrm;
	}

	public void setBkappl_mtrm(MeetingRoom bkapppl_mtrm) {
		this.mtrm = bkapppl_mtrm;
	}
	

	@Override
	public String toString() {
		return "BookApplication [bkappl_id=" + bkapplid + ", start_time="
				+ start_time + ", end_time=" + end_time + "]";
	}

}
