import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { AuthenticationService } from "../services/authentication.service";


@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private authenticationService: AuthenticationService, private router: Router) {}

    canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

        if (!this.authenticationService.getUserLoggedIn()) {
            this.router.navigate(['/login']);
        }
        return this.authenticationService.getUserLoggedIn();
    }
    
}