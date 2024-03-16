import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/models/course';
import { ProfessorService } from 'src/app/services/professor.service';
import { Router } from '@angular/router';
import { Professor } from 'src/app/models/professor';

@Component({
  selector: 'app-addedcourses',
  templateUrl: './addedcourses.component.html',
  styleUrls: ['./addedcourses.component.css']
})
export class AddedcoursesComponent implements OnInit {
  courses: Course[] = [];
  loggedUser: string = '';
  currRole: string = '';
  coursename: string = '';
  professor: Professor = new Professor();

  constructor(private profservice: ProfessorService, private _router: Router) { }

  ngOnInit(): void {
    this.loggedUser = JSON.stringify(sessionStorage.getItem('loggedUser') || '{}');
    this.loggedUser = this.loggedUser.replace(/"/g, '');
    this.currRole = JSON.stringify(sessionStorage.getItem('ROLE') || '{}');
    this.currRole = this.currRole.replace(/"/g, '');
    console.log(this.loggedUser);
    this.GetCourses();
    this.getProfessor(this.loggedUser);

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

  RechercherByName() {
    this.profservice.GetCourseByNameAndEmail(this.loggedUser, this.coursename).subscribe(
      data => {
        this.courses = data;
        console.log(this.courses);
      });
  }

  getProfessor(loggedUser: string) {
    this.profservice.getProfileDetails(this.loggedUser).subscribe(data => {
      this.professor = data;
      console.log(this.professor);
    })
  }
}
