import { Component, OnInit } from '@angular/core';
import { Article } from 'src/app/models/article.model';
import { Discount } from 'src/app/models/discount.model';
import { ArticlesManagementService } from 'src/app/services/articles-management.service';
import { DiscountsManagementService } from 'src/app/services/discounts-management.service';

@Component({
  selector: 'app-discounts-management',
  templateUrl: './discounts-management.component.html',
  styleUrls: ['./discounts-management.component.css']
})
export class DiscountsManagementComponent implements OnInit {

  discountList: Discount[];
  articleList: Article[];
  articleName: string;
  isArticleDiscount: boolean;
  discountId: number;
  promotion: string;
  quantity: number;

  constructor(private discountManagementService: DiscountsManagementService,
    private articleManagementService: ArticlesManagementService) {
    this.discountList = [];
    this.articleList = [];
    this.articleName = "";
    this.isArticleDiscount = false;
    this.discountId = 0;
    this.promotion = "";
    this.quantity = 0;
  }

  ngOnInit(): void {
    this.getArticles();
    this.getDiscounts();
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

  getDiscounts() {
    this.discountManagementService.getDiscounts().subscribe({
      next: dataResult => {
        this.discountList = dataResult;
      }
      ,
      error: error => {
        console.error("Ther was an error!", error);
      }
    })
  }

  addDiscount() {
    this.isArticleDiscount = false;

    let article!: Article;
    for (let i = 0; i < this.articleList.length; i++) {
      if (this.articleName == this.articleList[i].name) {
        article = this.articleList[i];
      }
    }

    for (let j = 0; j < this.discountList.length; j++) {
      if (this.articleName == this.discountList[j].name) {
        this.isArticleDiscount = true;
      }
    }

    if (!this.isArticleDiscount) {
      this.discountManagementService.addDiscount(article.id, this.promotion, this.quantity).then(
        result => {
          this.getDiscounts();
          this.articleName = "";
          this.promotion = "";
          this.quantity = 0;
        }
      ).catch(e => console.error("There was an error!", e));
    }
  }

  deleteDiscount() {
    this.discountManagementService.deleteDiscount(this.discountId).then(
      result => {
        this.getDiscounts();
        this.discountId = 0;
      }
    ).catch(e => console.error("There was an error!", e));
  }

  isButtonAddDisabled(): boolean {
    return (this.articleName == "");
  }

  isDiscount(): boolean {
    return this.isArticleDiscount;
  }

  isButtonDeleteDisabled(): boolean {
    return (this.discountId == 0);
  }

}
