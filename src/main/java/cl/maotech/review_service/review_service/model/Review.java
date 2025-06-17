package cl.maotech.review_service.review_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class representing a review entity.
 */
@Data
@Entity
@Table(name = "review")
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    /**
     * Unique identifier for the review.
     * This is the primary key and is auto-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Rating value of the review.
     */
    @Column(nullable = false)
    private Integer value;
    
    /**
     * Text content of the review.
     */
    private String review;

    /**
     * Identifier of the course associated with this review.
     */
    @Column(name = "course_id", nullable = false)
    private Long courseId; 
    
}
