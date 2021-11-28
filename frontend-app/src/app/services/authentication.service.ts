import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Token } from '../models/token.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  public token: string;
  private isUserLoggedIn: boolean;
  private headers = new HttpHeaders().set('Content-Type', 'application/json');
  private loginUrl = 'http://localhost:8080/login';

  constructor(private http: HttpClient) {

    this.isUserLoggedIn = false;
    var currentUser;
    try {
      currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
    } catch (e) {
      console.error('Invalid value');
    }
    this.token = currentUser && currentUser.token;
    if (currentUser) {
      this.isUserLoggedIn = true;
    }
  }

  login(username: string, password: string): Promise<boolean> {

    const body = {
      username: username,
      password: password
    }
    return this.http.post<any>(this.loginUrl, body, { headers: this.headers })
              .toPromise().then(
                response => {
                  let token = response && response.token;
                  if (token) {
                    this.token = token;
                    this.isUserLoggedIn = true;
                    localStorage.setItem('currentUser', JSON.stringify({ username: username, token: token}));
                    return true;
                  } else {
                    return false;
                  }
        }
      ) .catch(err => {return false;});
  }

  // login(username: string, password: string): Observable<Token> {

  //   const body = {
  //     username: username,
  //     password: password
  //   }

  //   return this.http.post<Token>(this.loginUrl, body);
  // }

  logout(): void {
    
    this.token = "";
    localStorage.removeItem('currentUser');
    this.isUserLoggedIn = false;
  }

  getUserLoggedIn() {
    return this.isUserLoggedIn;
  }

  createJwtHeader(): HttpHeaders {
    let headers = new HttpHeaders({
      'Authorization': this.token,
      'Content-Type': 'application/json'
    });
    return headers;
  }
}
