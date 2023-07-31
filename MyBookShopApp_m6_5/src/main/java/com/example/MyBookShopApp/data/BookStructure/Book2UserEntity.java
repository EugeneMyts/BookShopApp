package com.example.MyBookShopApp.data.BookStructure;

import com.example.MyBookShopApp.data.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book2user")
public class Book2UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "DATE NOT NULL")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private Book2UserTypeEntity book2UserTypeEntity;

    @ManyToOne()
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @JsonProperty("book")
    @JsonIgnore
    private Book book;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonProperty("user")
    @JsonIgnore
    private UserEntity user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Book2UserTypeEntity getBook2UserTypeEntity() {
        return book2UserTypeEntity;
    }

    public void setBook2UserTypeEntity(Book2UserTypeEntity book2UserTypeEntity) {
        this.book2UserTypeEntity = book2UserTypeEntity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
