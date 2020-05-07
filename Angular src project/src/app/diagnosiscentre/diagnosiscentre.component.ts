import { Component, OnInit, ViewChild } from '@angular/core';
import { Diagnosiscentre } from '../diagnosiscentre';
import { DiagnosisService } from '../diagnosis.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-diagnosiscentre',
  templateUrl: './diagnosiscentre.component.html',
  styleUrls: ['./diagnosiscentre.component.css']
})
export class DiagnosiscentreComponent implements OnInit {
  centres:Diagnosiscentre[] = [];
  centre:Diagnosiscentre= new Diagnosiscentre();
  msg:string;
  msg1:string;
  errorMsg:string;
  errorMsg1:string;
  errorMsg2:string;
  addCentreFlag:boolean=false;
  newFlag:boolean=false;
  viewCentreFlag=false;
  editFlag=false;
  adminFlag:boolean;
  @ViewChild("centrefrm")
  form:NgForm;

  constructor(private diagnosisService  : DiagnosisService) { }

  ngOnInit() {   
    let token = localStorage.getItem('token');
    if(token != null){
       let role = this.diagnosisService.decrypt(token.split("-")[2]);
       console.log("role",role);
        if( role.trim()=="admin"){
           this.adminFlag=true;
           console.log(this.adminFlag, "checked admin");
        }
       else{
           this.adminFlag=false;
          }
 }
  }
  showAddCentreForm(){
    this.addCentreFlag=true;
    this.viewCentreFlag=false;
    this.editFlag=false;
    this.newFlag= true;
}
addCentre(){
  this.diagnosisService.addCentre(this.centre).subscribe(data =>{ console.log(data);
                                                                  this.msg=data.message;
                                                                  this.form.resetForm();},
                                                         error=>{ console.log(error);
                                                                  this.errorMsg=error.error.message;})
                                                                  this.newFlag = true;
}
viewCentres(){
  this.diagnosisService.viewCentres().subscribe(data=>{console.log(data);
                                                       this.centres=data;
                                                       this.addCentreFlag=false;
                                                       this.viewCentreFlag=true;
                                                       this.editFlag=false;},
                                                error=>{ console.log(error);
                                                  this.errorMsg2=error.error.message;});
                                                this.newFlag = true;
}
showEditForm(centre : Diagnosiscentre){
                                         this.addCentreFlag=false;
                                         this.viewCentreFlag=false;
                                         this.editFlag=true;
                                         this.centre= centre;

}
editCentre(){
   this.diagnosisService.editCentre(this.centre).subscribe(data=>{console.log(data);
                                                                  this.msg1=data.message;
                                                                  this.addCentreFlag=false;
                                                                  this.viewCentreFlag=true;
                                                                  this.editFlag=false; },
                                                     error=>{console.log(error);
                                                            this.errorMsg1=error.error.message;})
}
}

