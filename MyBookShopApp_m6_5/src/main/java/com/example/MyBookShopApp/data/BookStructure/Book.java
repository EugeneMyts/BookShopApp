package com.example.MyBookShopApp.data.BookStructure;

import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.data.GenreEntity;
import com.example.MyBookShopApp.data.TagEntity;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@ApiModel(description = "entity representing a book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id generated by db automatically")
    private Integer id;

    @Column(name = "pub_date")
    @ApiModelProperty("date of book publication")
    @JsonProperty("pubDate")
    private LocalDate pubDate;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @JsonProperty("author")
    @JsonIgnore
    private Author author;

    @JsonGetter("authors")
    public String authorsFullName() {
        return author.toString();
    }

    @Column(name = "is_bestseller")
    @ApiModelProperty("if isBestseller = 1 so the book is considered to be bestseller and  if 0 the book is not a " +
            "bestseller")
    private Integer isBestseller;

    @ApiModelProperty("mnemonical identity sequence of characters")
    private String slug;
    @ApiModelProperty("book title")
    private String title;
    @ApiModelProperty("image url")
    private String image;



    @Column(columnDefinition = "TEXT")
    @ApiModelProperty("book description text")
    private String description;

    @Column(name = "price")
    @JsonProperty("price")
    @ApiModelProperty("book price without discount")
    private Integer priceOld;

    @Column(name = "discount")
    @JsonProperty("discount")
    @ApiModelProperty("discount value for book")
    private Double price;


    @OneToOne
    @JoinTable(name = "book2genre",
            joinColumns = @JoinColumn(name = "book_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id", nullable = false))
    @ApiModelProperty("book genres")
    @JsonIgnore
    private GenreEntity genres;

    @OneToMany(mappedBy = "book")
    private List<BookFile> bookFileList = new ArrayList<>();


    @Formula("(SELECT count(bu.id) FROM book2user bu join book2user_type but on bu.type_id=but.id WHERE bu.book_id = id AND but.code='PAID') + " +
            "((SELECT count(bu.id) FROM book2user bu join book2user_type but on bu.type_id=but.id WHERE bu.book_id = id AND but.code='CART') * 0.7) +" +
            "((SELECT count(bu.id) FROM book2user bu join book2user_type but on bu.type_id=but.id WHERE bu.book_id = id AND but.code='KEPT') * 0.4)")
    private Double bookPopularity;


    @OneToOne
    @JoinTable(name = "book2tag",
            joinColumns = @JoinColumn(name = "book_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id", nullable = false))
    @ApiModelProperty("book tags")
    @JsonIgnore
    private TagEntity tags;

    @OneToMany(mappedBy = "book")
    @ApiModelProperty("book user")
    @JsonIgnore
    private List<Book2UserEntity> book2User;


    @JsonProperty
    public Integer discountPrice(){
        Integer discountedPriceInt = priceOld - Math.toIntExact(Math.round(price*priceOld));
        return discountedPriceInt;
    }


    public List<BookFile> getBookFileList() {
        return bookFileList;
    }

    public void setBookFileList(List<BookFile> bookFileList) {
        this.bookFileList = bookFileList;
    }

    public Double getBookPopularity() {
        return bookPopularity;
    }

    public void setBookPopularity(Double bookPopularity) {
        this.bookPopularity = bookPopularity;
    }

    public GenreEntity getGenres() {
        return genres;
    }

    public void setGenres(GenreEntity genres) {
        this.genres = genres;
    }

    public TagEntity getTags() {
        return tags;
    }

    public void setTags(TagEntity tags) {
        this.tags = tags;
    }

    public LocalDate getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDate pubDate) {
        this.pubDate = pubDate;
    }

    public List<Book2UserEntity> getBook2User() {
        return book2User;
    }

    public void setBook2User(List<Book2UserEntity> book2User) {
        this.book2User = book2User;
    }

    public Integer getIsBestseller() {
        return isBestseller;
    }

    public void setIsBestseller(Integer isBestseller) {
        this.isBestseller = isBestseller;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(Integer priceOld) {
        this.priceOld = priceOld;
    }

    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", priceOld='" + priceOld + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
