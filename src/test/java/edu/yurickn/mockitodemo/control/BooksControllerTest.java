package edu.yurickn.mockitodemo.control;

import edu.yurickn.mockitodemo.entity.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.concurrent.atomic.AtomicReference;

@ExtendWith(MockitoExtension.class)
public class BooksControllerTest {

    private BooksController controller;
    @Mock
    private BooksRepository repository;

    @BeforeEach
    void setUp() {
        controller = new BooksController(repository);
    }

    @Test
    void testCreateBook() {
        String name = "some name";
        String author = "some author";
        Long expectedId = 132L;
        AtomicReference<Book> savedBook = new AtomicReference<>();

        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
        Mockito.when(repository.saveBook(captor.capture())).then((Answer<Long>) invocationOnMock -> {
            Book book = invocationOnMock.getArgument(0, Book.class);
            if (book.getId() == null)
                book.setId(expectedId);
            savedBook.set(book);
            return book.getId();
        });


        Long actualId = controller.createBook(name, author);

        Assertions.assertNotNull(actualId);
        Assertions.assertEquals(expectedId, actualId);

        Book actualBook = savedBook.get();
        Assertions.assertNotNull(actualBook);
        Assertions.assertEquals(expectedId, actualBook.getId());
        Assertions.assertEquals(name, actualBook.getName());
        Assertions.assertEquals(author, actualBook.getAuthor());
    }

    @Test
    void testFindBookById() {
        Long id = 123L;
        Book expectedBook = new Book();
        expectedBook.setId(id);
        expectedBook.setName("some name");
        expectedBook.setAuthor("some author");
        Mockito.when(repository.getBook(id)).thenReturn(expectedBook);

        Book actualBook = controller.getBook(id);
        Assertions.assertNotNull(actualBook);
        Assertions.assertEquals(expectedBook.getId(), actualBook.getId());
        Assertions.assertEquals(expectedBook.getName(), actualBook.getName());
        Assertions.assertEquals(expectedBook.getAuthor(), actualBook.getAuthor());
    }

    @Test
    void testRemoveBook() {
        Long id = 123L;
        controller.removeBook(id);
        Mockito.verify(repository, Mockito.atLeastOnce()).removeBook(id);
    }

    @AfterEach
    void tearDown() {
        controller = null;
    }
}
