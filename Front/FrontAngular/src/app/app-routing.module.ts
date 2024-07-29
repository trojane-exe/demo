import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { UpdateInfoComponent } from './AdminInterface/update-info/update-info.component';
import { LoginComponent } from './login/login.component';
import { AppComponent } from './app.component';
import { UsersComponent } from './AdminInterface/users/users.component';
import { AddUserComponent } from './AdminInterface/add-user/add-user.component';

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
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
