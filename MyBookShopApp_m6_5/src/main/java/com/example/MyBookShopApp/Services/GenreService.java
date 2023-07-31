package com.example.MyBookShopApp.Services;

import com.example.MyBookShopApp.Dto.GenresDto;
import com.example.MyBookShopApp.Repositories.BookRepository;
import com.example.MyBookShopApp.Repositories.GenreRepository;
import com.example.MyBookShopApp.data.BookStructure.Book;
import com.example.MyBookShopApp.data.GenreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    public GenreService(GenreRepository genreRepository, BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    public GenreEntity getGenreBySlug(String slug) {
        return genreRepository.findBySlug(slug);
    }

    public Page<Book> getPageOfGenreBooks(String slug, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.Direction.DESC, "pubDate", "title");
        return bookRepository.findBooksByGenresSlug(slug, nextPage);
    }

    public List<GenresDto> getGenresData() {
        List<GenresDto> result = new ArrayList<>();
        List<GenreEntity> list = genreRepository.findAllByOrderByParentIdAscIdAsc();

        for (int i = list.size()-1; i >= 0; i--) {
            GenreEntity genreEntity = list.get(i);
            int parentId = genreEntity.getParentId();
            if (parentId != 0) {
                for (GenreEntity x : list) {
                    if (x.getId() == parentId) {
                        x.getBookToGenreList().addAll(genreEntity.getBookToGenreList());
                    }
                }
            }
        }

        for (GenreEntity x : list) {
            if (x.getParentId() == 0 ) result.add(new GenresDto(x));
        }

        for (GenreEntity x : list) {
            addRecursively(result, x);
        }

        return result;
    }

    private void addRecursively(List<GenresDto> result, GenreEntity genreEntity) {

        for (GenresDto x : result) {
            if (x.getId() == genreEntity.getParentId()) {
                x.getGenresChildList().add(new GenresDto(genreEntity));
                return;
            }
        }
        for (GenresDto x : result) {
            addRecursively(x.getGenresChildList(), genreEntity);
        }
    }

}
