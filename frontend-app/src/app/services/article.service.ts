import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Article } from '../models/article.model';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  readonly baseUrl = 'http://localhost:8080';

  constructor(
    private http: HttpClient
  ) { }

  getArticlesByCategory(category: string): Observable<Article[]> {
    return this.http.get<Article[]>(this.baseUrl + '/articles?category=' + category);
  }

  getArticlesByNameLike(name: string): Observable<Article[]> {
    return this.http.get<Article[]>(this.baseUrl + '/articles-by-name?name=' + name);
  }
}
