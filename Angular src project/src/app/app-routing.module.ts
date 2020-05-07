import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiagnosiscentreComponent } from './diagnosiscentre/diagnosiscentre.component';
import { CheckupComponent } from './checkup/checkup.component';
import { ViewtestforcentreComponent } from './viewtestforcentre/viewtestforcentre.component';
import { ViewcentresfortestComponent } from './viewcentresfortest/viewcentresfortest.component';
import { SearchcentreComponent } from './searchcentre/searchcentre.component';
import { ViewslotComponent } from './viewslot/viewslot.component';
import { AddtestforcentreComponent } from './addtestforcentre/addtestforcentre.component';
import { AddslotComponent } from './addslot/addslot.component';
import { MakeappointmentComponent } from './makeappointment/makeappointment.component';
import { ViewuserappointmentComponent } from './viewuserappointment/viewuserappointment.component';
import { ViewadminappointmentComponent } from './viewadminappointment/viewadminappointment.component';
import { AppointmentComponent } from './appointment/appointment.component';
import { HomeComponent } from './home/home.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { CgguardService } from './cgguard-service';
import { ErrorpagecomponentComponent } from './errorpagecomponent/errorpagecomponent.component';

const routes: Routes = [
  {path:'diagnosis',component:DiagnosiscentreComponent},
  {path:'tests',component:CheckupComponent},
  {path:'viewtests/:cid', component: ViewtestforcentreComponent},
  {path:'viewdiagnosis/:tid', component:ViewcentresfortestComponent},
  {path:'search/:tname', component:SearchcentreComponent},
  {path:'viewslots/:cid/:tid', component: ViewslotComponent},
  {path:'addtestforcentre', component: AddtestforcentreComponent,canActivate:[CgguardService], data:{role:'admin'} },
  {path:'addslot/:dtid', component: AddslotComponent,canActivate:[CgguardService], data:{role:'admin'}},
  {path:'makeappointment/:slotid',component:MakeappointmentComponent},
  {path:'viewuserappointments', component:ViewuserappointmentComponent},
  {path:'appointment', component:AppointmentComponent},
  {path:'home', component:HomeComponent},
  {path:'contact', component:ContactUsComponent},
  {path:'viewadminappointments/:slotid', component:ViewadminappointmentComponent,canActivate:[CgguardService], data:{role:'admin'}},
  {path:'error',component:ErrorpagecomponentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
