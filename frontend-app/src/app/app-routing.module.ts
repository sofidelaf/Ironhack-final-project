import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutusComponent } from './components/aboutus/aboutus.component';
import { BikesComponent } from './components/bikes/bikes.component';
import { ContactComponent } from './components/contact/contact.component';
import { PagenotfoundComponent } from './components/pagenotfound/pagenotfound.component';
import { WelcomepageComponent } from './components/welcomepage/welcomepage.component';

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
    path: '**',
    component: PagenotfoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
