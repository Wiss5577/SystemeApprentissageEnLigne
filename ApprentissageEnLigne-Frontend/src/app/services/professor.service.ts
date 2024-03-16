import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Chapter } from '../models/chapter';
import { Course } from '../models/course';
import { Professor } from '../models/professor';

const NAV_URL = environment.apiURL;

@Injectable({
  providedIn: 'root'
})
export class ProfessorService {
  constructor(private _http: HttpClient) { }

  acceptRequestForProfessorApproval(curremail: string): Observable<any> {
    return this._http.get<any>(`${NAV_URL}/acceptstatus/` + curremail);
  }

  rejectRequestForProfessorApproval(curremail: string): Observable<any> {
    return this._http.get<any>(`${NAV_URL}/rejectstatus/` + curremail);
  }

  RequestForProfessorApproval(curremail: string): Observable<any> {
    return this._http.get<any>(`${NAV_URL}/sendrequest/` + curremail);
  }

  getProfessorList(): Observable<any> {
    return this._http.get<any>(`${NAV_URL}/professorlist`);
  }

  getYoutubeCourseList(): Observable<any> {
    return this._http.get<any>(`${NAV_URL}/youtubecourselist`);
  }

  getWebsiteCourseList(): Observable<any> {
    return this._http.get<any>(`${NAV_URL}/websitecourselist`);
  }

  getCourseListByName(coursename: string): Observable<any> {
    return this._http.get<any>(`${NAV_URL}/courselistbyname/` + coursename);
  }

  addCourse(course: Course): Observable<Course> {
    return this._http.post<Course>(`${NAV_URL}/addCourse`, course);
  }

  getProfessorListByEmail(email: string): Observable<any> {
    return this._http.get<any>(`${NAV_URL}/professorlistbyemail/` + email);
  }

  addNewChapters(chapter: Chapter): Observable<any> {
    return this._http.post<any>(`${NAV_URL}/addnewchapter`, chapter);
  }

  getProfileDetails(loggedUser: string): Observable<Professor> {
    const url = `${NAV_URL}/professorprofileDetails/`;
    return this._http.get<Professor>(url + loggedUser);
  }

  UpdateUserProfile(professor: Professor): Observable<Professor> {
    return this._http.put<Professor>(`${NAV_URL}/updateprofessor`, professor);
  }

  getCourseListNames(): Observable<any> {
    return this._http.get(`${NAV_URL}/getcoursenames/`);
  }

  GetCoursesByEmail(loggedUser: string): Observable<Course[]> {
    const url = `${NAV_URL}/courselistbyinsemail/`;
    return this._http.get<Course[]>(url + loggedUser);
  }

  DeleteCourseById(id: number): Observable<any> {
    const url = `${NAV_URL}/deletecourse/`;
    return this._http.delete<Course[]>(url + id);
  }
  GetCourseById(id: number): Observable<any> {
    const url = `${NAV_URL}/getcoursebyid/`;
    return this._http.get<Course>(url + id);
  }
  UpdateCourse(course: Course): Observable<Course> {
    return this._http.put<Course>(`${NAV_URL}/UpdateCourse`, course);
  }

  AjouterChapitres(chapter: Chapter): Observable<Chapter> {
    return this._http.post<Chapter>(`${NAV_URL}/AjouterChapitres`, chapter);
  }

  GetCourseByNameAndEmail(email: String, coursename: String) {
    const url = `${NAV_URL}/GetCoursByEmailAndName/`;
    const url2 = `/`;
    return this._http.get<Course[]>(url + email + url2 + coursename);
  }
  CountMycourses(email: String) {
    const url = `${NAV_URL}/CountMycourses/`;
    return this._http.get<number>(url + email);

  }
}
