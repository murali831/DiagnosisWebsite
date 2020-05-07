import { Component, OnInit, ViewChild } from '@angular/core';
import { DiagnosisService } from '../diagnosis.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-viewuserappointment',
  templateUrl: './viewuserappointment.component.html',
  styleUrls: ['./viewuserappointment.component.css']
})
export class ViewuserappointmentComponent implements OnInit {

  contactNo:string;
  errorMsg:string;
   appointments:any=[];
   showflag=true;
   @ViewChild("contactfrm")
  form:NgForm;

 constructor(private diagnosisService:DiagnosisService) {}
  patientName:string;
ngOnInit(){
       let token = localStorage.getItem('token');
                if(token != null){
                       let patientName = this.diagnosisService.decrypt(token.split("-")[0]);
                       this.patientName= patientName;
               }

        
}

removeApmt(apmtId){
        this.diagnosisService.removeAppointment(apmtId).subscribe(data=>{this.appointments = this.appointments.
          filter(a=>a.apmtId != apmtId)},
       error=>{this.errorMsg= error.error.message}
);
}

viewHistory(){
  this.diagnosisService.viewUserAppointments(this.contactNo).subscribe(
    data=>{this.appointments= data;this.showflag=false;this.patientName=this.appointments[0].patientName;},
    error=>{console.log(error);this.errorMsg= error.error.message});
}
}
