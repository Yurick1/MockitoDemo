package edu.yurickn.mockitodemo.bondary;

import edu.yurickn.mockitodemo.control.BooksController;
import edu.yurickn.mockitodemo.entity.Book;

/**
 * It could must to be a REST service, but it's only the simplest class
 */
public class BooksService {

    private BooksController controller;

    public void setController(BooksController controller) {
        this.controller = controller;
    }

    public Object getBook(Long id) {
        return makeResponse(controller.getBook(id));
    }

    public Object makeResponse(Book book) {
        return book.toString();
    }
}
