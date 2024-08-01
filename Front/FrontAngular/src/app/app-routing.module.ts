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

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
