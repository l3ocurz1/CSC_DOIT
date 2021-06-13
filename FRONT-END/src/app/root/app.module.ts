//Moduli
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card'
import { MatIconModule } from '@angular/material/icon';
import { MatBadgeModule } from '@angular/material/badge';
import { MatTableModule } from '@angular/material/table';
import { AppRoutingModule } from './app-routing.module';

//Componenti base
import { AppComponent } from './app.component';
import { NavbarComponent } from '../basic/navbar/navbar.component';
import { JumbotronComponent } from '../basic/jumbotron/jumbotron.component';
import { FooterComponent } from '../basic/footer/footer.component';
import { ErrorComponent } from '../basic/error/error.component';

//Componenti autenticazione
import { LoginComponent } from '../authentication/login/login.component';
import { SignupComponent } from '../authentication/signup/signup.component';
import { LogoutComponent } from '../authentication/logout/logout.component';
import { SignupRedirectComponent } from '../authentication//signup-redirect/signup-redirect.component';

//Componenti per guest
import { HomeComponent } from '../guest/home/home.component';
import { ListProjectsComponent } from '../guest/list-projects/list-projects.component';
import { ListDesignersComponent } from '../guest/list-designers/list-designers.component';
import { ListProponentsComponent } from '../guest/list-proponents/list-proponents.component';
import { ListExpertsComponent } from '../guest/list-experts/list-experts.component';
import { ListEntitiesComponent } from '../guest/list-entities/list-entities.component';
import { ProjectComponent } from '../guest/view-project/project.component';

//Componenti per utenti loggati
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

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ListProjectsComponent,
    JumbotronComponent,
    NavbarComponent,
    FooterComponent,
    LoginComponent,
    ErrorComponent,
    SignupComponent,
    LogoutComponent,
    SignupRedirectComponent,
    ProfileComponent,
    ListDesignersComponent,
    ListProponentsComponent,
    ListExpertsComponent,
    ListEntitiesComponent,
    CreateProjectComponent,
    ProjectComponent,
    EntityComponent,
    ExpertComponent,
    ProponentComponent,
    ProgettistaComponent,
    CompetenzeInfoComponent,
    ModificaProfiloComponent,
    RichiestaRedirectComponent,
    CandidaturaRedirectComponent,
    NotificationListComponent,
    ProjectManagementComponent,
    ModificaEffettuataComponent
  ],
  imports: [
    NgMultiSelectDropDownModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgMultiSelectDropDownModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatIconModule,
    MatBadgeModule,
    MatTableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
