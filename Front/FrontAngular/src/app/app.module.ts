import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ProfileComponent } from './profile/profile.component';
import { UpdateInfoComponent } from './AdminInterface/USER_MANAGEMENT/update-info/update-info.component';
import { LoginComponent } from './login/login.component';
import{ FormsModule,NgForm} from '@angular/forms';
import { UsersComponent } from './AdminInterface/USER_MANAGEMENT/users/users.component';
import { AddUserComponent } from './AdminInterface/USER_MANAGEMENT/add-user/add-user.component';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { NavbarAdminComponent } from './navbar-admin/navbar-admin.component';
import { DocumentsComponent } from './AdminInterface/DOCUMENTS_MANAGEMENT/documents/documents.component';
import { AddDocumentComponent } from './AdminInterface/DOCUMENTS_MANAGEMENT/add-document/add-document.component';

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
    AddDocumentComponent
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
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
