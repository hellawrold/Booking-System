package com.lzc.assessment.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "meetingroom")
public class MeetingRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mtrmid;
	private String mtrmName;
	
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, 
			mappedBy = "mtrm",orphanRemoval = true)
	private List<BookApplication> bookapplications;

	public MeetingRoom() {
		super();
	}

	public MeetingRoom(String mtrm_name, List<BookApplication> bookapplications) {
		super();
		this.mtrmName = mtrm_name;
		this.bookapplications = bookapplications;
	}
	

	public int getMtrm_id() {
		return mtrmid;
	}

	public void setMtrm_id(int mtrmid) {
		this.mtrmid = mtrmid;
	}

	public String getMtrm_name() {
		return mtrmName;
	}

	public void setMtrm_name(String mtrm_name) {
		this.mtrmName = mtrm_name;
	}

	public List<BookApplication> getBookapplications() {
		return bookapplications;
	}

	public void setBookapplications(List<BookApplication> bookapplications) {
		this.bookapplications = bookapplications;
	}

	@Override
	public String toString() {
		return "MeetingRoom [mtrm_id=" + mtrmid + ", mtrm_name=" + mtrmName
				+ "]";
	}
	
	

}
