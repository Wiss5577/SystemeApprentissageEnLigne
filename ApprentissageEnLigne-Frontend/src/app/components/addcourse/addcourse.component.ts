import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/course';
import { ProfessorService } from 'src/app/services/professor.service';
import * as $ from 'jquery';
import { Professor } from 'src/app/models/professor';
import { Chapter } from 'src/app/models/chapter';

@Component({
  selector: 'app-addcourse',
  templateUrl: './addcourse.component.html',
  styleUrls: ['./addcourse.component.css']
})
export class AddcourseComponent implements OnInit {
  chapter = new Chapter();
  course = new Course();
  id: number = 0;
  msg = ' ';
  loggedUser = '';
  currRole = '';
  professor: Professor = new Professor();
  dateToday: string = ""; // Vous pouvez définir le type en fonction de vos besoins

  constructor(private _professorService: ProfessorService, private _router: Router) { }

  ngOnInit(): void {
    this.loggedUser = JSON.stringify(sessionStorage.getItem('loggedUser') || '{}');
    this.loggedUser = this.loggedUser.replace(/"/g, '');
    this.currRole = JSON.stringify(sessionStorage.getItem('ROLE') || '{}');
    this.currRole = this.currRole.replace(/"/g, '');

    this.getProfessor(this.loggedUser);

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

    this.setDateToday();

  }

  test() {
    console.log("hi");
  }

  addCourse() {
    this.course.email = this.loggedUser;
    this.course.enrolleddate = this.dateToday;
    this.course.instructorname = this.professor.professorname;
    this.course.instructorinstitution = this.professor.institutionname;
    this.AjouterCours();
  }



  AjouterCours() {
    this._professorService.addCourse(this.course).subscribe(
      data => {
        this.course = data;
        console.log("Course added Successfully !!!");
        this._router.navigate(['/cours', this.course.id]);
      },
      (error: { error: any; }) => {
        console.log("Process Failed");
        console.log(error.error);
        this.msg = "Course with " + this.course.coursename + " already exists !!!";
      }
    )
  }

  getProfessor(loggedUser: string) {
    this._professorService.getProfileDetails(this.loggedUser).subscribe(data => {
      this.professor = data;
      console.log(this.professor);
    })
  }

  setDateToday(): void {
    const today: Date = new Date();
    // Formater la date en format ISO (AAAA-MM-JJ) pour l'élément input date
    this.dateToday = today.toISOString().slice(0, 10);
  }


}



