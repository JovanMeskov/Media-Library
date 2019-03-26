import {Component, OnDestroy, OnInit} from '@angular/core';
import {LibraryService} from "../core/library.service";
import {Music} from "../core/toolbar/music.model";
import {LocalDataSource} from "ng2-smart-table";

@Component({
  selector: 'music',
  templateUrl: './music.component.html'
})
export class MusicComponent implements OnInit, OnDestroy {

  music: Array<Music> = [];
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
        title: 'Name'
      },
      signer: {
        title: 'Signer'
      },
      type: {
        title: 'type'
      },
      favorite: {
        title: 'Is Favorite?'
      }
    }
  };

  constructor(private service: LibraryService) {
    this.source = new LocalDataSource(this.music);
  }

  ngOnInit() {
    this.service.findAllMusic().subscribe(result => {
      this.music = result;
    });
  }

  onDeleteConfirm(event) {
    console.log(event);
    console.log(event.data['id']);
    if (window.confirm('Are you sure you want to delete this music?')) {
      this.service.removeMusic(event.data['id']).subscribe();
      window.confirm("Successfully deleted music")
      event.confirm.resolve(event.newData);
    }
    else {
      event.confirm.reject();
    }
  }


  onSaveConfirm(event) {
    console.log("here");
    console.log(event.newData['id']);
    if (window.confirm('Are you sure you want to update music?')) {
      this.service.updateMusic(event.newData['id'], event.newData).subscribe();
      event.confirm.resolve(event.newData);
    }
    else {
      event.confirm.reject();
    }
  }

  onCreateConfirm(event) {
    if (window.confirm('Are you sure you want to create?')) {
      this.service.saveMusic(event.newData).subscribe();
      event.confirm.resolve(event.newData);
    } else {
      event.confirm.reject();
    }
  }

  ngOnDestroy() {
  }

}
