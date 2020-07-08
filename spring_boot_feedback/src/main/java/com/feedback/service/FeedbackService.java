package com.feedback.service;

import java.util.List;

import com.feedback.exception.BusinessException;
import com.feedback.model.Feedback;

public interface FeedbackService {
	public Feedback createFeedback(Feedback feedback) throws BusinessException;

	public Feedback updateFeedback(Feedback feedback) throws BusinessException;

	public Feedback getFeedbackById(int id) throws BusinessException;

	public List<Feedback> getAllfeedbacks();

	public List<Feedback> getAllFeedbacksByRating(float rating) throws BusinessException;

	public void deleteFeedbackById(int id) throws BusinessException;
}
