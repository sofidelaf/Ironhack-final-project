import { Component, OnInit } from '@angular/core';
import { ArticleCreate } from 'src/app/models/article-create.model';
import { Article } from 'src/app/models/article.model';
import { ArticlesManagementService } from 'src/app/services/articles-management.service';
import { CategoriesManagementService } from 'src/app/services/categories-management.service';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent implements OnInit {

  articleList: Article[];
  name: string;
  categoryList: string[];
  category: string;
  brand: string;
  description: string;
  imageurl: string;
  price: number;
  size: string;
  units: number;
  articleId: number;

  constructor(private articleManagementService: ArticlesManagementService,
    private categoriesManagementService: CategoriesManagementService) {
    this.articleList = [];
    this.name = "";
    this.categoryList = [];
    this.category = "";
    this.brand = "";
    this.description = "";
    this.imageurl = "";
    this.price = 0;
    this.size = "";
    this.units = 0;
    this.articleId = 0;
  }

  ngOnInit(): void {
    this.getCategories();
    this.getArticles();
  }

  getCategories() {
    this.categoriesManagementService.getCategories().subscribe({
      next: dataResult => {
        for (let i = 0; i < dataResult.length; i++) {
          this.categoryList.push(dataResult[i].type);
        }
        if (this.categoryList.length > 0) {
          this.category = this.categoryList[0];
        }
      }
      ,
      error: error => {
        console.error("There was an error!", error);
      }
    })
  }

  getArticles() {
    this.articleManagementService.getArticles().subscribe({
      next: dataResult => {
        this.articleList = dataResult;
      }
      ,
      error: error => {
        console.error("There was an error!", error);
      }
    })
  }

  addArticle() {
    const image:string = '../../../assets/img/' + this.imageurl;
    const newArticle: ArticleCreate = new ArticleCreate(this.name, this.category, this.brand, this.description, image,
      this.price, this.size, this.units);

    this.articleManagementService.addArticle(newArticle).then(
      result => {
        this.getArticles();
        this.name = "";
        this.brand = "";
        this.description = "";
        this.imageurl = "";
        this.price = 0;
        this.size = "";
        this.units = 0;
        this.articleId = 0;
      }
    ).catch(e => console.error("There was an error!", e));
  }

  isButtonDisabled(): boolean {
    return ((this.name == "") || (this.brand == "") || (this.description == "") || (this.imageurl == "")
      || (this.price == 0) || (this.size == "") || (this.units == 0))
  }

  deleteArticle() {
    this.articleManagementService.deleteArticle(this.articleId).then(
      result => {
        this.getArticles();
        this.articleId = 0;
      }
    ).catch(e => console.error("There was an error!", e));
  }

  isButtonDeleteDisabled(): boolean {
    return (this.articleId == 0);
  }
}