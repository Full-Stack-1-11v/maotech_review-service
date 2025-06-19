package cl.maotech.review_service.review_service.controller;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import cl.maotech.review_service.review_service.assemblers.ReviewModelAssembler;
import cl.maotech.review_service.review_service.model.Review;
import cl.maotech.review_service.review_service.service.ReviewServiceImplement;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReviewServiceImplement reviewService;

    @MockBean
    private ReviewModelAssembler reviewModelAssembler;
    
    @Test
    void createReview_ok() throws Exception {
        Review review = new Review();
        review.setId(1);
        review.setReview("Great product!");
        review.setValue(5);
        review.setCourseId(1L);
        Mockito.when(reviewService.save(any(Review.class))).thenReturn(Optional.of(review));
        Mockito.when(reviewModelAssembler.toModel(any(Review.class)))
            .thenReturn(org.springframework.hateoas.EntityModel.of(review));
        mockMvc.perform(post("/api/v1/reviews")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"review\":\"Great product!\",\"value\":5,\"courseId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void createReview_badRequest() throws Exception {
        mockMvc.perform(post("/api/v1/reviews")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"review\":\"\",\"value\":5,\"courseId\":1}"))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void listReviews_ok() throws Exception {
        Review review = new Review();
        review.setId(1);
        review.setReview("Great product!");
        review.setValue(5);
        review.setCourseId(1L);

        Mockito.when(reviewService.findAll()).thenReturn(Collections.singletonList(review));
        Mockito.when(reviewModelAssembler.toModel(any(Review.class)))
            .thenReturn(org.springframework.hateoas.EntityModel.of(review));

        mockMvc.perform(get("/api/v1/reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].review").value("Great product!"))
                .andExpect(jsonPath("$[0].value").value(5))
                .andExpect(jsonPath("$[0].courseId").value(1L));
    }

    @Test
    void listReviews_empty() throws Exception {
        Mockito.when(reviewService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getReviewById_ok() throws Exception {
        Review review = new Review();
        review.setId(1);
        review.setReview("Great product!");
        review.setValue(5);
        review.setCourseId(1L);

        Mockito.when(reviewService.findById(1)).thenReturn(Optional.of(review));
        Mockito.when(reviewModelAssembler.toModel(any(Review.class)))
            .thenReturn(org.springframework.hateoas.EntityModel.of(review));

        mockMvc.perform(get("/api/v1/reviews/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.review").value("Great product!"))
                .andExpect(jsonPath("$.value").value(5))
                .andExpect(jsonPath("$.courseId").value(1L));
    }

    @Test
    void getReviewById_notFound() throws Exception {
        Mockito.when(reviewService.findById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/reviews/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updatedReview_ok() throws Exception {
        Review review = new Review();
        review.setId(1);
        review.setReview("Updated review");
        review.setValue(4);
        review.setCourseId(1L);

        Mockito.when(reviewService.findById(1)).thenReturn(Optional.of(review));
        Mockito.when(reviewService.update(any(Review.class))).thenReturn(Optional.of(review));

        mockMvc.perform(put("/api/v1/reviews/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"review\":\"Updated review\",\"value\":4,\"courseId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.review").value("Updated review"))
                .andExpect(jsonPath("$.value").value(4))
                .andExpect(jsonPath("$.courseId").value(1L));
    }

    @Test
    void updatedReview_notFound() throws Exception {
        Mockito.when(reviewService.findById(1)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/v1/reviews/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"review\":\"Updated review\",\"value\":4,\"courseId\":1}"))
                .andExpect(status().isNotFound());
    }
}