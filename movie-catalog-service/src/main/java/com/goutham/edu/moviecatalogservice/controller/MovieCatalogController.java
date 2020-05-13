package com.goutham.edu.moviecatalogservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.goutham.edu.moviecatalogservice.model.CatalogItem;
import com.goutham.edu.moviecatalogservice.model.Movie;
import com.goutham.edu.moviecatalogservice.model.Rating;
import com.goutham.edu.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
 
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		ArrayList<CatalogItem> al = new ArrayList<CatalogItem>();
		
		UserRating ur = restTemplate.getForObject("http://ratings-data-service/ratings/users/" + userId, UserRating.class);
	    // get movie info from movie info service
		
		for(Rating r : ur.getUserRating()) {
			Movie m = restTemplate.getForObject("http://movie-info-service/movies/" + r.getMovieId(), Movie.class);
			al.add(new CatalogItem(m.getName(), "desc", r.getRating()));
			
		}
		
		
		return al;
		
	}
}
