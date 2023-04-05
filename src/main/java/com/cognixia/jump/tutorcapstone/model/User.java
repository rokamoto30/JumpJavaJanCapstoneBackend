package com.cognixia.jump.tutorcapstone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
public class User {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, nullable = false)
	private String username;

    @Column(nullable = false)
	private String password;

    @Pattern(regexp = "^.+@.+$")
    @Column(unique=true, nullable = false)
    private String email;

    private String description;

    private String pfp_url;

    public User() {
    }

    public User(Integer id, String username, String password, String email, String description, String pfp_url) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.description = description;
        this.pfp_url = pfp_url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
                + ", description=" + description + ", pfp_url=" + pfp_url + "]";
    }

    

    


}
