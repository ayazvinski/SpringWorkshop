package pl.coderslab.services;

import org.springframework.stereotype.Service;
import pl.coderslab.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MockBookService implements BookService{

    private List<Book> books;
    private static Long nextId = 4L;

    public MockBookService() {
        books = new ArrayList<>();
        books.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce	Eckel", "Helion", "programming"));
        books.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        books.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
                "programming"));
    }


    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public Optional<Book> get(Long id) {
        return books.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    @Override
    public void add(Book book) {
        book.setId(nextId++);
        books.add(book);
    }


    @Override
    public void delete(Long id) {
        Optional<Book> bookToDelete = get(id);
        bookToDelete.ifPresent(book -> books.remove(book));
    }


    @Override
    public void update(Book book) {
        if (this.get(book.getId()).isPresent()) {
            int indexOf = books.indexOf(this.get(book.getId()).get());
            books.set(indexOf, book);
        }
    }

}
