import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/category.model';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class CategoriesManagementService {

  readonly baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient, private authenticationService: AuthenticationService) { }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.baseUrl + '/categories', {headers: this.authenticationService.createJwtHeader()});
  }
}
