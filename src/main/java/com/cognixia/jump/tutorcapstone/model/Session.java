package com.cognixia.jump.tutorcapstone.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Session implements Serializable {
	
	private static final long serialVersionUID = 1L;
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
	
	@Min(value=0)
	@Max(value=5)
	private Double rating;
	
	public Session () {
		
	}
	
	public Session(Integer id, Course course, User user, LocalDate start, LocalDate end, Double rating) {
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

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", course=" + course + ", user=" + user + ", start=" + start + ", end=" + end
				+ ", rating=" + rating + "]";
	}
	
	
	
	
}