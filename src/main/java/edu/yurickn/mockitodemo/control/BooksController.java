package edu.yurickn.mockitodemo.control;

import edu.yurickn.mockitodemo.entity.Book;

public class BooksController {

    private BooksRepository repository;

    public BooksController() {

    }

    public void setRepository(BooksRepository repository) {
        this.repository = repository;
    }

    public Long createBook(String name, String author) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        return repository.saveBook(book);
    }

    public Book getBook(Long id) {
        return repository.getBook(id);
    }

    public void removeBook(Long id) {
        repository.removeBook(id);//Remove this line to see how dp works Mockito.verify
    }
}
