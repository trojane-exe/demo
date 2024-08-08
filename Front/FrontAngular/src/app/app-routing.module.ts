import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { UpdateInfoComponent } from './AdminInterface/USER_MANAGEMENT/update-info/update-info.component';
import { LoginComponent } from './login/login.component';
import { AppComponent } from './app.component';
import { UsersComponent } from './AdminInterface/USER_MANAGEMENT/users/users.component';
import { AddUserComponent } from './AdminInterface/USER_MANAGEMENT/add-user/add-user.component';
import { DocumentsComponent } from './AdminInterface/DOCUMENTS_MANAGEMENT/documents/documents.component';
import { AddDocumentComponent } from './AdminInterface/DOCUMENTS_MANAGEMENT/add-document/add-document.component';
import { BookDocumentComponent } from './AdminInterface/DOCUMENTS_MANAGEMENT/book-document/book-document.component';
import { UpdateDocumentComponent } from './AdminInterface/DOCUMENTS_MANAGEMENT/update-document/update-document.component';
import { ReservationsComponent } from './AdminInterface/RESERVATIONS_MANAGEMENT/reservations/reservations.component';
import { UpdateReservationComponent } from './AdminInterface/RESERVATIONS_MANAGEMENT/update-reservation/update-reservation.component';
import { EmpruntsComponent } from './AdminInterface/EMPRUNT_MANAGEMENT/emprunts/emprunts.component';
import { ValidateEmpruntComponent } from './AdminInterface/RESERVATIONS_MANAGEMENT/validate-emprunt/validate-emprunt.component';


const routes: Routes = [

  {
    path:'profile',
    component:ProfileComponent},
    {
    path:'update-profile/:id',
    component:UpdateInfoComponent
  },
  {path:'',
    component:LoginComponent,
  },
  {
    path:'users',
    component:UsersComponent,
  },
  {
    path:'add-user',
    component:AddUserComponent
  },
  {
    path : 'documents',
    component:DocumentsComponent
  },
  {
    path :'add-document',
    component : AddDocumentComponent
  }
  ,
  {
    path : 'reserve-doc/:id',
    component : BookDocumentComponent
  },
  {
    path : 'update-doc/:id',
    component : UpdateDocumentComponent
  },
  {
    path : 'reservations',
    component : ReservationsComponent
  },
  {
    path : 'update-reservation/:id',
    component : UpdateReservationComponent
  },
  {
    path : 'emprunts',
    component : EmpruntsComponent
  },
  {
    path:'add-emprunt/:id',
    component : ValidateEmpruntComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
