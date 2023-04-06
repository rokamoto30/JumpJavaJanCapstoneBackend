package com.cognixia.jump.tutorcapstone.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn( name = "course_id", referencedColumnName = "id")
	private Course course;
	
	@ManyToOne
	@JoinColumn( name = "user_id", referencedColumnName = "id")
	private User user;
	
	private LocalDate start;
	private LocalDate end;
	
	private float rating;
	
	public Session () {
		
	}
	
	public Session(Integer id, Course course, User user, LocalDate start, LocalDate end, float rating) {
		super();
		this.id = id;
		this.course = course;
		this.user = user;
		this.start = start;
		this.end = end;
		this.rating = rating;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", course=" + course + ", user=" + user + ", start=" + start + ", end=" + end
				+ ", rating=" + rating + "]";
	}
	
	
	
	
}