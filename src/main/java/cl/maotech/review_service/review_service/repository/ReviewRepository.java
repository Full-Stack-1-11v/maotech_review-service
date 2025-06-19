package cl.maotech.review_service.review_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.maotech.review_service.review_service.model.Review;

/**
 * Repository interface for managing Review entities.
 * This interface extends JpaRepository to provide CRUD operations for Review entities.
 * It is annotated with @Repository to indicate that it is a Spring Data repository.
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

}
