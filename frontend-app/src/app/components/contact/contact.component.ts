import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ContactService } from 'src/app/services/contact.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  public contactForm: FormGroup;
  public nameInput: FormControl;
  public emailInput: FormControl;
  public subjectInput: FormControl;
  public informationInput: FormControl;

  constructor(private contactService: ContactService) {
    
    this.nameInput = new FormControl('', [Validators.required]);
    this.emailInput = new FormControl('', [Validators.required, Validators.email]);
    this.subjectInput = new FormControl('', [Validators.required]);
    this.informationInput = new FormControl('', [Validators.required]);
  
    this.contactForm = new FormGroup({
      name: this.nameInput,
      email: this.emailInput,
      subject: this.subjectInput,
      information: this.informationInput
    });
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
  
    const name: string = this.nameInput.value;
    const email: string = this.emailInput.value;
    const subject: string = this.subjectInput.value;
    const information: string = this.informationInput.value;
    this.contactService.addContact(name, email, subject, information).then(
      result => {
        this.contactForm.reset();
      }
    ).catch(e => console.error("There was an error!", e));
  }

}
