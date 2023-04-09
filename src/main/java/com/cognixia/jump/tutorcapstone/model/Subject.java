package com.cognixia.jump.tutorcapstone.model;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
public class Subject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private String category;
	
	@Column(nullable = true)
	private String description;
	
	@JsonIgnore
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<Course> courses;
	
	
	public Subject() {
	}


	public Subject(Integer id, String name, String category, String description, List<Course> courses) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.description = description;
		this.courses = courses;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<Course> getCourses() {
		return courses;
	}


	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}


	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", category=" + category + ", description=" + description
				+ ", courses=" + courses + "]";
	}

	
	



	
}
