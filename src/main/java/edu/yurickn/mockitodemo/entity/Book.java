package edu.yurickn.mockitodemo.entity;

import java.util.Objects;

public class Book {
    private Long id;
    private String name;
    private String author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId()) && Objects.equals(getName(), book.getName())
                && Objects.equals(getAuthor(), book.getAuthor());
    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getName(), getAuthor());
//    }
}
