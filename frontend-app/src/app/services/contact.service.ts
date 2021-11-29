import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  readonly baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  addContact(fullName: string, email: string, subject: string, details: string): Promise<any> {

    const body = {
      fullName: fullName,
      email: email,
      subject: subject,
      details: details
    }
    return this.http.post<any>(
      this.baseUrl + '/contact', body
      ).toPromise()
      .then(response => response as any)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error("There was an error!", error);
    return Promise.reject(error.message || error);
  }
}
