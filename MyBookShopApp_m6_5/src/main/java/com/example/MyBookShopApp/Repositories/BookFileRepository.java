package com.example.MyBookShopApp.Repositories;

import com.example.MyBookShopApp.data.BookStructure.BookFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookFileRepository extends JpaRepository<BookFile, Integer> {

    public BookFile findBookFileByHash(String hash);

}
