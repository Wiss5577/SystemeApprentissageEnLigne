import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/models/course';
import { ProfessorService } from 'src/app/services/professor.service';
import { Router } from '@angular/router';
import { Enrollment } from 'src/app/models/enrollment';
import { UserService } from 'src/app/services/user.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-myinsc',
  templateUrl: './myinsc.component.html',
  styleUrls: ['./myinsc.component.css']
})
export class MyinscComponent implements OnInit {
  loggedUser: string = '';
  currRole: string = '';
  enrollments: Observable<any[]> | undefined;

  constructor(private userservice: UserService, private _router: Router) { }

  ngOnInit(): void {
    this.loggedUser = JSON.stringify(sessionStorage.getItem('loggedUser') || '{}');
    this.loggedUser = this.loggedUser.replace(/"/g, '');
    this.currRole = JSON.stringify(sessionStorage.getItem('ROLE') || '{}');
    this.currRole = this.currRole.replace(/"/g, '');
    console.log(this.loggedUser);
    this.enrollments = this.userservice.getAllEnrollments();
  }





}
