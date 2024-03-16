import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Professor } from 'src/app/models/professor';
import { ProfessorService } from 'src/app/services/professor.service';

@Component({
  selector: 'app-professorprofile',
  templateUrl: './professorprofile.component.html',
  styleUrls: ['./professorprofile.component.css']
})
export class ProfessorprofileComponent implements OnInit {

  professor: Professor = new Professor();
  msg = ' ';
  currRole = '';
  loggedUser = '';
  temp = false;

  constructor(private _service: ProfessorService, private activatedRoute: ActivatedRoute, private _router: Router) { }

  ngOnInit(): void {
    this.loggedUser = JSON.stringify(sessionStorage.getItem('loggedUser') || '{}');
    this.loggedUser = this.loggedUser.replace(/"/g, '');

    this.currRole = JSON.stringify(sessionStorage.getItem('ROLE') || '{}');
    this.currRole = this.currRole.replace(/"/g, '');

    $("#profilecard").hide();
    $("#profileform").show();

    this.getProfileDetails(this.loggedUser);
  }

  editProfile() {
    $("#profilecard").hide();
    $("#profileform").show();
  }


  getProfileDetails(loggedUser: string) {
    this._service.getProfileDetails(this.loggedUser).subscribe(data => {
      this.professor = data;
    })
    console.log(this.professor);
  }

  UpdateProfessor() {
    console.log(this.professor);
    this._service.UpdateUserProfile(this.professor).subscribe(
      data => {
        console.log("Professor Profile Updated succesfully");
        this.msg = "Profile Updated Successfully !!!";
        $(".editbtn").hide();
        $("#message").show();
        this.temp = true;
        $("#profilecard").show();
        $("#profileform").hide();
        setTimeout(() => {
          this._router.navigate(['/approveprofessor']);
        }, 6000);
      },
      error => {
        console.log("Profile Updation Failed");
        console.log(error.error);
        this.msg = "Profile Updation Failed !!!";
      }
    )
  }

  updateProfessorProfile() {

  }

}