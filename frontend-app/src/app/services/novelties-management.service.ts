import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NoveltyOutput } from '../models/novelty-output.model';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class NoveltiesManagementService {

  readonly baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient, private authenticationService: AuthenticationService) { }

  getNovelties(): Observable<NoveltyOutput[]> {
    return this.http.get<NoveltyOutput[]>(this.baseUrl + '/novelties');
  }

  addNovelty(number: number): Promise<NoveltyOutput> {

    const body = {
      id: number
    }
    return this.http.post<NoveltyOutput>(
      this.baseUrl + '/novelties', body, {headers: this.authenticationService.createJwtHeader()}
      ).toPromise()
      .then(response => response as NoveltyOutput)
      .catch(this.handleError);
  }

  deleteNovelty(number: number): Promise<any> {

    return this.http.delete<any>(
      this.baseUrl + '/novelties' + '/' + number, {headers: this.authenticationService.createJwtHeader()}
      ).toPromise()
      .then(response => response as any)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error("There was an error!", error);
    return Promise.reject(error.message || error);
  }
}
