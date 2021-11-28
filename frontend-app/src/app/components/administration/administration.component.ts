import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-administration',
  templateUrl: './administration.component.html',
  styleUrls: ['./administration.component.css']
})
export class AdministrationComponent implements OnInit {

    title: string = 'Administration Menu';

  constructor(private authService: AuthenticationService, private router: Router) { }

  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }

  ngOnInit(): void {
  }

}
