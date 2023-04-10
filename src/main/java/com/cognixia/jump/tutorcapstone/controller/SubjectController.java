package com.cognixia.jump.tutorcapstone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.tutorcapstone.model.Subject;
import com.cognixia.jump.tutorcapstone.repository.SubjectRepo;

import mysql.PopulateScript;

@RequestMapping("/api/subject")
@RestController
@CrossOrigin(maxAge = 3600)
public class SubjectController {
	@Autowired
	SubjectRepo repo;
	
	@GetMapping()
	public List<Subject> getSubjects() {
		return repo.findAll();
	}
	
	@GetMapping("/populate")
	public void populate() {
		PopulateScript.populate(repo);
	}
	

}
