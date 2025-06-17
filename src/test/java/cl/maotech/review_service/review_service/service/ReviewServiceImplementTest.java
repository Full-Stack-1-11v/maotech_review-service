package cl.maotech.review_service.review_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import cl.maotech.review_service.review_service.model.Review;
import cl.maotech.review_service.review_service.repository.ReviewRepository;

@SpringBootTest
@ActiveProfiles("test")
public class ReviewServiceImplementTest {

    @InjectMocks
    private ReviewServiceImplement reviewServiceImplement;

    @Mock
    private ReviewRepository reviewRepository;

    public void testFindAll() {
        // Given
        List<Review> mockReviews = List.of(
            new Review(1, 5, "Great course!", 1L),
            new Review(2, 4, "Very informative.", 1L)
        );

        // When
        // Llamar al método que quieres probar
        when(reviewRepository.findAll()).thenReturn(mockReviews);

        // Then
        // Aquí puedes verificar el resultado esperado
        List<Review> reviews = reviewServiceImplement.findAll();
        assertEquals(null, reviews);
    }

    public void testSave() {
        // Given
        Review mockReview = new Review(1, 5, "Great course!", 1L);

        // When
        when(reviewRepository.save(mockReview)).thenReturn(mockReview);

        // Then
        Review savedReview = reviewServiceImplement.save(mockReview).orElse(null);
        assertEquals(mockReview, savedReview);
    }

    public void testFindById() {
        // Given
        Integer reviewId = 1;
        Review mockReview = new Review(reviewId, 5, "Great course!", 1L);

        // When
        when(reviewRepository.findById(reviewId)).thenReturn(java.util.Optional.of(mockReview));

        // Then
        Review foundReview = reviewServiceImplement.findById(reviewId).orElse(null);
        assertEquals(mockReview, foundReview);
    }

    public void testUpdate() {
        // Given
        Review existingReview = new Review(1, 5, "Great course!", 1L);
        Review updatedReview = new Review(1, 4, "Very good course!", 1L);

        // When
        when(reviewRepository.findById(existingReview.getId())).thenReturn(java.util.Optional.of(existingReview));
        when(reviewRepository.save(existingReview)).thenReturn(updatedReview);

        // Then
        Review result = reviewServiceImplement.update(updatedReview).orElse(null);
        assertEquals(updatedReview, result);
    }


}
