package cl.maotech.review_service.review_service.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class CourseDTOTest {;

    public final CourseDTO courseDTO = new CourseDTO(
        1L, 
        "CS101", 
        "Introduction to Computer Science", 
        "This course covers the basics of computer science.", 
        3, 
        30, 
        1L, 
        LocalDateTime.now(), 
        LocalDateTime.now()
    );

    @Test
    public void testGetId() {
        assertEquals(1L, courseDTO.getId(), "El ID del curso debe ser 1");
    }

    @Test
    public void testGetCodigo() {
        assertEquals("CS101", courseDTO.getCodigo(), "El código del curso debe ser 'CS101'");
    }

    @Test
    public void testGetNombre() {
        assertEquals("Introduction to Computer Science", courseDTO.getNombre(), "El nombre del curso debe ser 'Introduction to Computer Science'");
    }

    @Test
    public void testGetDescripcion() {  
        assertEquals("This course covers the basics of computer science.", courseDTO.getDescripcion(), "La descripción del curso debe ser 'This course covers the basics of computer science.'");
    }

    @Test
    public void testGetCreditos() {
        assertEquals(3, courseDTO.getCreditos(), "Los créditos del curso deben ser 3");
    }

    @Test
    public void testGetCapacidadMaxima() {
        assertEquals(30, courseDTO.getCapacidadMaxima(), "La capacidad máxima del curso debe ser 30");
    }

    @Test
    public void testGetInstructorId() {
        assertEquals(1L, courseDTO.getInstructorId(), "El ID del instructor del curso debe ser 1");
    }

    @Test
    public void testGetFechaCreacion() {
        assertEquals(courseDTO.getFechaCreacion().getDayOfYear(), LocalDateTime.now().getDayOfYear(), "La fecha de creación del curso debe ser la fecha actual");
    }

    @Test
    public void testGetFechaActualizacion() {
        assertEquals(courseDTO.getFechaActualizacion().getDayOfYear(), LocalDateTime.now().getDayOfYear(), "La fecha de actualización del curso debe ser la fecha actual");
    }

    @Test
    public void testSetId() {   
        courseDTO.setId(2L);
        assertEquals(2L, courseDTO.getId(), "El ID del curso debe ser actualizado a 2");
    }

    @Test
    public void testSetCodigo() {
        courseDTO.setCodigo("CS102");
        assertEquals("CS102", courseDTO.getCodigo(), "El código del curso debe ser actualizado a 'CS102'");
    }

    @Test
    public void testSetNombre() {
        courseDTO.setNombre("Advanced Computer Science");
        assertEquals("Advanced Computer Science", courseDTO.getNombre(), "El nombre del curso debe ser actualizado a 'Advanced Computer Science'");
    }

    @Test
    public void testSetDescripcion() {
        courseDTO.setDescripcion("This course covers advanced topics in computer science.");
        assertEquals("This course covers advanced topics in computer science.", courseDTO.getDescripcion(), "La descripción del curso debe ser actualizada a 'This course covers advanced topics in computer science.'");
    }

    @Test
    public void testSetCreditos() {
        courseDTO.setCreditos(4);
        assertEquals(4, courseDTO.getCreditos(), "Los créditos del curso deben ser actualizados a 4");
    }

    @Test
    public void testSetCapacidadMaxima() {
        courseDTO.setCapacidadMaxima(40);
        assertEquals(40, courseDTO.getCapacidadMaxima(), "La capacidad máxima del curso debe ser actualizada a 40");
    }

    @Test
    public void testSetInstructorId() {
        courseDTO.setInstructorId(2L);
        assertEquals(2L, courseDTO.getInstructorId(), "El ID del instructor del curso debe ser actualizado a 2");
    }

    @Test
    public void testSetFechaCreacion() {
        LocalDateTime newDate = LocalDateTime.of(2023, 10, 1, 12, 0);
        courseDTO.setFechaCreacion(newDate);
        assertEquals(newDate.getDayOfYear(), courseDTO.getFechaCreacion().getDayOfYear(), "La fecha de creación del curso debe ser actualizada a la nueva fecha");
    }

    @Test
    public void testSetFechaActualizacion() {
        LocalDateTime newDate = LocalDateTime.of(2023, 10, 1, 12, 0);
        courseDTO.setFechaActualizacion(newDate);
        assertEquals(newDate.getDayOfYear(), courseDTO.getFechaActualizacion().getDayOfYear(), "La fecha de actualización del curso debe ser actualizada a la nueva fecha");
    }

}
