package cl.maotech.review_service.review_service.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import cl.maotech.review_service.review_service.controller.ReviewController;
import cl.maotech.review_service.review_service.model.Review;

@Component
public class ReviewModelAssembler implements RepresentationModelAssembler<Review, EntityModel<Review>> {

    @Override
    public EntityModel<Review> toModel(Review review) {
        Link selfLink = linkTo(methodOn(ReviewController.class).getReviewById(review.getId()))
                .withSelfRel();
        Link allPacientesLink = linkTo(methodOn(ReviewController.class).getReviews()).withRel("pacientes");

        // Link de creación marcado como POST
        Link crearLink = linkTo(methodOn(ReviewController.class).updateReview(review.getId(), review))
                .withRel("crear")
                .withType("POST");

        return EntityModel.of(review, selfLink, allPacientesLink, crearLink);
        
    }
    
}
