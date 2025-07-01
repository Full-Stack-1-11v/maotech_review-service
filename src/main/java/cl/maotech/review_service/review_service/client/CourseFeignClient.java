package cl.maotech.review_service.review_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.maotech.review_service.review_service.model.CourseDTO;

/**
 * Feign client for interacting with the Course Service.
 * This client provides methods to retrieve course information from the Course Service.
 */
@FeignClient(url = "http://localhost:8080/api/courses", name = "course-service")
public interface CourseFeignClient {

    /**
     * Retrieves a list of all courses. 
     * @return a list of CourseDTO objects representing all courses.
     */
    @GetMapping()
    List<CourseDTO> findAll();
    
    /**
     * Retrieves a course by its ID.
     * @param id the ID of the course to retrieve.
     * @return a CourseDTO object representing the course with the specified ID.
     */
    @GetMapping("/{id}")
    CourseDTO findById(@PathVariable Long id);
}
