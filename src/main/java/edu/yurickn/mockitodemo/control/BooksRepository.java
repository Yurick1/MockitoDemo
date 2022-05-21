package edu.yurickn.mockitodemo.control;

import edu.yurickn.mockitodemo.entity.Book;

public interface BooksRepository {
    Long saveBook(Book book);
    Book getBook(Long id);
    void removeBook(Long id);
}
