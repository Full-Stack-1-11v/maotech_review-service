package cl.maotech.review_service.review_service.controller;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import cl.maotech.review_service.review_service.service.ReviewServiceImplement;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReviewServiceImplement reviewService;

    @Test
    void listReviews() throws Exception {
        Mockito.when(reviewService.findAll()).thenReturn(Collections.emptyList());

        //mockMvc.perform()
    }
}
