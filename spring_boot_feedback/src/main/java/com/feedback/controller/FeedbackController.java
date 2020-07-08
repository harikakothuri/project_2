package com.feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.exception.BusinessException;
import com.feedback.model.Feedback;
import com.feedback.service.FeedbackService;

@RestController
public class FeedbackController {

	@Autowired
	private FeedbackService service;

	private MultiValueMap<String, String> map;
	private MultiValueMap<String, String> map1;

	@PostMapping("/feedback")
	public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {

		try {
			return new ResponseEntity<Feedback>(service.createFeedback(feedback), HttpStatus.OK);
		} catch (BusinessException e) {
			map = new LinkedMultiValueMap<>();
			map.add("message", e.getMessage());
			return new ResponseEntity<Feedback>(null, map, HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/feedback")
	public ResponseEntity<Feedback> updateFeedback(@RequestBody Feedback feedback) {

		try {
			return new ResponseEntity<Feedback>(service.updateFeedback(feedback), HttpStatus.OK);
		} catch (BusinessException e) {
			map = new LinkedMultiValueMap<>();
			map.add("message", e.getMessage());
			return new ResponseEntity<Feedback>(null, map, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/feedback/{id}")
	public ResponseEntity<Feedback> getFeedbackById(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<Feedback>(service.getFeedbackById(id), HttpStatus.OK);
		} catch (BusinessException e) {
			map = new LinkedMultiValueMap<>();
			map.add("message", e.getMessage());
			return new ResponseEntity<Feedback>(null, map, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/feedbacks")
	public List<Feedback> getAllfeedbacks() {

		return service.getAllfeedbacks();
	}

	@GetMapping("/feedbacks/rating/{rating}")
	public ResponseEntity<List<Feedback>> getAllFeedbacksByRating(@PathVariable("rating") float rating) {
		try {
			return new ResponseEntity<List<Feedback>>(service.getAllFeedbacksByRating(rating), HttpStatus.OK);
		} catch (BusinessException e) {
			map1 = new LinkedMultiValueMap<>();
			map1.add("message", e.getMessage());
			return new ResponseEntity<List<Feedback>>(null, map1, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/feedback/{id}")
	public ResponseEntity<Feedback> deleteFeedbackById(@PathVariable("id") int id) {
		try {
			service.deleteFeedbackById(id);
			return new ResponseEntity<Feedback>(HttpStatus.OK);
		} catch (BusinessException e) {
			map = new LinkedMultiValueMap<>();
			map.add("message", e.getMessage());
			return new ResponseEntity<Feedback>(null, map, HttpStatus.NOT_FOUND);
		}

	}
}
