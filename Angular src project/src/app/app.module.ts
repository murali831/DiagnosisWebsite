import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
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
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ErrorpagecomponentComponent } from './errorpagecomponent/errorpagecomponent.component';
import { AppointmentComponent } from './appointment/appointment.component';
import { HomeComponent } from './home/home.component';
import { ContactUsComponent } from './contact-us/contact-us.component';

@NgModule({
  declarations: [
    AppComponent,
    DiagnosiscentreComponent,
    CheckupComponent,
    ViewtestforcentreComponent,
    ViewcentresfortestComponent,
    SearchcentreComponent,
    ViewslotComponent,
    AddtestforcentreComponent,
    AddslotComponent,
    MakeappointmentComponent,
    ViewuserappointmentComponent,
    ViewadminappointmentComponent,
    ErrorpagecomponentComponent,
    AppointmentComponent,
    HomeComponent,
    ContactUsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
