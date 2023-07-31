package com.example.MyBookShopApp.Dto;

import com.example.MyBookShopApp.data.BookStructure.Book2GenreEntity;
import com.example.MyBookShopApp.data.GenreEntity;

import java.util.ArrayList;
import java.util.List;

public class GenresDto {
    private int id;
    private int parentId;
    private String slug;
    private String name;
    private List<GenresDto> genresChildList;
    private List<Book2GenreEntity> book2GenreEntities;

    public GenresDto(GenreEntity genreEntity) {
        this.id = genreEntity.getId();
        this.parentId = genreEntity.getParentId();
        this.slug = genreEntity.getSlug();
        this.name = genreEntity.getName();
        this.genresChildList = new ArrayList<>();
        this.book2GenreEntities = genreEntity.getBookToGenreList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<GenresDto> getGenresChildList() {
        return genresChildList;
    }

    public void setGenresChildList(List<GenresDto> genresChildList) {
        this.genresChildList = genresChildList;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<Book2GenreEntity> getBook2GenreEntities() {
        return book2GenreEntities;
    }

    public void setBook2GenreEntities(List<Book2GenreEntity> book2GenreEntities) {
        this.book2GenreEntities = book2GenreEntities;
    }
}