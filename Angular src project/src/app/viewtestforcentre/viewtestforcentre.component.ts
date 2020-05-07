import { Component, OnInit } from '@angular/core';
import { Diagnosiscentre } from '../diagnosiscentre';
import { ActivatedRoute } from '@angular/router';
import { DiagnosisService } from '../diagnosis.service';

@Component({
  selector: 'app-viewtestforcentre',
  templateUrl: './viewtestforcentre.component.html',
  styleUrls: ['./viewtestforcentre.component.css']
})
export class ViewtestforcentreComponent implements OnInit {
  tests:any= [];
   centre: Diagnosiscentre= new Diagnosiscentre();
   adminFlag=false;


  constructor(private diagnosisService:DiagnosisService,private route:ActivatedRoute) { }

  ngOnInit() {
    let token = localStorage.getItem('token');
    if(token != null){
let role = this.diagnosisService.decrypt(token.split("-")[2]);
console.log(this.adminFlag);
if( role.trim()=='admin'){
  this.adminFlag=true;
  console.log(this.adminFlag);}
else
  this.adminFlag=false;}
  this.route.paramMap.subscribe(params=>{let centreId= params.get('cid');
                                this.diagnosisService.viewTestsForCentre(centreId).subscribe(data=>{console.log(data);
                                                                   this.tests=data;
                                                                   this.centre= this.tests[0].diagnosisCentre});});
}
}
