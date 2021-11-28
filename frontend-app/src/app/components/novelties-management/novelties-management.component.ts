import { Component, OnInit } from '@angular/core';
import { Article } from 'src/app/models/article.model';
import { NoveltyOutput } from 'src/app/models/novelty-output.model';
import { ArticlesManagementService } from 'src/app/services/articles-management.service';
import { NoveltiesManagementService } from 'src/app/services/novelties-management.service';

@Component({
  selector: 'app-novelties-management',
  templateUrl: './novelties-management.component.html',
  styleUrls: ['./novelties-management.component.css']
})
export class NoveltiesManagementComponent implements OnInit {

  noveltyList: NoveltyOutput[];
  articleList: Article[];
  articleName: string;
  isArticleNovelty: boolean;
  noveltyId: number;

  constructor(private noveltiesManagementService: NoveltiesManagementService,
    private articleManagementService: ArticlesManagementService) {
    this.noveltyList = [];
    this.articleList = [];
    this.articleName = "";
    this.isArticleNovelty = false;
    this.noveltyId = 0;
  }

  ngOnInit(): void {
    this.getArticles();
    this.getNovelties();
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

  getNovelties() {
    this.noveltiesManagementService.getNovelties().subscribe({
      next: dataResult => {
        this.noveltyList = dataResult;
      }
      ,
      error: error => {
        console.error("Ther was an error!", error);
      }
    })
  }

  addNovelty() {
    this.isArticleNovelty = false;

    let article!: Article;
    for (let i = 0; i < this.articleList.length; i++) {
      if (this.articleName == this.articleList[i].name) {
        article = this.articleList[i];
      }
    }

    for (let j = 0; j < this.noveltyList.length; j++) {
      if (this.articleName == this.noveltyList[j].name) {
        this.isArticleNovelty = true;
      }
    }

    if (!this.isArticleNovelty) {
      this.noveltiesManagementService.addNovelty(article.id).then(
        result => {
          this.getNovelties();
          this.articleName = "";
        }
      ).catch(e => console.error("There was an error!", e));
    }
  }

  deleteNovelty() {
    this.noveltiesManagementService.deleteNovelty(this.noveltyId).then(
      result => {
        this.getNovelties();
        this.noveltyId = 0;
      }
    ).catch(e => console.error("There was an error!", e));
  }

isButtonAddDisabled(): boolean {
  return (this.articleName == "");
}

isNovelty(): boolean {
  return this.isArticleNovelty;
}

isButtonDeleteDisabled(): boolean {
  return (this.noveltyId == 0);
}
}
