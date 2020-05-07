import { Component, OnInit } from '@angular/core';
import { DiagnosisService } from '../diagnosis.service';
import { ActivatedRoute } from '@angular/router';
import { Appointmentform } from '../appointmentform';

@Component({
  selector: 'app-viewadminappointment',
  templateUrl: './viewadminappointment.component.html',
  styleUrls: ['./viewadminappointment.component.css']
})
export class ViewadminappointmentComponent implements OnInit {

  appointments:any=[]
  errorMsg: any;
  aform: Appointmentform = new Appointmentform();
  showflag=true;
  slotid: string;
constructor(private diagnosisService:DiagnosisService,private route:ActivatedRoute) {}
ngOnInit(){
        let token = localStorage.getItem('token');
                if(token != null){
                       let contact= parseInt(this.diagnosisService.decrypt(token.split("-")[2]));
                       this.aform.contactNo=contact;
               }
               
          this.route.paramMap.subscribe(params=>{let slotID= params.get('slotid');
             this.diagnosisService.viewadminAppointments(slotID).subscribe(
                      data=>{this.appointments= data;},
                      error=>{this.errorMsg= error.error.message});


});}

removeApmt(apmtId){
  this.diagnosisService.removeAppointment(apmtId).subscribe(data=>{this.appointments = this.appointments.
    filter(a=>a.apmtId != apmtId)},
 error=>{this.errorMsg= error.error.message});
}





}
