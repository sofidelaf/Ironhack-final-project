import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ArticleCreate } from '../models/article-create.model';
import { Article } from '../models/article.model';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class ArticlesManagementService {

  readonly baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient, private authenticationService: AuthenticationService) { }

  getArticles(): Observable<Article[]> {
    return this.http.get<Article[]>(this.baseUrl + '/articles', {headers: this.authenticationService.createJwtHeader()});
  }

  addArticle(article: ArticleCreate): Promise<Article> {

    const body = {
      name: article.name,
      category: article.category,
      brand: article.brand,
      description: article.description,
      imageUrl: article.imageurl,
      price: article.price,
      size: article.size,
      units: article.units
    }
    return this.http.post<Article>(
      this.baseUrl + '/articles', body, {headers: this.authenticationService.createJwtHeader()}
      ).toPromise()
      .then(response => response as Article)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error("There was an error!", error);
    return Promise.reject(error.message || error);
  }
}
