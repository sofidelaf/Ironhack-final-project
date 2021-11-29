import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { Discount } from '../models/discount.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DiscountsManagementService {

  readonly baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient, private authenticationService: AuthenticationService) { }

  getDiscounts(): Observable<Discount[]> {
    return this.http.get<Discount[]>(this.baseUrl + '/discounts');
  }

  addDiscount(number: number, promotion: string, quantity: number): Promise<Discount> {

    const body = {
      id: number,
      promotion: promotion,
      quantity: quantity
    }
    return this.http.post<Discount>(
      this.baseUrl + '/discounts', body, {headers: this.authenticationService.createJwtHeader()}
      ).toPromise()
      .then(response => response as Discount)
      .catch(this.handleError);
  }

  deleteDiscount(number: number): Promise<any> {

    return this.http.delete<any>(
      this.baseUrl + '/discounts' + '/' + number, {headers: this.authenticationService.createJwtHeader()}
      ).toPromise()
      .then(response => response as any)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error("There was an error!", error);
    return Promise.reject(error.message || error);
  }
}
