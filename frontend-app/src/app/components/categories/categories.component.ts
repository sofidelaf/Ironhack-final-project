import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models/category.model';
import { CategoriesManagementService } from 'src/app/services/categories-management.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  categoryList: Category[];

  constructor(private categoriesManagementService: CategoriesManagementService) {
    this.categoryList = [];
   }

  ngOnInit(): void {
    this.getCategories();
  }

  getCategories(){
    this.categoriesManagementService.getCategories().subscribe({
    next: dataResult => {
      this.categoryList = dataResult;
    }
    ,
    error: error => {
      console.error("Ther was an error!", error);
    }
  })
  }
}
