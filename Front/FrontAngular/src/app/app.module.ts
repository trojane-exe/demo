import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ProfileComponent } from './UserInterface/UserProfile/profile/profile.component';
import { UpdateInfoComponent } from './AdminInterface/USER_MANAGEMENT/update-info/update-info.component';
import { LoginComponent } from './login/login.component';
import{ FormsModule,NgForm} from '@angular/forms';
import { UsersComponent } from './AdminInterface/USER_MANAGEMENT/users/users.component';
import { AddUserComponent } from './AdminInterface/USER_MANAGEMENT/add-user/add-user.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { NavbarAdminComponent } from './navbar-admin/navbar-admin.component';
import { DocumentsComponent } from './AdminInterface/DOCUMENTS_MANAGEMENT/documents/documents.component';
import { AddDocumentComponent } from './AdminInterface/DOCUMENTS_MANAGEMENT/add-document/add-document.component';
import { BookDocumentComponent } from './AdminInterface/DOCUMENTS_MANAGEMENT/book-document/book-document.component';
import { UpdateDocumentComponent } from './AdminInterface/DOCUMENTS_MANAGEMENT/update-document/update-document.component';
import { ReservationsComponent } from './AdminInterface/RESERVATIONS_MANAGEMENT/reservations/reservations.component';
import { UpdateReservationComponent } from './AdminInterface/RESERVATIONS_MANAGEMENT/update-reservation/update-reservation.component';
import { AddReservationComponent } from './AdminInterface/RESERVATIONS_MANAGEMENT/add-reservation/add-reservation.component';
import { EmpruntsComponent } from './AdminInterface/EMPRUNT_MANAGEMENT/emprunts/emprunts.component';
import { ValidateEmpruntComponent } from './AdminInterface/RESERVATIONS_MANAGEMENT/validate-emprunt/validate-emprunt.component';
import { UpdateEmpruntComponent } from './AdminInterface/EMPRUNT_MANAGEMENT/update-emprunt/update-emprunt.component';
import { AuthInterceptor } from './auth.interceptor';
import { UpdateprofileComponent } from './UserInterface/UserProfile/UpdateProfile/updateprofile/updateprofile.component';
import { DocumentsUserComponent } from './UserInterface/Documents/documents-user/documents-user.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ProfileComponent,
    UpdateInfoComponent,
    LoginComponent,
    UsersComponent,
    AddUserComponent,
    NavbarAdminComponent,
    DocumentsComponent,
    AddDocumentComponent,
    BookDocumentComponent,
    UpdateDocumentComponent,
    ReservationsComponent,
    UpdateReservationComponent,
    AddReservationComponent,
    EmpruntsComponent,
    ValidateEmpruntComponent,
    UpdateEmpruntComponent,
    UpdateprofileComponent,
    DocumentsUserComponent,
  ],
  imports: [
    ReactiveFormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    AppRoutingModule,
    FormsModule,
    
    HttpClientModule
  ],
  providers: [
    { provide : HTTP_INTERCEPTORS, useClass:AuthInterceptor,multi:true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
