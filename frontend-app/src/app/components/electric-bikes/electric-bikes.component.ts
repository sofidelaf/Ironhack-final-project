import { Component, OnInit } from '@angular/core';
import { Article } from 'src/app/models/article.model';
import { ArticleService } from 'src/app/services/article.service';

@Component({
  selector: 'app-electric-bikes',
  templateUrl: './electric-bikes.component.html',
  styleUrls: ['./electric-bikes.component.css']
})
export class ElectricBikesComponent implements OnInit {
  
    bikeList: Article[];
  
    constructor(private articleService: ArticleService) { 
      this.bikeList = [];
    }
  
    ngOnInit(): void {
      this.getBikes();
    }
  
    getBikes(){
      this.articleService.getArticlesByCategory("electric bike").subscribe({
      next: dataResult => {
        this.bikeList = dataResult;
      }
      ,
      error: error => {
        console.error("Ther was an error!", error);
      }
    })
    }
  
  }
  
