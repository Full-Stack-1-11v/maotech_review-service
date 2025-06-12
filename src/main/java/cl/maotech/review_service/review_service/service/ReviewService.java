package cl.maotech.review_service.review_service.service;

import java.util.List;
import java.util.Optional;

import cl.maotech.review_service.review_service.model.Review;

public interface ReviewService {

    Optional<Review> save(Review review);
    List<Review> findAll();
    Optional<Review> findById(Integer id);
    Optional<Review> update(Review review);
    void delete(Integer id);

}
