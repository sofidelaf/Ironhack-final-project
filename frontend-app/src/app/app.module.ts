import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { PagenotfoundComponent } from './components/pagenotfound/pagenotfound.component';
import { WelcomepageComponent } from './components/welcomepage/welcomepage.component';
import { ContactComponent } from './components/contact/contact.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { AboutusComponent } from './components/aboutus/aboutus.component';
import { BikesComponent } from './components/bikes/bikes.component';
import { HttpClientModule } from '@angular/common/http';
import { ArticleItemComponent } from './components/article-item/article-item.component';
import { NoveltyItemComponent } from './components/novelty-item/novelty-item.component';
import { DiscountItemComponent } from './components/discount-item/discount-item.component';
import { AuthenticationService } from './services/authentication.service';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { AuthGuard } from './utils/auth.guard';
import { AdministrationComponent } from './components/administration/administration.component';
import { CategoriesComponent } from './components/categories/categories.component';
import { ArticlesComponent } from './components/articles/articles.component';
import { NoveltiesManagementComponent } from './components/novelties-management/novelties-management.component';
import { DiscountsManagementComponent } from './components/discounts-management/discounts-management.component';
import { SearchComponent } from './components/search/search.component';
import { MountainBikesComponent } from './components/mountain-bikes/mountain-bikes.component';
import { ElectricBikesComponent } from './components/electric-bikes/electric-bikes.component';
import { UpdatePriceComponent } from './components/update-price/update-price.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    PagenotfoundComponent,
    WelcomepageComponent,
    ContactComponent,
    AboutusComponent,
    BikesComponent,
    ArticleItemComponent,
    NoveltyItemComponent,
    DiscountItemComponent,
    LoginFormComponent,
    AdministrationComponent,
    CategoriesComponent,
    ArticlesComponent,
    NoveltiesManagementComponent,
    DiscountsManagementComponent,
    SearchComponent,
    MountainBikesComponent,
    ElectricBikesComponent,
    UpdatePriceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatButtonModule
  ],
  providers: [
    AuthenticationService,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
