package com.library.api.libraryapi.controllers;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
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
import org.springframework.security.access.prepost.PreAuthorize;


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

    @GetMapping("/api/users/{userId}/reviews")
    public List<Review> getUserReviews(@PathVariable UUID userId) {
        return reviewService.getUserReviews(userId);
    }

    @GetMapping("/api/reviews/{id}")
    public Review getReviewById(@PathVariable UUID id) {
        return reviewService.getReviewById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/api/reviews/add")
    public Review addReview(@RequestBody Review review, Principal principal) {
        return reviewService.addReview(review, principal.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/api/reviews/update/{id}")
    public Review updateReview(@PathVariable UUID id, @RequestBody Review review, Principal principal) throws AccessDeniedException {
        return reviewService.updateReview(id, review, principal.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/api/reviews/delete/{id}")
    public void deleteReview(@PathVariable UUID id, Principal principal) throws AccessDeniedException {
        reviewService.deleteReviewById(id, principal.getName());
    }
}
