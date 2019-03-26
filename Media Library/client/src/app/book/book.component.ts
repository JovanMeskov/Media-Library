import {Component, OnDestroy, OnInit} from '@angular/core';
import {Book} from "../core/book.model";
import {LibraryService} from "../core/library.service";
import {LocalDataSource} from "ng2-smart-table";

@Component({
  selector: 'books',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.scss']
})
export class BookComponent implements OnInit, OnDestroy {

  books: Array<Book> = [];
  source: LocalDataSource;
  settings = {
    delete: {
      confirmDelete: true,
    },
    add: {
      confirmCreate: true,
    },
    edit: {
      confirmSave: true,
    },
    columns: {
      id: {
        title: 'ID'
      },
      name: {
        title: 'Full Name'
      },
      author: {
        title: 'Author'
      },
      isbn: {
        title: 'Isbn'
      },
      price: {
        title: 'Price'
      }
    }
  };

  constructor(private service: LibraryService) {
    this.source = new LocalDataSource(this.books);
  }

  ngOnInit() {
    this.service.findAllBooks().subscribe(result => {
      this.books = result;
    });
  }

  onDeleteConfirm(event) {
    console.log(event);
    console.log(event.data['id']);
    if (window.confirm('Are you sure you want to delete this book?')) {
      this.service.removeBook(event.data['id']).subscribe();
      window.confirm("Successfully deleted book");
      event.confirm.resolve(event.data);
    }
    else {
      event.confirm.reject();
    }
  }

  onSaveConfirm(event) {
    console.log(event.newData['id']);
    if (window.confirm('Are you sure you want to update book?')) {
      this.service.updateBook(event.newData['id'], event.newData).subscribe();
      event.confirm.resolve(event.newData);
    }
    else {
      event.confirm.reject();
    }
  }

  onCreateConfirm(event) {
    if (window.confirm('Are you sure you want to create book?')) {
      this.service.saveBook(event.newData).subscribe();
      event.confirm.resolve(event.newData);
    } else {
      event.confirm.reject();
    }
  }

  ngOnDestroy() {
  }
}
