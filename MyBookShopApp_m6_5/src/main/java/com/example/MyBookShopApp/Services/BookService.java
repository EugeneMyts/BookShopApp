package com.example.MyBookShopApp.Services;

import com.example.MyBookShopApp.Repositories.BookRepository;
import com.example.MyBookShopApp.Repositories.GenreRepository;
import com.example.MyBookShopApp.data.BookStructure.Book;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
    private GenreRepository genreRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

   Mapper mapper = new Mapper();

    public List<Book> getBooksData() {
        return bookRepository.findAll();
    }

    //NEW BOOK SEVICE METHODS

    public List<Book> getBooksByAuthor(String authorName){
        return bookRepository.findBooksByAuthorFirstNameContaining(authorName);
    }

    public List<Book> getBooksByTitle(String title){
        return bookRepository.findBooksByTitleContaining(title);
    }

    public List<Book> getBooksWithPriceBetween(Integer min, Integer max){
        return bookRepository.findBooksByPriceOldBetween(min,max);
    }

    public List<Book> getBooksWithPrice(Integer price){
        return bookRepository.findBooksByPriceOldIs(price);
    }

    public List<Book> getBooksWithMaxPrice(){
        return bookRepository.getBooksWithMaxDiscount();
    }



    public Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfRecentBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.Direction.DESC, "pubDate", "title");
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfPopularBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAllByOrderByBookPopularityDesc(nextPage);
    }

    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findBookByTitleContaining(searchWord,nextPage);
    }

    public Page<Book> getPageOfRecentBooksPubDateBetween(LocalDate from, LocalDate to, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset,limit, Sort.Direction.DESC, "pubDate", "title");
        return bookRepository.findByPubDateBetween(from, to, nextPage);
    }

//    public Page<Book> getBooksByTagSlug(String slug, Integer offset, Integer limit) {
//        Pageable nextPage = PageRequest.of(offset, limit);
//        return bookRepository.getBooksByTagSlug(slug, nextPage);
//    }

//    public GenreEntity getGenre(Integer id) {
//        return genreRepository.findGenreEntitiesById(id).get(id);
//    }

//    public Book getBooksByGenreDto (Integer genreId, Integer offset, Integer limit) {
//        Pageable nextPage = PageRequest.of(offset, limit);
//        GenreEntity genreEntity = genreRepository.findByGenre(genreId, nextPage);
//
//        Page<Book> page = bookRepository.findByGenre(genre, nextPage);
//
//        Book
//    }

}
