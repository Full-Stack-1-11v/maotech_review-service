package cl.maotech.review_service.review_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.maotech.review_service.review_service.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

}
