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
  categoryName: string;
  description: string;

  constructor(private categoriesManagementService: CategoriesManagementService) {
    this.categoryList = [];
    this.categoryName = "";
    this.description = "";
  }

  ngOnInit(): void {
    this.getCategories();
  }

  getCategories() {
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

  addCategory() {
    const newCategory: Category = new Category(this.categoryName, this.description, new Date(), "administration", new Date("0001-01-01"), "");

    this.categoriesManagementService.addCategory(newCategory).then(
      result => {
        this.categoryList.push(newCategory);
        this.categoryName = "";
        this.description = "";
      }
    ).catch(e => console.error("There was an error!", e));
  }

  isButtonDisabled(): boolean {
    return ((this.categoryName == "") || (this.description == ""))
  }
}
