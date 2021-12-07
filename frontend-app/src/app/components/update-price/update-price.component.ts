import { Component, OnInit } from '@angular/core';
import { Article } from 'src/app/models/article.model';
import { ArticlesManagementService } from 'src/app/services/articles-management.service';

@Component({
  selector: 'app-update-price',
  templateUrl: './update-price.component.html',
  styleUrls: ['./update-price.component.css']
})
export class UpdatePriceComponent implements OnInit {

  articleList: Article[];
  articleId: number;
  price: number;


  constructor(private articleManagementService: ArticlesManagementService) {
    this.articleList = [];
    this.articleId = 0;
    this.price = 0;
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
        console.error("There was an error!", error);
      }
    })
  }

  updatePrice() {

    this.articleManagementService.updatePrice(this.articleId, this.price).then(
      result => {
        this.getArticles();
        this.articleId = 0;
        this.price = 0;
      }
    ).catch(e => console.error("There was an error!", e));
    // this.articleManagementService.updatePrice(this.articleId, this.price).subscribe({
    //   next: dataResult => {
    //     this.getArticles();
    //     this.articleId = 0;
    //     this.price = 0;
    //   }
    //   ,
    //   error: error => {
    //     console.error("There was an error!", error);
    //   }
    // })
  }

  isButtonDisabled(): boolean {
    return ((this.articleId == 0) || (this.price == 0));
  }

}
