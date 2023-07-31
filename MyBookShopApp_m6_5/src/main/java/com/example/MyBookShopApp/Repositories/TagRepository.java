package com.example.MyBookShopApp.Repositories;

import com.example.MyBookShopApp.data.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {

    TagEntity findBySlug(String slug);

}