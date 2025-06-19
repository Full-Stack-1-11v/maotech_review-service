package cl.maotech.review_service.review_service.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReviewTest {

    private final Review review = new Review(1, 5, "Great course!", 1L);

    @Test
    public void testGetId() {
        assertEquals(1, review.getId(), "El ID de la reseña debe ser 1");
    }

    @Test
    public void testGetValue() {
        assertEquals(5, review.getValue(), "La calificación de la reseña debe ser 5");
    }

    @Test
    public void testGetReview() {
        assertEquals("Great course!", review.getReview(), "El comentario de la reseña debe ser 'Great course!'");
    }

    @Test
    public void testGetCourseId() {
        assertEquals(1L, review.getCourseId(), "El ID del curso de la reseña debe ser 1");
    }

    @Test
    public void testSetId() {
        review.setId(2);
        assertEquals(2, review.getId(), "El ID de la reseña debe ser actualizado a 2");
    }

    @Test
    public void testSetValue() {
        review.setValue(4);
        assertEquals(4, review.getValue(), "La calificación de la reseña debe ser actualizada a 4");
    }

    @Test
    public void testSetReview() {
        review.setReview("Updated review");
        assertEquals("Updated review", review.getReview(), "El comentario de la reseña debe ser actualizado a 'Updated review'");
    }

    @Test
    public void testSetCourseId() {
        review.setCourseId(2L);
        assertEquals(2L, review.getCourseId(), "El ID del curso de la reseña debe ser actualizado a 2");
    }

}
