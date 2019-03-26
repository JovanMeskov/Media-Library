import {Component, OnDestroy, OnInit} from '@angular/core';
import {LibraryService} from "./core/library.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {

  constructor(private service: LibraryService) {
  }

  ngOnInit() {
  }

  ngOnDestroy() {
  }
}
