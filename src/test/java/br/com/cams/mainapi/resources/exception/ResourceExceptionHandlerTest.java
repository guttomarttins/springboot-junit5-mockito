package br.com.cams.mainapi.resources.exception;

import br.com.cams.mainapi.resources.UserResource;
import br.com.cams.mainapi.services.exceptions.DataIntegrityViolationException;
import br.com.cams.mainapi.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ResourceExceptionHandlerTest {

    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final String MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema!";
    @InjectMocks
    private ResourceExceptionHandler resourceExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundThenReturnResponseEntity() {
        ResponseEntity<StandardError> response = resourceExceptionHandler.objectNotFound(
                new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO),
                new MockHttpServletRequest()
        );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("user/2", response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }

    @Test
    void whenDataIntegrityViolationException() {
        ResponseEntity<StandardError> response = resourceExceptionHandler.dataIntegrity(
                new DataIntegrityViolationException(MAIL_JA_CADASTRADO_NO_SISTEMA),
                new MockHttpServletRequest()
        );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(MAIL_JA_CADASTRADO_NO_SISTEMA, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }
}