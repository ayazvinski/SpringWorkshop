package pl.coderslab.services;

import pl.coderslab.Book;

import java.util.List;
import java.util.Optional;

public class MockBookService implements BookService{
    private List<Book> list;

    @Override
    public List<Book> getBooks() {
        return null;
    }

    @Override
    public Optional<Book> get(Long id) {
        return Optional.empty();
    }

    @Override
    public void add(Book book) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Book book) {

    }
}
