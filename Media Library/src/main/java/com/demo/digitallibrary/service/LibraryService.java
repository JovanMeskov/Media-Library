package com.demo.digitallibrary.service;

import com.demo.digitallibrary.model.Book;
import com.demo.digitallibrary.model.Document;
import com.demo.digitallibrary.model.Music;
import com.demo.digitallibrary.model.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LibraryService {

    List<Book> findAllBooks();

    List<Music> findAllMusic();

    List<Video> findAllVideos();

    Music saveMusic(Music musicObject);

    Music updateMusic(Long id, Music musicToUpdate) throws Exception;

    void removeMusic(Long id) throws Exception;

    Book saveBook(Book bookObject);

    Book updateBook(Long id, Book bookObject) throws Exception;

    void removeBook(Long id) throws Exception;

    Document saveFile(Long videoId, byte[] bytes, String originalFilename, String contentType) throws Exception;
}
