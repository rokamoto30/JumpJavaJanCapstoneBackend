package com.cognixia.jump.tutorcapstone.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn( name = "subject_id", referencedColumnName = "id")
	private Subject subject;
	
	@ManyToOne
	@JoinColumn( name = "user_id", referencedColumnName = "id")
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "course_id", cascade = CascadeType.ALL)
	private List<Session> sessions;
	
	private String availability;
	
	private String hourly;
	
	public Course() {
		
	}

	public Course(Integer id, Subject subject, User user, List<Session> sessions, String availability, String hourly) {
		super();
		this.id = id;
		this.subject = subject;
		this.user = user;
		this.sessions = sessions;
		this.availability = availability;
		this.hourly = hourly;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getHourly() {
		return hourly;
	}

	public void setHourly(String hourly) {
		this.hourly = hourly;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", subject=" + subject + ", user=" + user + ", sessions=" + sessions
				+ ", availability=" + availability + ", hourly=" + hourly + "]";
	}
	
	
	
	
}

