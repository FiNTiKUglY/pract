package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Review;
import com.library.api.libraryapi.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.library.api.libraryapi.repositories.ReviewRepository;
import com.library.api.libraryapi.repositories.UserRepository;

@Service
public class ReviewService {
    
    ReviewRepository reviewRepository;
    UserRepository userRepository;

    @Autowired 
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
        User user = new User();
        Optional<User> userOpt = userRepository.findById(UUID.fromString(userName));
        if (userOpt.isPresent()) {
            user = userOpt.get();
        }
        review.setUser(user);
        return reviewRepository.save(review);
    }

    public Review getReviewById(UUID id) {
        Review review = new Review();
        Optional<Review> reviewOpt = reviewRepository.findById(id);
        if (reviewOpt.isPresent()) {
            review = reviewOpt.get();
        }
        return review;
    }

    public void deleteReviewById(UUID id, String userName) throws AccessDeniedException {
        var review = getReviewById(id);
        if (!userName.equals(review.getUser().getId().toString())) {
            throw new AccessDeniedException("");
        }
        reviewRepository.deleteById(id);
    }

    public Review updateReview(UUID id, Review review, String userName) throws AccessDeniedException {
        review.setId(id);
        if (!userName.equals(review.getUser().getId().toString())) {
            throw new AccessDeniedException("");
        }
        return reviewRepository.save(review);
    }
}
