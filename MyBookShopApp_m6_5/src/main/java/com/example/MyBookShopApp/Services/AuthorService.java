package com.example.MyBookShopApp.Services;

import com.example.MyBookShopApp.Repositories.AuthorRepository;
import com.example.MyBookShopApp.Repositories.BookRepository;
import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.data.BookStructure.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {

   private final AuthorRepository authorRepository;
   private final BookRepository bookRepository;

   @Autowired
    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Map<String, List<Author>> getAuthorMap() {
       List<Author> authors = authorRepository.findAll();
       return authors.stream().collect(Collectors.groupingBy((Author a) -> a.getLastName().substring(0,1)));
    }

    public Author getAuthorBySlug(String slug) {
       return authorRepository.findAuthorBySlug(slug);
    }

    public Page<Book> getPageOfAuthorBooks(Integer authorId, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.Direction.DESC, "pubDate", "title");
        return bookRepository.findBooksByAuthorId(authorId, nextPage);
    }

}
