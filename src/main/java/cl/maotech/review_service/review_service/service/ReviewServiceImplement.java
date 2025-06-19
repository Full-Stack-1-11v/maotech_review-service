package cl.maotech.review_service.review_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.maotech.review_service.review_service.client.CourseFeignClient;
import cl.maotech.review_service.review_service.model.Review;
import cl.maotech.review_service.review_service.repository.ReviewRepository;

/**
 * Implementation of the ReviewService interface.
 * This class provides methods to manage reviews, including saving, finding, updating, and deleting reviews.
 * It uses a Feign client to interact with the Course Service to validate course existence before saving reviews.
 */
@Service
@Transactional
public class ReviewServiceImplement implements ReviewService {

    /**
     * Feign client for interacting with the Course Service.
     * This client is used to check if a course exists before saving a review.
     */
    private CourseFeignClient courseFeignClient;
    /**
     * Repository for managing Review entities.
     * This repository provides CRUD operations for Review entities.
     */
    private ReviewRepository reviewRepository;

    /**
     * Constructor for ReviewServiceImplement.
     * Initializes the service with the provided ReviewRepository and CourseFeignClient.
     *
     * @param reviewRepository the repository for managing Review entities
     * @param courseFeignClient the Feign client for interacting with the Course Service
     */
    public ReviewServiceImplement(ReviewRepository reviewRepository, CourseFeignClient courseFeignClient) {
        this.courseFeignClient = courseFeignClient;
        this.reviewRepository = reviewRepository;
    }

    /**
     * Saves a review if the associated course exists.
     * Uses the CourseFeignClient to check if the course exists before saving the review.
     *
     * @param review the review to be saved
     * @return an Optional containing the saved review if successful, or empty if the course does not exist
     */
    @Override
    public Optional<Review> save(Review review) {
        // Validar que el curso existe antes de guardar la reseña
        if (courseFeignClient.findById(review.getCourseId()) != null) {
            return Optional.of(reviewRepository.save(review));
        } else {
            return Optional.empty(); // Retorna vacío si el curso no existe
        }
    }

    /**
     * Retrieves all reviews from the repository.
     *
     * @return a list of all reviews
     */
    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll(); 
    }
    
    /**
     * Finds a review by its ID.
     *
     * @param id the ID of the review to find
     * @return an Optional containing the found review, or empty if not found
     */
    @Override
    public Optional<Review> findById(Integer id) {
        return reviewRepository.findById(id);
    }

    /**
     * Updates an existing review.
     * If the review exists, it updates the review text and value, and saves it back to the repository.
     *
     * @param review the review with updated information
     * @return an Optional containing the updated review if successful, or empty if the review does not exist
     */
    @Override
    public Optional<Review> update(Review review) {
        return reviewRepository.findById(review.getId())
                .map(existingReview -> {
                    existingReview.setReview(review.getReview());
                    existingReview.setValue(review.getValue());
                    return Optional.of(reviewRepository.save(existingReview));
                })
                .orElse(Optional.empty());
    }
    
    /**
     * Deletes a review by its ID.
     *
     * @param id the ID of the review to delete
     */
    @Override
    public void delete(Integer id) {
        reviewRepository.deleteById(id);
    }

}
