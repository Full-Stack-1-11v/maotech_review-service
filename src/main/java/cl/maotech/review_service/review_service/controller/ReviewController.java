package cl.maotech.review_service.review_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api/v1/review")
@Tag(name = "Review", description = "Controlador para manejar reseñas de productos")
public class ReviewController {

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
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return reviewService.save(review)
                .map(savedReview -> ResponseEntity.status(201).body(savedReview))
                .orElse(ResponseEntity.badRequest().build());
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
        List<EntityModel<Review>> reviews = reviewService.findAll().stream().map(reviewModelAssembler::toModel).toList();
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
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
    public ResponseEntity<Review> getReviewById(@PathVariable Integer id) {
        return reviewService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
    @PostMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Integer id, @RequestBody Review review) {
        return reviewService.update(review)
                .map(updatedReview -> ResponseEntity.ok(updatedReview))
                .orElse(ResponseEntity.notFound().build());
    }

}
