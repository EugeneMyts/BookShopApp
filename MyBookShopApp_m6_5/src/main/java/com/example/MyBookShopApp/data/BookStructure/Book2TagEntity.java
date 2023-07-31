package com.example.MyBookShopApp.data.BookStructure;

import com.example.MyBookShopApp.data.BookStructure.Book;
import com.example.MyBookShopApp.data.TagEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "book2tag")
public class Book2TagEntity {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    @JsonIgnore
    private TagEntity tagEntity;

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

    public TagEntity getTagEntity() {
        return tagEntity;
    }

    public void setTagEntity(TagEntity tagEntity) {
        this.tagEntity = tagEntity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}