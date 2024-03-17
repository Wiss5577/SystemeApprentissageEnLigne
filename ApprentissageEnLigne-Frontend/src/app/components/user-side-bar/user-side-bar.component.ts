import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-side-bar',
  templateUrl: './user-side-bar.component.html',
  styleUrls: ['./user-side-bar.component.css']
})
export class UserSideBarComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
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
