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

  addCategory(category: Category): Promise<Category> {

    const body = {
      type: category.type,
      description: category.description
    }
    return this.http.post<Category>(
      this.baseUrl + '/categories', body, {headers: this.authenticationService.createJwtHeader()}
      ).toPromise()
      .then(response => response as Category)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error("There was an error!", error);
    return Promise.reject(error.message || error);
  }
}
