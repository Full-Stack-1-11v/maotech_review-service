package cl.maotech.review_service.review_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.maotech.review_service.review_service.model.CourseDTO;

@FeignClient(url = "localhost:8082/api/v1/cursos", name = "course-service")
public interface CourseFeignClient {

    @GetMapping()
    List<CourseDTO> findAll();
    
    @GetMapping("/{id}")
    CourseDTO findById(@PathVariable Long id);
}
