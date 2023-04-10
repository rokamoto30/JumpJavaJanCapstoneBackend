package com.cognixia.jump.tutorcapstone.model;

import java.io.Serializable;

import java.time.LocalDateTime;


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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class Session implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn( name = "course_id", referencedColumnName = "id")
	@Schema(description="linked course")
	private Course course;

	
	@ManyToOne
	@JoinColumn( name = "user_id", referencedColumnName = "id")
	@Schema(description="linked user to act as the student")
	private User user;
	@Schema(description="start time", example=" [2023, 4, 9, 17, 33]")
	private LocalDateTime start;
	@Schema(description="end time", example=" [2023, 4, 9, 17, 33]")
	private LocalDateTime end;
	
	@Min(value=0)
	@Max(value=5)
	@Schema(description="rating from 0-5", example="3.5")
	private Double rating;
	
	@Schema(description="cost")
	private Double cost;
	
	public Session () {}
	


	public Session(Integer id, Course course, User user, LocalDateTime start, LocalDateTime end, Double rating) {

		super();
		this.id = id;
		this.course = course;
		this.user = user;
		this.start = start;
		this.end = end;
		this.rating = rating;
	}
	
	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
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

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
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