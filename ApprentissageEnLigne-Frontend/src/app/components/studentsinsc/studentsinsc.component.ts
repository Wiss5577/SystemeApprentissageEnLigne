import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-studentsinsc',
  templateUrl: './studentsinsc.component.html',
  styleUrls: ['./studentsinsc.component.css']
})
export class StudentsinscComponent implements OnInit {

  users: Observable<User[]> | undefined;

  constructor(private _serive: UserService) { }

  ngOnInit(): void {
    this.users = this._serive.getAllUsers();
  }

}
