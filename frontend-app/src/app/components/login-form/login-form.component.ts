import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  loading: boolean = false;
  error: string = "";
  username: string = "";
  password: string = "";

  constructor(private router: Router, private authenticationService: AuthenticationService) { }

  loginUser(e: Event) {

    // this.authenticationService.login(this.username, this.password).subscribe({
    //   next: dataResult => {
    //     console.log("login succesfull", dataResult);
    //   }
    //   ,
    //   error: error => {
    //     console.error("There was an error!", error);
    //   }
    // })
    e.preventDefault();
    //var username = e.target.elements[0].value;
    //var password = e.target.elements[1].value;
    this.loading = true;
    this.authenticationService.login(this.username, this.password).then(
      result => {
        if (result === true) {
          this.router.navigate(['administration']);
        } else {
          this.error = "User name or password invalid";
          this.loading = false;
        }
      }
    ).catch(e => console.error("There was an error!", e));

    return false;
  }

  ngOnInit(): void {
  }

}
