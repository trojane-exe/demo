import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { UpdateInfoComponent } from './update-info/update-info.component';
import { LoginComponent } from './login/login.component';
import { AppComponent } from './app.component';
import { UsersComponent } from './users/users.component';
import { AddUserComponent } from './add-user/add-user.component';

const routes: Routes = [

  {
    path:'profile',
    component:ProfileComponent},
    {
    path:'update-profile',
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
