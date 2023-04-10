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
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, nullable = false)
	@Schema(description="username to be used with login")
	private String username;

    @Column(nullable = false)
    @Schema(description="password to be used with login")
	private String password;

    @Pattern(regexp = "^.+@.+$")
    @Column(unique=true, nullable = false)
    @Schema(description="email contact to connect tutors and students")
    private String email;
    
    @Schema(description="personal profile description")
    private String description;
    
    @Schema(description="url to profile picture")
    private String pfp_url;
    
    @Schema(description="user avarage rating")
    private String rating;
    

    @JsonIgnore
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    @Schema(description="list of courses a user is teaching")
    private List<Course> courses;
    
    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @Schema(description="list of sessions a user has attended")
    private List<Session> sessions;

    public User() {
    }

	public User(Integer id, String username, String password, @Pattern(regexp = "^.+@.+$") String email,
			String description, String pfp_url, String rating, List<Course> courses, List<Session> sessions) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.description = description;
		this.pfp_url = pfp_url;
		this.rating = rating;
		this.courses = courses;
		this.sessions = sessions;
	}



	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		System.out.println("CALLED def");
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public String getPfp_url() {
        return pfp_url;
    }

    public void setPfp_url(String pfp_url) {
        this.pfp_url = pfp_url;
    }

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", description=" + description + ", pfp_url=" + pfp_url + ", courses=" + courses + ", sessions="
				+ sessions + "]";
	}



}
