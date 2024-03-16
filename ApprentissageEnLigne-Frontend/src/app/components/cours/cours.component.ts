import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from 'src/app/models/course';
import { ProfessorService } from 'src/app/services/professor.service';
import * as $ from 'jquery';
import { Professor } from 'src/app/models/professor';
import { Chapter } from 'src/app/models/chapter';

@Component({
  selector: 'app-cours',
  templateUrl: './cours.component.html',
  styleUrls: ['./cours.component.css']
})
export class CoursComponent implements OnInit {

  chapter = new Chapter();
  course = new Course();
  id: number = 0; //1
  msg = ' ';
  loggedUser = '';
  currRole = '';
  professor: Professor = new Professor();
  dateToday: string = ""; // Vous pouvez définir le type en fonction de vos besoins

  constructor(private _professorService: ProfessorService, private _router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.loggedUser = JSON.stringify(sessionStorage.getItem('loggedUser') || '{}');
    this.loggedUser = this.loggedUser.replace(/"/g, '');
    this.currRole = JSON.stringify(sessionStorage.getItem('ROLE') || '{}');
    this.currRole = this.currRole.replace(/"/g, '');
    this.getCourse(this.id);
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

  }




  getCourse(id: number) {
    this._professorService.GetCourseById(id).subscribe(data => {
      this.course = data;
    })
  }
  getProfessor(loggedUser: string) {
    this._professorService.getProfileDetails(this.loggedUser).subscribe(data => {
      this.professor = data;
      console.log(this.professor);
    })
  }

  Ajouterchapitres(coursname: string) {
    console.log(coursname);
    this._router.navigate(['/addchapter', coursname]);
  }
}
