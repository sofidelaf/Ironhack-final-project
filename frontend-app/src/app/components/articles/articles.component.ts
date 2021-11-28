import { Component, OnInit } from '@angular/core';
import { ArticleCreate } from 'src/app/models/article-create.model';
import { Article } from 'src/app/models/article.model';
import { ArticlesManagementService } from 'src/app/services/articles-management.service';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent implements OnInit {

  articleList: Article[];
  name: string;
  category: string;
  brand: string;
  description: string;
  imageurl: string;
  price: number;
  size: string;
  units: number;

  constructor(private articleManagementService: ArticlesManagementService) { 
    this.articleList = [];
    this.name = "";
    this.category = "";
    this.brand = "";
    this.description = "";
    this.imageurl = "";
    this.price = 0;
    this.size = "";
    this.units = 0;
  }

  ngOnInit(): void {
    this.getArticles();
  }

  getArticles() {
    this.articleManagementService.getArticles().subscribe({
      next: dataResult => {
        this.articleList = dataResult;
      }
      ,
      error: error => {
        console.error("Ther was an error!", error);
      }
    })
  }

  addArticle() {
    const newArticle: ArticleCreate = new ArticleCreate(this.name, this.category,this.brand, this.description, this.imageurl,
      this.price, this.size, this.units);

    this.articleManagementService.addArticle(newArticle).then(
      result => {
        this.getArticles();
        this.name = "";
        this.category = "";
        this.brand = "";
        this.description = "";
        this.imageurl = "";
        this.price = 0;
        this.size = "";
        this.units = 0;
      }
    ).catch(e => console.error("There was an error!", e));
  }

  isButtonDisabled(): boolean {
    return ((this.name == "") || (this.category == "") || (this.brand == "") || (this.description == "") || (this.imageurl == "")
      || (this.price == 0) || (this.size == "") || (this.units == 0))
  }
}