import {Component, OnDestroy, OnInit} from '@angular/core';
import {LibraryService} from "../core/library.service";
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'videos',
  templateUrl: './video.component.html'
})
export class VideoComponent implements OnInit, OnDestroy {

  videos: Array<any> = [];

  constructor(private service: LibraryService,
              private sanitizer: DomSanitizer) {
  }

  ngOnInit() {
    this.service.findAllVideos().subscribe(result => {
      this.videos = result;
      console.log("here");
      console.log(this.videos)
    })
  }

  ngOnDestroy() {
  }

  uploadFile(event) {
    let files = event.target.files;
    if (files.length > 0) {
      console.log(files); // You will see the file
      console.log("here to see");
      console.log(files[0]);
      this.service.uploadVideo(1, files[0]).subscribe();
    }
  }

  sanitize(url:string){
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }
}
