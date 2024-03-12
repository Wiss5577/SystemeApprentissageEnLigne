import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/models/course';
import { ProfessorService } from 'src/app/services/professor.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-myinsc',
  templateUrl: './myinsc.component.html',
  styleUrls: ['./myinsc.component.css']
})
export class MyinscComponent implements OnInit {
  courses: Course[] = [];
  loggedUser: string = '';
  currRole: string = '';

  constructor(private profservice: ProfessorService, private _router: Router) { }

  ngOnInit(): void {
    this.loggedUser = JSON.stringify(sessionStorage.getItem('loggedUser') || '{}');
    this.loggedUser = this.loggedUser.replace(/"/g, '');
    this.currRole = JSON.stringify(sessionStorage.getItem('ROLE') || '{}');
    this.currRole = this.currRole.replace(/"/g, '');
    console.log(this.loggedUser);
    this.GetCourses();
  }


  private GetCourses() {
    this.profservice.GetCoursesByEmail(this.loggedUser).subscribe(
      data => {
        this.courses = data;
        console.log(this.courses);
      });
  }

  Modifier(id: number) {
    this._router.navigate(['/updatecourse', id]);

  }

  Supprimer(id: number) {
    this.profservice.DeleteCourseById(id).subscribe(
      data => {
        this.GetCourses();
      });

  }

}
