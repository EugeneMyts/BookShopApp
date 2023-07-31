package com.example.MyBookShopApp.Repositories;

import com.example.MyBookShopApp.data.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {

    GenreEntity findBySlug(String slug);

    List<GenreEntity> findAllByOrderByParentIdAscIdAsc();

}