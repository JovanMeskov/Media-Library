import {Injectable} from '@angular/core';
import {Router} from "@angular/router";
import {Observable} from "rxjs/Observable";
import {Book} from "./book.model";
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from "@angular/common/http";
import {ErrorObservable} from "rxjs/observable/ErrorObservable";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {Music} from "./toolbar/music.model";
import {Video} from "./toolbar/video.model";
import {FileService, IUploadOptions} from "./file.service";

@Injectable()
export class LibraryService {

  constructor(private _router: Router,
              private http: HttpClient,
              private fileUploadService: FileService) {
  }


  findAllBooks(): Observable<Array<Book>> {
    return this.get("/api/library/books");
  }

  findAllMusic(): Observable<Array<Music>> {
    return this.get("/api/library/music");
  }

  findAllVideos(): Observable<Array<any>> {
    return this.get("/api/library/videos")
  }

  uploadVideo(videoId: number, document: File): Observable<any> {
    const options: IUploadOptions = {
      url: `/api/library/videos/${videoId}/save`,
      method: "post",
      file: document
    };
    console.log("success")
    return this.fileUploadService.upload(options);
  }

  saveMusic(obj: any): Observable<Music> {
    return this.post(`/api/library/music/save`, obj);
  }

  updateMusic(id: number, obj: any): Observable<Music> {
    return this.post(`/api/library/music/update/${id}`, obj);
  }

  removeMusic(id: number) {
    return this.get(`/api/library/music/remove/${id}`);
  }

  saveBook(obj: any): Observable<Book> {
    return this.post(`/api/library/book/save`, obj);
  }

  updateBook(id: number, obj: any): Observable<Book> {
    return this.post(`/api/library/book/update/${id}`, obj);
  }

  removeBook(id: number) {
    return this.get(`/api/library/book/remove/${id}`);
  }

  post<T = any>(path: string, params: any, options?: {
    headers?: HttpHeaders;
    observe?: 'body';
    params?: HttpParams;
    reportProgress?: boolean;
    responseType?: 'json';
    withCredentials?: boolean;
  }): Observable<T> {
    if (!options) {
      options = {};
    }

    if (options.withCredentials !== false) {
      options.withCredentials = true;
    }
    return this.http.post(path, params, options)
      .map(this.handleResponse)
      .catch(this.handleError);
  }

  get<T = any>(path: string, options: any = {}, filterObj?: any): Observable<T[]> {
    path = this.addQueryParams(path, filterObj);

    return this.http.get<T[]>(path, options)
      .catch(this.handleError);
  }

  private addQueryParams(path: string, filterObj?: any): string {
    if (!filterObj) {
      return path;
    }

    for (const key of Object.keys(filterObj)) {
      if (filterObj[key] === null) {
        filterObj[key] = undefined;
      }
    }
    const filterParams = encodeURIComponent(JSON.stringify(filterObj));
    // TODO check if the sign before the query param should be ? or &
    return path + '?filter=' + filterParams;
  }

  private handleError(error: HttpErrorResponse): ErrorObservable {
    try {
      return Observable.throw(error.error);
    } catch (ex) {
      return Observable.throw(error);
    }
  }

  private handleResponse(response: Response) {
    return response;
  }

}
