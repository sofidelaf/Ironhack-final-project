import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  public contactForm: FormGroup;
  public nameInput: FormControl;
  public emailInput: FormControl;
  public informationInput: FormControl;

  constructor() {
    
    this.nameInput = new FormControl('', [Validators.required]);
    this.emailInput = new FormControl('', [Validators.required, Validators.email]);
    this.informationInput = new FormControl('', [Validators.required]);

    this.contactForm = new FormGroup({
      name: this.nameInput,
      email: this.emailInput,
      information: this.informationInput
    });
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    console.log(this.contactForm.value);
  }

}
