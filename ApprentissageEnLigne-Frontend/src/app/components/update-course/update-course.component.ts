import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/models/course';
import { ProfessorService } from 'src/app/services/professor.service';
import * as $ from 'jquery';
import { ActivatedRoute, Router } from '@angular/router';
import { Professor } from 'src/app/models/professor';

@Component({
  selector: 'app-update-course',
  templateUrl: './update-course.component.html',
  styleUrls: ['./update-course.component.css']
})
export class UpdateCourseComponent implements OnInit {
  course = new Course();
  loggedUser = '';
  currRole = '';
  professor: Professor = new Professor();
  dateToday: string = "";
  id: number = 0;

  constructor(private _professorService: ProfessorService, private _router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.loggedUser = JSON.stringify(sessionStorage.getItem('loggedUser') || '{}');
    this.loggedUser = this.loggedUser.replace(/"/g, '');
    this.currRole = JSON.stringify(sessionStorage.getItem('ROLE') || '{}');
    this.currRole = this.currRole.replace(/"/g, '');
    this.getProfessor(this.loggedUser);
    this.getCourse(this.id);
    $("#websitelink, #youtubelink").css("display", "none");
    $("#websitelink").hide();

    $("select").on('change', function () {
      $(this).find("option:selected").each(function () {
        var option = $(this).attr("value");
        if (option === "Website") {
          $("#websitelink").css("display", "block");
          $("#youtubelink").css("display", "none");
        }
        else if (option === "Youtube") {
          $("#youtubelink").css("display", "block");
          $("#websitelink").css("display", "none");
        }
      });
    }).change();
  }

  getProfessor(loggedUser: string) {
    this._professorService.getProfileDetails(this.loggedUser).subscribe(data => {
      this.professor = data;
      console.log(this.professor);
    })
  }

  getCourse(id: number) {
    this._professorService.GetCourseById(id).subscribe(data => {
      this.course = data;
    })
  }

  UpdateCourse() {
    console.log(this.course);
    this._professorService.UpdateCourse(this.course).subscribe(
      data => {
        console.log("Course  Updated succesfully");
        this._router.navigate(['/addedcourses']);
      },
      error => {
        console.log(error.error);
      }
    )
  }



}
