package cl.maotech.review_service.review_service.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.maotech.review_service.review_service.assemblers.ReviewModelAssembler;
import cl.maotech.review_service.review_service.model.Review;
import cl.maotech.review_service.review_service.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * Controlador REST para manejar las operaciones de reseñas.
 * Permite crear, obtener, actualizar y listar reseñas.
 */
@RestController
@RequestMapping("/api/v1/reviews")
@Tag(name = "Review", description = "Controlador para manejar reseñas de productos")
public class ReviewController {

    /**
     * Logger para registrar eventos en el controlador.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private ReviewModelAssembler reviewModelAssembler;

    /**
     * Servicio para manejar la lógica de negocio relacionada con las reseñas.
     * Se inyecta a través del constructor para seguir el principio de inversión de dependencias.
     */
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Crea una nueva reseña.
     * @param review Objeto {@link Review} que contiene los detalles de la reseña a crear.
     * @return Objeto {@link Review} creado
     */
    @Operation(summary = "Crear una nueva reseña", description = "Permite crear una nueva reseña para un curso")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Reseña creada exitosamente"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<EntityModel<Review>> createReview(@RequestBody Review review) {
        LOGGER.info("[createReview] Start: {}", review);
        LOGGER.debug("[createReview] Creating new review", review);
        Optional<Review> newReview = reviewService.save(review);
        
        if (newReview.isEmpty()) {
            LOGGER.error("[createReview] Error creating review: {}", review);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        EntityModel<Review> reviewModel = reviewModelAssembler.toModel(newReview.get());
        LOGGER.info("[createReview] Review created successfully: {}", reviewModel);
        LOGGER.debug("[createReview] Review details: {}", reviewModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewModel);
    }

    /**
     * Obtiene todas las reseñas.
     * @return Lista de objetos {@link Review}
     */
    @Operation(summary = "Obtener todas las reseñas", description = "Permite obtener una lista de todas las reseñas")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de reseñas obtenida exitosamente"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "No se encontraron reseñas")
    })
     @GetMapping
    public ResponseEntity<List<EntityModel<Review>>> getReviews() {
        LOGGER.info("[getReviews] Start fetching all reviews");
        LOGGER.debug("[getReviews] Fetching all reviews from the service");

        List<EntityModel<Review>> reviews = reviewService.findAll().stream().map(reviewModelAssembler::toModel).toList();
        if (reviews.isEmpty()) {
            LOGGER.warn("[getReviews] No reviews found");
            return ResponseEntity.ok(Collections.emptyList());
        }

        LOGGER.info("[getReviews] Successfully fetched {} reviews", reviews.size());
        LOGGER.debug("[getReviews] Reviews details: {}", reviews);
        return ResponseEntity.ok(reviews);
    }

    /**
     * Obtiene una reseña por su ID.
     * @param id ID de la reseña a obtener
     * @return Objeto {@link Review} si se encuentra
     */
    @Operation(summary = "Obtener una reseña por ID", description = "Permite obtener una reseña específica por su ID")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Reseña encontrada"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Reseña no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Review>> getReviewById(@PathVariable Integer id) {
        LOGGER.info("[getReviewById] Start fetching review with ID: {}", id);
        LOGGER.debug("[getReviewById] Fetching review from the service");
        
        Review review = reviewService.findById(id)
                .orElse(null);
        if (review == null) {
            LOGGER.warn("[getReviewById] Review with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
        EntityModel<Review> reviewModel = reviewModelAssembler.toModel(review);

        LOGGER.info("[getReviewById] Successfully fetched review: {}", reviewModel);
        LOGGER.debug("[getReviewById] Review details: {}", reviewModel);
        return ResponseEntity.ok(reviewModel);
    }
    
    /**
     * Actualiza una reseña existente.
     * @param id ID de la reseña a actualizar
     * @param review Objeto {@link Review} con los nuevos datos
     * @return Objeto {@link Review} actualizado
     */
    @Operation(summary = "Actualizar una reseña", description = "Permite actualizar los detalles de una reseña existente")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Reseña actualizada exitosamente"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Reseña no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Integer id, @RequestBody Review review) {
        LOGGER.info("[updateReview] Start updating review with ID: {}", id);
        LOGGER.debug("[updateReview] Review details before update: {}", review);
        
        // Verificar si la reseña existe
        Optional<Review> existingReview = reviewService.findById(id);
        if (existingReview.isEmpty()) {
            LOGGER.warn("[updateReview] Review with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }

        LOGGER.debug("[updateReview] Existing review found: {}", existingReview.get());
        return reviewService.update(review)
                .map(updatedReview -> ResponseEntity.ok(updatedReview))
                .orElse(ResponseEntity.notFound().build());
    }

}
