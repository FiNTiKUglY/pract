package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Review;
import com.library.api.libraryapi.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

import com.library.api.libraryapi.repositories.ReviewRepository;
import com.library.api.libraryapi.repositories.UserRepository;

@Service
public class ReviewService {
    
    @Autowired ReviewRepository reviewRepository;
    @Autowired UserRepository userRepository;

    public ReviewService() {
        //Constructor for service
    }

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getBookReviews(UUID bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    public List<Review> getUserReviews(UUID userId) {
        return reviewRepository.findByUserId(userId);
    }

    public Review addReview(Review review, String userName) {
        User user = userRepository.findById(UUID.fromString(userName)).get();
        review.setUser(user);
        return reviewRepository.save(review);
    }

    public Review getReviewById(UUID id) {
        return reviewRepository.findById(id).get();
    }

    public void deleteReviewById(UUID id, String userName) throws AccessDeniedException {
        var review = getReviewById(id);
        if (UUID.fromString(userName) != review.getUser().getId()) {
            throw new AccessDeniedException("");
        }
        reviewRepository.deleteById(id);
    }

    public Review updateReview(UUID id, Review review, String userName) throws AccessDeniedException {
        review.setId(id);
        if (UUID.fromString(userName) != review.getUser().getId()) {
            throw new AccessDeniedException("");
        }
        return reviewRepository.save(review);
    }
}
