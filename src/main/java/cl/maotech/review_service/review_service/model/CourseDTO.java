package cl.maotech.review_service.review_service.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

/**
 * Data Transfer Object for Course.
 * This class is used to transfer course data between layers.
 */
@AllArgsConstructor
public class CourseDTO {

    /**
     * Unique identifier for the course.
     */
    private Long id;
    /**
     * Course code, typically a unique identifier for the course.
     * This could be a string like "CS101" or "MATH202".
     */
    private String codigo;
    /**
     * Name of the course.
     * This is a human-readable name for the course, such as "Introduction to Computer Science".
     */
    private String nombre;
    /**
     * Description of the course.
     * This provides additional details about the course content and objectives.
     */
    private String descripcion;
    /**
     * Number of credits awarded for completing the course.
     * This is typically an integer value representing the credit hours.
     */
    private Integer creditos;
/**
     * Maximum capacity of the course.
     * This indicates the maximum number of students that can enroll in the course.
     */
    private Integer capacidadMaxima;
/**
     * Identifier of the instructor teaching the course.
     * This is a foreign key that references the instructor's ID.
     */
    private Long instructorId;
    /**
     * Timestamp indicating when the course was created.
     * This is typically used for auditing purposes.
     */
    private LocalDateTime fechaCreacion;
/**
     * Timestamp indicating when the course was last updated.
     * This is also used for auditing purposes to track changes to the course.
     */
    private LocalDateTime fechaActualizacion;
    
    public CourseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Integer getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(Integer capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
