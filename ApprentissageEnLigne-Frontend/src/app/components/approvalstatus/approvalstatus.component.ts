import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Professor } from 'src/app/models/professor';
import { ProfessorService } from 'src/app/services/professor.service';

@Component({
  selector: 'app-approvalstatus',
  templateUrl: './approvalstatus.component.html',
  styleUrls: ['./approvalstatus.component.css']
})
export class ApprovalstatusComponent implements OnInit {
  showButton: boolean = true;
  showButton2: boolean = false;
  currRole = '';
  loggedUser = '';
  approval: Observable<Professor[]> | undefined;
  professorlist: Observable<Professor[]> | undefined;
  responses: Observable<any> | undefined;
  professor: Professor = new Professor()

  constructor(private _service: ProfessorService, private _router: Router) { }

  ngOnInit(): void {
    this.loggedUser = JSON.stringify(sessionStorage.getItem('loggedUser') || '{}');
    this.loggedUser = this.loggedUser.replace(/"/g, '');

    this.currRole = JSON.stringify(sessionStorage.getItem('ROLE') || '{}');
    this.currRole = this.currRole.replace(/"/g, '');

    this.professorlist = this._service.getProfessorList();
    this.approval = this._service.getProfessorListByEmail(this.loggedUser);

    if (this.currRole === 'professor' || this.currRole === 'PROFESSOR') {
      $("#adminapproval").hide();
      $("#professorapproval").show();
    }
    else if (this.currRole === 'admin' || this.currRole === 'ADMIN' && this.loggedUser === 'admin@gmail.com') {
      $("#adminapproval").show();
      $("#professorapproval").hide();
    }
  }

  acceptRequest(curremail: string) {
    this.responses = this._service.acceptRequestForProfessorApproval(curremail);
    $("#acceptbtn").hide();
    $("#rejectbtn").hide();
    $("#acceptedbtn").show();
    $("#rejectedbtn").hide();
  }

  rejectRequest(curremail: string) {
    this.responses = this._service.rejectRequestForProfessorApproval(curremail);
    $("#acceptbtn").hide();
    $("#rejectbtn").hide();
    $("#acceptedbtn").hide();
    $("#rejectedbtn").show();
  }


  Essayer(curremail: string) {
    this.showButton = false;
    this.showButton2 = true;
    this._service.RequestForProfessorApproval(curremail)
      .subscribe((response) => {
        console.log('Response from server:', response);
        this._router.navigate(['/approveprofessor']);
      }, (error) => {
        console.error('Error occurred:', error);
      });
  }

}
