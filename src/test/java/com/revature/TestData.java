package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.models.Brewery;
import com.revature.models.Review;
import com.revature.models.User;

public class TestData {
	
	public User u1 = new User();
	public User u2 = new User();
	
	public Brewery b1 = new Brewery();
	public Brewery b2 = new Brewery();
	
	public Review r1 = new Review();
	public Review r2 = new Review();
	
	public TestData() {
		
		r1 = new Review();
		b1.setName("b1");
		b2.setName("b2");
		
		u1.setUsername("u1");
		u2.setUsername("u2");
		
		r1.setId(1);
		r1.setBrewery(b1); r1.setSubmitter(u1); r1.setReviewText("r1");
		r2.setId(2);
		r2.setBrewery(b2); r2.setSubmitter(u2); r2.setReviewText("r2");
	}

}