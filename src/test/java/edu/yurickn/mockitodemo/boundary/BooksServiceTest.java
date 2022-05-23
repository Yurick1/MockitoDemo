package edu.yurickn.mockitodemo.boundary;

import edu.yurickn.mockitodemo.bondary.BooksService;
import edu.yurickn.mockitodemo.control.BooksController;
import edu.yurickn.mockitodemo.entity.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BooksServiceTest {

    /**
     * {@code @Spy} not overrides default implementation of this class. Try to change to {@code @Mock} to see
     * the difference as next error:
     * {@code java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "actualO" is null}.
     * This is testing object, but we need to make it spy to be able to override the makeResponse method.
     */
    @Spy
    private BooksService service;
    /**
     * We need the mock here to override all methods of this class to avoid the dependency from this tests to
     * the BooksRepository interface. Try to change to {@code @Spy} to see the difference as next error:
     * {@code java.lang.NullPointerException: Cannot invoke "edu.yurickn.mockitodemo.control.BooksRepository.getBook(java.lang.Long)" because "this.repository" is null}
     */
    @Mock
    private BooksController controller;


    @BeforeEach
    void setUp() {
        service.setController(controller);
    }

    @Test
    void testGetBook() {
        Long expectedId = 123L;
        Book expected = new Book();
        expected.setId(expectedId);
        expected.setName("some name");
        expected.setAuthor("some author");
        //Explicitly overriding required method in the mock
        Mockito.when(controller.getBook(expectedId)).thenReturn(expected);
        //Explicitly overriding only one method in the spy
        Mockito.when(service.makeResponse(expected)).thenReturn(expected);

        Object actualO = service.getBook(expectedId);
        Assertions.assertEquals(Book.class, actualO.getClass());
        Book actual = (Book) actualO;

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getAuthor(), actual.getAuthor());

    }

    @AfterEach
    void tearDown() {
        service = null;
        controller = null;
    }
}
