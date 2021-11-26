import { Component, Input, OnInit } from '@angular/core';
import { Novelty } from 'src/app/models/novelty.model';

@Component({
  selector: 'app-novelty-item',
  templateUrl: './novelty-item.component.html',
  styleUrls: ['./novelty-item.component.css']
})
export class NoveltyItemComponent implements OnInit {

  @Input()
  novelty!: Novelty;

  constructor() { }

  ngOnInit(): void {
  }

}
