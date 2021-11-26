import { Component, Input, OnInit } from '@angular/core';
import { Discount } from 'src/app/models/discount.model';

@Component({
  selector: 'app-discount-item',
  templateUrl: './discount-item.component.html',
  styleUrls: ['./discount-item.component.css']
})
export class DiscountItemComponent implements OnInit {

  @Input()
  discount!: Discount;

  constructor() { }

  ngOnInit(): void {
  }

}
