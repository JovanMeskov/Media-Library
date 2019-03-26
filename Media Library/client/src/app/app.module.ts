import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {SharedModule} from './shared/shared.module';
import {RouterModule} from "@angular/router";
import {LibraryService} from "./core/library.service";
import {CdkTableModule} from "@angular/cdk/table";
import {HttpClientModule} from "@angular/common/http";
import {Ng2SmartTableModule} from "ng2-smart-table";
import {BookComponent} from "./book/book.component";
import {HomeComponent} from "./home/home.component";
import {MusicComponent} from "./music/music.component";
import {VideoComponent} from "./video/video.component";
import {BrowserModule} from '@angular/platform-browser';
import {VgCoreModule} from "videogular2/core";
import {VgControlsModule} from "videogular2/controls";
import {VgOverlayPlayModule} from "videogular2/overlay-play";
import {VgBufferingModule} from "videogular2/buffering";
import {FileService} from "./core/file.service";

@NgModule({
  declarations: [
    AppComponent,
    BookComponent,
    HomeComponent,
    MusicComponent,
    VideoComponent
  ],
  imports: [
    SharedModule,
    CdkTableModule,
    RouterModule.forRoot([
      {
        path: '',
        component: AppComponent
      },
      {
        path: 'books',
        component: BookComponent
      },
      {
        path: 'home',
        component: HomeComponent
      },
      {
        path: 'music',
        component: MusicComponent
      },
      {
        path: 'videos',
        component: VideoComponent
      }
    ]),
    HttpClientModule,
    Ng2SmartTableModule,
    BrowserModule,
    VgCoreModule,
    VgControlsModule,
    VgOverlayPlayModule,
    VgBufferingModule
  ],
  providers: [LibraryService, FileService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
