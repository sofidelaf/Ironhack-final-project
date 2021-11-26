import { Component, OnInit } from '@angular/core';
import { Discount } from 'src/app/models/discount.model';
import { Novelty } from 'src/app/models/novelty.model';
import { DiscountService } from 'src/app/services/discount.service';
import { NoveltyService } from 'src/app/services/novelty.service';

@Component({
  selector: 'app-welcomepage',
  templateUrl: './welcomepage.component.html',
  styleUrls: ['./welcomepage.component.css']
})
export class WelcomepageComponent implements OnInit {

  noveltyList: Novelty[];
  discountList: Discount[];

  constructor(private noveltyService: NoveltyService, private discountService: DiscountService) {
    this.noveltyList = [];
    this.discountList = [];
   }

  ngOnInit(): void {
    this.getNovelties();
    this.getDiscounts();
  }

  getNovelties(){
    this.noveltyService.getNovelties().subscribe({
    next: dataResult => {
      this.noveltyList = dataResult;
    }
    ,
    error: error => {
      console.error("Ther was an error!", error);
    }
  })
  }
  
  getDiscounts(){
    this.discountService.getDiscounts().subscribe({
    next: dataResult => {
      this.discountList = dataResult;
    }
    ,
    error: error => {
      console.error("Ther was an error!", error);
    }
  })
  }
}
