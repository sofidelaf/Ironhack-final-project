import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutusComponent } from './components/aboutus/aboutus.component';
import { AdministrationComponent } from './components/administration/administration.component';
import { ArticlesComponent } from './components/articles/articles.component';
import { BikesComponent } from './components/bikes/bikes.component';
import { CategoriesComponent } from './components/categories/categories.component';
import { ContactComponent } from './components/contact/contact.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { PagenotfoundComponent } from './components/pagenotfound/pagenotfound.component';
import { WelcomepageComponent } from './components/welcomepage/welcomepage.component';
import { AuthGuard } from './utils/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: WelcomepageComponent
  },
  {
    path: 'bikes',
    component: BikesComponent
  },
  {
    path: 'contact',
    component: ContactComponent
  },
  {
    path: 'information',
    component: AboutusComponent
  },
  {
    path: 'login',
    component: LoginFormComponent
  },
  {
    path: 'administration',
    component: AdministrationComponent,
    canActivate: [AuthGuard],
    children: [
      {path: 'categories', component: CategoriesComponent},
      {path: 'articles', component: ArticlesComponent}
    ]},
    {path: '', redirectTo: '/', pathMatch: 'full'}
  ,
  {
    path: '**',
    component: PagenotfoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
