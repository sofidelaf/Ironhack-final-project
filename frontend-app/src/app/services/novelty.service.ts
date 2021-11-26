import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Novelty } from '../models/novelty.model';

@Injectable({
  providedIn: 'root'
})
export class NoveltyService {

  readonly baseUrl = 'http://localhost:8080';

  constructor(
    private http: HttpClient
  ) { }

  getNovelties(): Observable<Novelty[]> {
    return this.http.get<Novelty[]>(this.baseUrl + '/novelties');
  }
}
