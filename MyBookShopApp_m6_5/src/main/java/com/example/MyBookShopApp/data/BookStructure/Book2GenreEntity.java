package com.example.MyBookShopApp.data.BookStructure;

import com.example.MyBookShopApp.data.BookStructure.Book;
import com.example.MyBookShopApp.data.GenreEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "book2genre")
public class Book2GenreEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(columnDefinition = "INT NOT NULL")
//    private int bookId;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    @JsonIgnore
    private GenreEntity genreEntity;

    @OneToOne
    @JoinColumn(name = "book_id")
    @MapsId
    private Book book;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GenreEntity getGenreEntity() {
        return genreEntity;
    }

    public void setGenreEntity(GenreEntity genreEntity) {
        this.genreEntity = genreEntity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}