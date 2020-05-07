import { Component, OnInit } from '@angular/core';
import { Checkup } from '../checkup';
import { ActivatedRoute } from '@angular/router';
import { DiagnosisService } from '../diagnosis.service';

@Component({
  selector: 'app-viewcentresfortest',
  templateUrl: './viewcentresfortest.component.html',
  styleUrls: ['./viewcentresfortest.component.css']
})
export class ViewcentresfortestComponent implements OnInit {
  centres:any= [];
   test : Checkup = new Checkup();
  adminFlag=false;
  constructor(private diagnosisService:DiagnosisService,private route:ActivatedRoute) { }

  ngOnInit() {
    let token = localStorage.getItem('token');
    if(token != null){
   let role = this.diagnosisService.decrypt(token.split("-")[2]);
   if( role.trim()=='admin')
        this.adminFlag=true;
   else
      this.adminFlag=false;}

this.route.paramMap.subscribe(params=>{let testId= params.get('tid');
                              this.diagnosisService.viewCentresForTest(testId).subscribe(data=>{console.log(data);
                                                              this.centres=data;
                                                              this.test= this.centres[0].checkup});

});
  }

}
