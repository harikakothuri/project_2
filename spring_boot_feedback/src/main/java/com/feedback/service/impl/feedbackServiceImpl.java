package com.feedback.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.dao.FeedbackDAO;
import com.feedback.exception.BusinessException;
import com.feedback.model.Feedback;
import com.feedback.service.FeedbackService;

@Service
public class feedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackDAO dao;

	@Override
	public Feedback createFeedback(Feedback feedback) throws BusinessException {
		List<Feedback> fb = new ArrayList<>();
		fb = dao.findAll();
		if (!fb.isEmpty()) {
			for (Feedback f : fb) {
				if (f.getEmail().equals(feedback.getEmail())) {
					throw new BusinessException("Email already exists");
				}
			}
		}
		return dao.save(feedback);

	}

	@Override
	public Feedback updateFeedback(Feedback feedback) throws BusinessException {
		if (dao.findById(feedback.getId()).isPresent()) {
			return dao.save(feedback);
		} else {
			throw new BusinessException("Id " + feedback.getId() + " is not valid");
		}

	}

	@Override
	public Feedback getFeedbackById(int id) throws BusinessException {
		if (id <= 0) {
			throw new BusinessException("Id " + id + " is not valid");
		}
		Feedback feedback = null;
		try {
			feedback = dao.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new BusinessException("No feedback found for id " + id);
		}
		return feedback;
	}

	@Override
	public List<Feedback> getAllfeedbacks() {

		return dao.findAll();
	}

	@Override
	public List<Feedback> getAllFeedbacksByRating(float rating) throws BusinessException {
		List<Feedback> feedback = new ArrayList<>();

		feedback = dao.findByRating(rating);
		if (feedback.isEmpty()) {
			throw new BusinessException("No feedback found for rating " + rating);
		}
		return feedback;
	}

	@Override
	public void deleteFeedbackById(int id) throws BusinessException {
		if (dao.findById(id).isPresent()) {
			dao.deleteById(id);
		} else {
			throw new BusinessException("Id " + id + " is not valid");
		}

	}

}
