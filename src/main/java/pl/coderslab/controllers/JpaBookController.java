package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.Book;
import pl.coderslab.services.BookService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/books")
public class JpaBookController {
    private BookService bookService;

    public JpaBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public String showAll(Model model) {
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "allBooks";
    }

    @GetMapping("/add")
    public String showAdd(Model model){
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/add")
    public String saveBook (@Valid Book book, BindingResult result){
        if(result.hasErrors()){
            return "addBook";
        }
        bookService.add(book);
        return "redirect:/admin/books/all";
    }

    @GetMapping("edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        model.addAttribute("book", bookService.get(id));
        return "editBook";
    }

    @PostMapping("/edit")
    public String editBook (@Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "editBook";
        }
        bookService.add(book);
        return "redirect:/admin/books/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable long id) {
        bookService.delete(id);
        return "redirect:/admin/books/all";
    }

}
