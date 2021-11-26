import { Component, OnInit } from '@angular/core';
import { Article } from 'src/app/models/article.model';
import { ArticleService } from 'src/app/services/article.service';

@Component({
  selector: 'app-bikes',
  templateUrl: './bikes.component.html',
  styleUrls: ['./bikes.component.css']
})
export class BikesComponent implements OnInit {

  bikeList: Article[];

  constructor(private articleService: ArticleService) { 
    this.bikeList = [];
  }

  ngOnInit(): void {
    this.getBikes();
  }

  getBikes(){
    this.articleService.getArticlesByCategory("road bike").subscribe({
    next: dataResult => {
      console.log(dataResult);
      this.bikeList = dataResult;
    }
    ,
    error: error => {
      console.error("Ther was an error!", error);
    }
  })
  }

}
