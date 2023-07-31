package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.Dto.BooksPageDto;
import com.example.MyBookShopApp.Services.BookService;
import com.example.MyBookShopApp.Services.GenreService;
import com.example.MyBookShopApp.Services.TagService;
import com.example.MyBookShopApp.data.*;
import com.example.MyBookShopApp.data.BookStructure.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {

    private final BookService bookService;
    private final TagService tagService;

    private final GenreService genreService;


    @Autowired
    public MainPageController(BookService bookService, TagService tagService, GenreService genreService) {
        this.bookService = bookService;
        this.tagService = tagService;
        this.genreService = genreService;
    }

   @ModelAttribute("tagList")
   public List<TagEntity> tagList() {
        return tagService.getTagData();
   }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getPageOfRecommendedBooks(0, 6).getContent();
    }

    @ModelAttribute("recentBooks")
    public List<Book> recentBooks() {
        return bookService.getPageOfRecentBooks(0, 6).getContent();
    }

    @ModelAttribute("popularBooks")
    public List<Book> popularBooks(){
        return bookService.getPageOfPopularBooks(0,6).getContent();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResults() {
        return new ArrayList<>();
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksPageDto getBooksPage(@RequestParam("offset") Integer offset,
                                     @RequestParam("limit") Integer limit) {
        return new BooksPageDto(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }







//    @GetMapping("/books/popular")
//    @ResponseBody
//    public BooksPageDto getPopularPage(@RequestParam("offset") Integer offset,
//                                       @RequestParam("limit") Integer limit) {
//        return new BooksPageDto(bookService.getPageOfPopularBooks(offset, limit).getContent());
//    }

    @GetMapping(value = {"/search", "/search/{searchWord}"})
    public String getSearchResults(@PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto,
                                   Model model) {
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5).getContent());
        return "/search/index";
    }

    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BooksPageDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit,
                                          @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto) {
        return new BooksPageDto(bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit).getContent());
    }






}


