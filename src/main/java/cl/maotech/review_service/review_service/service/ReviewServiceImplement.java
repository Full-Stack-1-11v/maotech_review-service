package cl.maotech.review_service.review_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.maotech.review_service.review_service.client.CourseFeignClient;
import cl.maotech.review_service.review_service.model.Review;
import cl.maotech.review_service.review_service.repository.ReviewRepository;

@Service
@Transactional
public class ReviewServiceImplement implements ReviewService {

    private CourseFeignClient courseFeignClient;
    private ReviewRepository reviewRepository;


    public ReviewServiceImplement(ReviewRepository reviewRepository, CourseFeignClient courseFeignClient) {
        this.courseFeignClient = courseFeignClient;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<Review> save(Review review) {
        // Validar que el curso existe antes de guardar la reseña
        if (courseFeignClient.findById(review.getCourseId()) != null) {
            return Optional.of(reviewRepository.save(review));
        } else {
            return Optional.empty(); // Retorna vacío si el curso no existe
        }
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll(); 
    }
    
    @Override
    public Optional<Review> findById(Integer id) {
        return reviewRepository.findById(id);
    }

    
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
    

    @Override
    public void delete(Integer id) {
        reviewRepository.deleteById(id);
    }

}
