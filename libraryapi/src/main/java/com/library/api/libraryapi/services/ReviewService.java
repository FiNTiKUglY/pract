package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

import com.library.api.libraryapi.repositories.ReviewRepository;

@Service
public class ReviewService {
    
    @Autowired ReviewRepository reviewRepository;

    public ReviewService() {
        //Constructor for service
    }

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getBookReviews(UUID bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review getReviewById(UUID id) {
        return reviewRepository.findById(id).get();
    }

    public void deleteReviewById(UUID id) {
        reviewRepository.deleteById(id);
    }

    public Review updateReview(UUID id, Review review) {
        review.setId(id);
        return reviewRepository.save(review);
    }
}
