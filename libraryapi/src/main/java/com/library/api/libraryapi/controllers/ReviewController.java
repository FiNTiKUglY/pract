package com.library.api.libraryapi.controllers;

import java.util.List;
import java.util.UUID;
import com.library.api.libraryapi.services.ReviewService;
import com.library.api.libraryapi.entities.Review;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;



@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping("/api/reviews")
    public List<Review> getReviews() {
        return reviewService.getReviews();
    }

    @GetMapping("/api/books/{bookId}/reviews")
    public List<Review> getBookReviews(@PathVariable UUID bookId) {
        return reviewService.getBookReviews(bookId);
    }

    @GetMapping("/api/reviews/{id}")
    public Review getReviewById(@PathVariable UUID id) {
        return reviewService.getReviewById(id);
    }

    @PostMapping("/api/reviews/add")
    public Review addReview(@RequestBody Review review) {
        return reviewService.addReview(review);
    }

    @PostMapping("/api/reviews/update/{id}")
    public Review updateReview(@PathVariable UUID id, @RequestBody Review review) {
        return reviewService.updateReview(id, review);
    }

    @DeleteMapping("/api/reviews/delete/{id}")
    public void deleteReview(@PathVariable UUID id) {
        reviewService.deleteReviewById(id);
    }
}
