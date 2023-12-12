import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SaveComponent } from './components/save/save.component';
import { ContactListComponent } from './components/contact-list/contact-list.component';

const routes: Routes = [
  {path: '', component: HomeComponent, title: "home"},
  {path: 'save', component: SaveComponent, title: "Save"},
  {path:'contact-list', component: ContactListComponent, title: "lista-contactos"},
  {path: '**', pathMatch: 'full', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
