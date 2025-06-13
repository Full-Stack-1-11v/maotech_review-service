package cl.maotech.review_service.review_service.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import cl.maotech.review_service.review_service.controller.ReviewController;
import cl.maotech.review_service.review_service.model.Review;

@Component
public class ReviewModelAssembler implements RepresentationModelAssembler<Review, EntityModel<Review>> {

    @Override
    public EntityModel<Review> toModel(Review entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toModel'");
    }

}
