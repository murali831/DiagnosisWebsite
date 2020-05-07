import { Component, OnInit, ViewChild } from '@angular/core';
import { Appointmentform } from '../appointmentform';
import { NgForm } from '@angular/forms';
import { DiagnosisService } from '../diagnosis.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-makeappointment',
  templateUrl: './makeappointment.component.html',
  styleUrls: ['./makeappointment.component.css']
})
export class MakeappointmentComponent implements OnInit {

  aform:Appointmentform = new Appointmentform();
  msg:string;
  errorMsg:string;
  @ViewChild("appmtfrm")
  form:NgForm;
  
   constructor(private diagnosisService:DiagnosisService,private route:ActivatedRoute) {}
  ngOnInit(){
       let token = localStorage.getItem('token');
                  if(token != null){
                         let contact = parseInt( this.diagnosisService.decrypt(token.split("-")[2]));
                         this.aform.contactNo= contact;
                 }
            this.route.paramMap.subscribe(params=>{let slotId= params.get('slotid');
                                                  this.aform.slotID= slotId;
                                                  console.log(slotId);});
  
  }
  makeAppointment(){
  this.diagnosisService.makeAppointment(this.aform).subscribe(data=>{console.log(data);
                                                                    this.msg=data.message;
                                                                    this.form.resetForm();
  },
  error=>{console.log(error);this.errorMsg=error.error.message});
  }
  


}
