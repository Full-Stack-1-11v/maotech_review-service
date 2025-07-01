package cl.maotech.review_service.review_service.assemblers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import cl.maotech.review_service.review_service.model.Review;

public class ReviewModelAssemblerTest {

    private final ReviewModelAssembler assembler = new ReviewModelAssembler();

    @Test
    public void testToModel_returnsEntityModelWithExpectedLinks() {
        // Given
        Review review = new Review(1, 5, "Great course!", 1L);
        
        // When
        EntityModel<Review> entityModel = assembler.toModel(review);
        
        // Then
        // Verificamos que el contenido sea la reseña creada
        assertNotNull(entityModel, "El EntityModel no debe ser nulo");
        assertEquals(review, entityModel.getContent(), "El contenido del EntityModel es incorrecto");
        
        // Verificamos que existan los links esperados: self, pacientes, crear.
        assertTrue(entityModel.getLink("self").isPresent(), "No se encontró el link 'self'");
        assertTrue(entityModel.getLink("pacientes").isPresent(), "No se encontró el link 'pacientes'");
        assertTrue(entityModel.getLink("crear").isPresent(), "No se encontró el link 'crear'");
        
        // Opcional: verificar que el link 'self' contenga el id esperado
        String selfHref = entityModel.getLink("self").get().getHref();
        assertTrue(selfHref.contains("/api/v1/reviews/1"), "El link 'self' no contiene la URL esperada");
    }
    
}
