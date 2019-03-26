package com.demo.digitallibrary.service;

import com.demo.digitallibrary.model.Book;
import com.demo.digitallibrary.model.Document;
import com.demo.digitallibrary.model.Music;
import com.demo.digitallibrary.model.Video;
import com.demo.digitallibrary.repository.BookRepository;
import com.demo.digitallibrary.repository.DocumentRepository;
import com.demo.digitallibrary.repository.MusicRepository;
import com.demo.digitallibrary.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final BookRepository bookRepository;
    private final MusicRepository musicRepository;
    private final VideoRepository videoRepository;
    private final DocumentRepository documentRepository;

    public LibraryServiceImpl(BookRepository bookRepository,
                              MusicRepository musicRepository, VideoRepository videoRepository, DocumentRepository documentRepository) {
        this.bookRepository = bookRepository;
        this.musicRepository = musicRepository;
        this.videoRepository = videoRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Music> findAllMusic() {
        return musicRepository.findAll();
    }

    @Override
    public List<Video> findAllVideos() {
        return videoRepository.findAll();
    }

    @Override
    public Music saveMusic(Music musicObject) {
        return musicRepository.save(musicObject);
    }

    @Override
    public Music updateMusic(Long id, Music musicToSave) throws Exception {
        if (musicRepository.findById(id).isPresent()) {
            Music musicToUpdate = musicRepository.findById(id).orElseThrow(() -> new Exception("not found"));
            musicToUpdate.setFavorite(musicToSave.getFavorite());
            musicToUpdate.setName(musicToSave.getName());
            musicToUpdate.setSinger(musicToSave.getSinger());
            musicToUpdate.setType(musicToSave.getType());
            return musicRepository.save(musicToUpdate);
        }
        return null;
    }

    @Override
    public void removeMusic(Long id) throws Exception {
        if (musicRepository.findById(id).isPresent()) {
            musicRepository.delete(musicRepository.findById(id).orElseThrow(() -> new Exception("music not found")));
        }
    }

    @Override
    public Book saveBook(Book bookObject) {
        return bookRepository.save(bookObject);
    }

    @Override
    public Book updateBook(Long id, Book bookObject) throws Exception {
        if (bookRepository.findById(id).isPresent()) {
            Book bookToUpdate = bookRepository.findById(id).orElseThrow(() -> new Exception("book not found"));
            bookToUpdate.setAuthor(bookObject.getAuthor());
            bookToUpdate.setName(bookObject.getName());
            bookToUpdate.setPrice(bookObject.getPrice());
            bookToUpdate.setIsbn(bookObject.getIsbn());
            return bookRepository.save(bookToUpdate);
        }
        return null;
    }

    @Override
    public void removeBook(Long id) throws Exception {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.delete(bookRepository.findById(id).orElseThrow(() -> new Exception("book not found")));
        }
    }

    /*TODO: change return statement*/
    @Override
    public Document saveFile(Long videoId, byte[] bytes, String originalFilename, String contentType) throws Exception {
        Document document = new Document(originalFilename, contentType, ZonedDateTime.now(), bytes);
        documentRepository.save(document);
        Video video = null;
        if (videoRepository.findById(videoId).isPresent()) {
            video = videoRepository.findById(videoId).orElseThrow(() -> new Exception("never thrown"));
            video.setDocument(document);
        } else {
            video = new Video(originalFilename);
            video.setDocument(document);
        }
        videoRepository.save(video);
        return document;
    }
}
