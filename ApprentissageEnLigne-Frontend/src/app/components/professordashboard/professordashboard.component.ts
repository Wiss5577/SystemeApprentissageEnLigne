import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import { Observable } from 'rxjs';
import { AdminService } from 'src/app/services/admin.service';
import { Professor } from 'src/app/models/professor';
import { ProfessorService } from 'src/app/services/professor.service';


@Component({
  selector: 'app-professordashboard',
  templateUrl: './professordashboard.component.html',
  styleUrls: ['./professordashboard.component.css']
})
export class ProfessordashboardComponent implements OnInit {

  loggedUser = '';
  currRole = '';
  Name = '';
  totalcourses: number = 0;
  professor: Professor = new Professor();
  courses: Observable<any[]> | undefined;
  enrollments: Observable<any[]> | undefined;
  enrollmentcount: Observable<any[]> | undefined;
  wishlist: Observable<any[]> | undefined;
  chapters: Observable<any[]> | undefined;

  constructor(private _service: AdminService, private _professorService: ProfessorService) { }

  ngOnInit(): void {

    this.loggedUser = JSON.stringify(sessionStorage.getItem('loggedUser') || '{}');
    this.loggedUser = this.loggedUser.replace(/"/g, '');

    this.currRole = JSON.stringify(sessionStorage.getItem('ROLE') || '{}');
    this.currRole = this.currRole.replace(/"/g, '');

    this.Name = JSON.stringify(sessionStorage.getItem('name') || '{}');
    this.Name = this.Name.replace(/"/g, '');

    $("#btn").click(function () {
      $(".sidebar").toggleClass("open");
      menuBtnChange();
    });

    $(".bx-search").click(function () {
      $(".sidebar").toggleClass("open");
      menuBtnChange();
    });

    function menuBtnChange() {
      if ($(".sidebar").hasClass("open")) {
        $("#btn").removeClass("fa-bars").addClass("fa-ellipsis-v");
      } else {
        $("#btn").removeClass("fa-ellipsis-v").addClass("fa-bars");
      }
    }

    this.courses = this._service.getTotalCourses();
    this.enrollments = this._service.getTotalEnrollments();
    this.enrollmentcount = this._service.getTotalEnrollmentCount();
    this.wishlist = this._service.getTotalWishlist();
    this.chapters = this._service.getTotalChapters();
    this.getProfessor(this.loggedUser);
    this.getTotalInscToMyCourses(this.loggedUser);
  }


  getProfessor(loggedUser: string) {
    this._professorService.getProfileDetails(this.loggedUser).subscribe(data => {
      this.professor = data;
      console.log(this.professor);
    })
  }

  getTotalInscToMyCourses(loggedUser: String) {
    this._professorService.CountMycourses(this.loggedUser).subscribe(data => {
      this.totalcourses = data;
      console.log(this.totalcourses);
    })

  }
}
