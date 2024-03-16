import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import { Observable } from 'rxjs';
import { AdminService } from 'src/app/services/admin.service';
import { Professor } from 'src/app/models/professor';

@Component({
  selector: 'app-prof-side-bar',
  templateUrl: './prof-side-bar.component.html',
  styleUrls: ['./prof-side-bar.component.css']
})
export class ProfSideBarComponent implements OnInit {
  loggedUser = '';
  currRole = '';
  Name = '';
  constructor() { }

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
  }

}
