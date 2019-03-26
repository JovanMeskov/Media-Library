package com.demo.digitallibrary.controller;

import com.demo.digitallibrary.model.Book;
import com.demo.digitallibrary.model.Document;
import com.demo.digitallibrary.model.Music;
import com.demo.digitallibrary.model.Video;
import com.demo.digitallibrary.service.LibraryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/library", produces = MediaType.APPLICATION_JSON_VALUE)
public class LibraryController {

    private final LibraryService service;

    public LibraryController(LibraryService service) {
        this.service = service;
    }

    @GetMapping("/books")
    public List<Book> findAllBooks() {
        return service.findAllBooks();
    }

    @GetMapping("/music")
    public List<Music> findAllMusic() {
        return service.findAllMusic();
    }

    @PostMapping("/music/save")
    public Music saveMusic(@RequestBody Music musicObject) {
        return service.saveMusic(musicObject);
    }

    @PostMapping("/music/update/{id}")
    public Music updateMusic(@PathVariable Long id, @RequestBody Music musicObject) throws Exception {
        return service.updateMusic(id, musicObject);
    }

    @GetMapping("/music/remove/{id}")
    public void removeMusic(@PathVariable Long id) throws Exception {
        service.removeMusic(id);
    }

    @PostMapping("/book/save")
    public Book saveBook(@RequestBody Book bookObject) {
        return service.saveBook(bookObject);
    }

    @PostMapping("/book/update/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookObject) throws Exception {
        return service.updateBook(id, bookObject);
    }

    @GetMapping("/book/remove/{id}")
    public void removeBook(@PathVariable Long id) throws Exception {
        service.removeBook(id);
    }

    @PostMapping("videos/{videoId}/save")
    public Document saveVideo(@PathVariable Long videoId, @RequestParam MultipartFile file) throws Exception {
        return service.saveFile(videoId, file.getBytes(), file.getOriginalFilename(), file.getContentType());
    }

    @GetMapping("/videos")
    public List<String> findAllVideos() throws IOException {
        List<Video> videos = service.findAllVideos();
        List<String> videosToShow = new ArrayList<>();
        for (int i = 0; i < videos.size(); i++) {
            File myFile = new File("video" + i + ".mp4");
            FileOutputStream fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(videos.get(i).getDocument().getBytes());
            fileOutputStream.close();
            videosToShow.add(myFile.getAbsolutePath());
        }
        return videosToShow;
    }
}
