package com.goutham.edu.ratingsdataservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goutham.edu.ratingsdataservice.model.Rating;
import com.goutham.edu.ratingsdataservice.model.UserRating;



@RestController
@RequestMapping("/ratings")
public class RatingController {
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 8);
	}
	
	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		ratings.add(new Rating("1", 5) );
		ratings.add(new Rating("2", 6) );
		ratings.add(new Rating("3", 7) );
		
		UserRating ur = new UserRating();
		ur.setUserRating(ratings);
		return ur;

	}

}
