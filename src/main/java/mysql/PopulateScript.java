package mysql;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.tutorcapstone.model.Subject;
import com.cognixia.jump.tutorcapstone.repository.SubjectRepo;

public class PopulateScript {
	static public void populate(SubjectRepo repo) {
		Subject sub1 = new Subject(1, "Algebra", "math", "a branch of mathematics in which arithmetic operations and other formal manipulations are applied to abstract symbols rather than specific numbers", new ArrayList());
		Subject newSub = repo.save(sub1);
		
		Subject sub2 = new Subject(2, "Calculus", "math", "the mathematical study of change", null);
		newSub = repo.save(sub2);
		
		Subject sub3 = new Subject(3, "Triginometry", "math", "The study of angles and of the angular relationships of planar and three-dimensional figures", null);
		newSub = repo.save(sub3);
		
		Subject sub4 = new Subject(4, "Java", "CS", " a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible", null);
		newSub = repo.save(sub4);
		
		Subject sub5 = new Subject(5, "JavaScript", "CS", "an object-oriented computer programming language commonly used to create interactive effects within web browsers", null);
		newSub = repo.save(sub5);
		
		Subject sub6 = new Subject(6, "AWS", "CS", "on-demand cloud computing platforms and APIs", null);
		newSub = repo.save(sub6);
		
		Subject sub7 = new Subject(7, "Physics", "science", "the branch of science that deals with the structure of matter and how the fundamental constituents of the universe interact", null);
		newSub = repo.save(sub7);
		
		Subject sub8 = new Subject(8, "Chemistry", "science", "Chemistry is the scientific study of the properties and behavior of matter", null);
		newSub = repo.save(sub8);
		
		Subject sub9 = new Subject(9, "Guitar", "art", "a stringed musical instrument, with a fretted fingerboard, typically incurved sides, and six or twelve strings, played by plucking or strumming with the fingers or a plectrum", null);
		newSub = repo.save(sub9);
		
		Subject sub10 = new Subject(10, "Cooking", "lifestyle", "the practice or skill of preparing food by combining, mixing, and heating ingredients", null);
		newSub = repo.save(sub10);
	}
	
}
