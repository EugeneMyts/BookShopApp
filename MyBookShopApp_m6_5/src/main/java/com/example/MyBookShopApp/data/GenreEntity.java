package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.BookStructure.Book2GenreEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genre")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "INT", name = "parent_id")
    private int parentId;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String slug;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @OneToMany(mappedBy = "genreEntity")
    @JsonIgnore
    private List<Book2GenreEntity> bookToGenreList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book2GenreEntity> getBookToGenreList() {
        return bookToGenreList;
    }

    public void setBookToGenreList(List<Book2GenreEntity> bookToGenreList) {
        this.bookToGenreList = bookToGenreList;
    }
}