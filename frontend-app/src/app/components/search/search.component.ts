import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Article } from 'src/app/models/article.model';
import { ArticleService } from 'src/app/services/article.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  public contactForm: FormGroup;
  public searchText: FormControl;
  public articleList: Article[];
  public isNoItem: boolean;

  constructor(private articleService: ArticleService) {
    this.articleList = [];
    this.searchText = new FormControl('', [Validators.required]);
    this.isNoItem = false;

    this.contactForm = new FormGroup({
      searchText: this.searchText,
    });
   }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.getArticles();
  }

  getArticles() {
    this.articleService.getArticlesByNameLike(this.searchText.value).subscribe({
      next: dataResult => {
        this.articleList = dataResult;
        if (this.articleList.length == 0) {
          this.isNoItem = true;
        } else {
          this.isNoItem = false;
        }
      }
      ,
      error: error => {
        console.error("There was an error!", error);
      }
    })
  }
}
