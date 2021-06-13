//Moduli
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

//Componenti

import { ErrorComponent } from '../basic/error/error.component';

import { LoginComponent } from '../authentication/login/login.component';
import { SignupComponent } from '../authentication/signup/signup.component';
import { LogoutComponent } from '../authentication/logout/logout.component';
import { SignupRedirectComponent } from '../authentication//signup-redirect/signup-redirect.component';

import { HomeComponent } from '../guest/home/home.component';
import { ListProjectsComponent } from '../guest/list-projects/list-projects.component';
import { ListDesignersComponent } from '../guest/list-designers/list-designers.component';
import { ListProponentsComponent } from '../guest/list-proponents/list-proponents.component';
import { ListExpertsComponent } from '../guest/list-experts/list-experts.component';
import { ListEntitiesComponent } from '../guest/list-entities/list-entities.component';
import { ProjectComponent } from '../guest/view-project/project.component';

import { ProfileComponent } from '../logged/profile/profile.component';
import { CreateProjectComponent } from '../logged/create-project/create-project.component';
import { EntityComponent } from '../logged/entity/entity.component';
import { ExpertComponent } from '../logged/expert/expert.component';
import { ProponentComponent } from '../logged/proponent/proponent.component';
import { ProgettistaComponent } from '../logged/progettista/progettista.component';
import { CompetenzeInfoComponent } from '../logged/competenze-info/competenze-info.component';
import { ModificaProfiloComponent } from '../logged/modifica-profilo/modifica-profilo.component';
import { RichiestaRedirectComponent } from '../logged/richiesta-redirect/richiesta-redirect.component';
import { CandidaturaRedirectComponent } from '../logged/candidatura-redirect/candidatura-redirect.component';
import { NotificationListComponent } from '../logged/notification-list/notification-list.component';
import { ProjectManagementComponent } from '../logged/project-management/project-management.component';
import { ModificaEffettuataComponent } from '../logged/modifica-profilo-redirect/modifica-effettuata.component';


const routes: Routes = [
  

  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},

  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'signup-redirect', component : SignupRedirectComponent},
  {path: 'logout', component : LogoutComponent},

  {path: 'list-projects', component: ListProjectsComponent},
  {path: 'list-designers', component: ListDesignersComponent},
  {path: 'list-entities', component: ListEntitiesComponent},
  {path: 'list-proponents', component: ListProponentsComponent},
  {path: 'list-experts', component: ListExpertsComponent},
  {path: 'project/:id', component:ProjectComponent},
  {path: 'entity/:id', component:EntityComponent},
  {path: 'proponent/:id', component:ProponentComponent},
  {path: 'expert/:id', component:ExpertComponent},
  {path: 'progettista/:id', component:ProgettistaComponent},

  {path: 'profile', component: ProfileComponent},
  {path: 'modifica-profilo', component:ModificaProfiloComponent},
  {path: 'modifica-redirect', component:ModificaEffettuataComponent},
  {path: 'competenze-info', component:CompetenzeInfoComponent},
  {path: 'richiesta-ok',component:RichiestaRedirectComponent},
  {path: 'candidatura-ok', component:CandidaturaRedirectComponent},
  {path: 'create-project', component: CreateProjectComponent},
  {path: 'notification', component : NotificationListComponent},
  {path: 'project-management/:id', component : ProjectManagementComponent},
  
  //{path: 'home', component : HomeComponent, canActivate:[RouteGuardService]}, //Da rivedere
  {path: '**', component: ErrorComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
