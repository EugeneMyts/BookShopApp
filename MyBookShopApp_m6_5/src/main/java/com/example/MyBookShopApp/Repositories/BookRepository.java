package com.example.MyBookShopApp.Repositories;

import com.example.MyBookShopApp.data.BookStructure.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByAuthorId(Integer name);
    Page<Book> findBooksByAuthorId(Integer id, Pageable pageable);

    List<Book> findBooksByAuthor_FirstName(String name);

    @Query("from Book")
    List<Book> customFindAllBooks();

    //NEW BOOK REST REPOSITORY COMMANDS

    List<Book> findBooksByAuthorFirstNameContaining(String authorFirstName);

    List<Book> findBooksByTitleContaining(String bookTitle);

    List<Book> findBooksByPriceOldBetween(Integer min, Integer max);

    List<Book> findBooksByPriceOldIs(Integer price);

    @Query(value = "SELECT * FROM books WHERE discount = (SELECT MAX(discount) FROM books)", nativeQuery = true)
    List<Book> getBooksWithMaxDiscount();

    Page<Book> findBookByTitleContaining(String bookTitle, Pageable nextPage);

//    @Query(value = "SELECT * from books order by pub_date desc", nativeQuery = true)
//    Page<Book> getBookByPubDate(Pageable pageable);


//    @Query(value = "select * from books where is_bestseller = 1", nativeQuery = true)
//    Page<Book> getBestSellerBooks(Pageable pageable);

    @Query("from Book where isBestseller=1")
    List<Book> getBestSellers();

    Page<Book> findAllByOrderByBookPopularityDesc(Pageable nexPage);


    Page<Book> findAllBooksByTagsSlug(String slug, Pageable nextPage);


    Page<Book> findBooksByGenresSlug(String slug, Pageable nextPage);

    Page<Book> findByPubDateBetween(LocalDate from, LocalDate to, Pageable nextPage);

Book findBookBySlug(String slug);

}
