package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.Services.TagService;
import com.example.MyBookShopApp.Dto.BooksPageDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TagController {

    private final TagService tagService;


    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tags/{slug}")
    public String tagPage(@PathVariable("slug") String slug, Model model) {
        model.addAttribute("pageSlug", slug);
        model.addAttribute("bookList", tagService.getPageOfTagBook(slug, 0, 10).getContent());
        model.addAttribute("tag", tagService.getTagBySlug(slug));
        return "/tags/index";
    }

    @GetMapping("/books/tag/{slug}")
    @ResponseBody
    public BooksPageDto nextTagPage(@PathVariable("slug") String slug,
                                    @RequestParam("offset") Integer offset,
                                    @RequestParam("limit") Integer limit) {
        return new BooksPageDto(tagService.getPageOfTagBook(slug,offset,limit).getContent());
    }
}
