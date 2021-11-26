import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Discount } from '../models/discount.model';

@Injectable({
  providedIn: 'root'
})
export class DiscountService {

  readonly baseUrl = 'http://localhost:8080';

  constructor(
    private http: HttpClient
  ) { }

  getDiscounts(): Observable<Discount[]> {
    return this.http.get<Discount[]>(this.baseUrl + '/discounts');
  }
}
