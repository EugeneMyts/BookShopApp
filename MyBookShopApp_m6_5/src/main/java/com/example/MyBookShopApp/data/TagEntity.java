package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.BookStructure.Book2TagEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tag")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String slug;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @OneToMany(mappedBy = "tagEntity")
    @JsonIgnore
    private List<Book2TagEntity> bookToTagList;

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

    public List<Book2TagEntity> getBookToTagList() {
        return bookToTagList;
    }

    public void setBookToTagList(List<Book2TagEntity> bookToTagList) {
        this.bookToTagList = bookToTagList;
    }

    public String getTagClass() {
        int size = this.bookToTagList.size();
        if (size > 40) return "Tag_lg";
        else if (size>20) return "Tag_md";
        else if (size>10) return "Tag_sm";
        else return "Tag_xs";
    }
}