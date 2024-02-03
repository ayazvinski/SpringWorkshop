package pl.coderslab.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.Book;
import pl.coderslab.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    @ResponseBody
    public List<Book> getList() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Book getBookById(@PathVariable Long id){
        return this.bookService.get(id).orElseThrow(() -> {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found");
    });
}
    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        bookService.add(book);
    }

    @DeleteMapping("/{id}")
    public void removeBook(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PutMapping("")
    @ResponseBody
    public void updateBook(@RequestBody Book book) {
        bookService.update(book);
    }




}

