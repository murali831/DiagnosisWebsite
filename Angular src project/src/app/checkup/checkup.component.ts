import { Component, OnInit } from '@angular/core';
import { DiagnosisService } from '../diagnosis.service';
import { Diagnosiscentre } from '../diagnosiscentre';
import { Checkup } from '../checkup';

@Component({
  selector: 'app-checkup',
  templateUrl: './checkup.component.html',
  styleUrls: ['./checkup.component.css']
})
export class CheckupComponent implements OnInit {
  adminFlag = false;
  newFlag= false;
  viewTestFlag = false;
  tests: any = [];
  test: Checkup[];
  test1: Checkup[];
  errorMsg:string;
  constructor(private diagnosisService: DiagnosisService) { }

  ngOnInit() {
    let token = localStorage.getItem('token');
    if (token != null) {
      let role = this.diagnosisService.decrypt(token.split("-")[2]);
      console.log("role", role);
      if (role.trim()=='admin') {
        this.adminFlag = true;
        console.log(this.adminFlag, "checked admin");
      }
      else{  
        this.adminFlag = false;
      }
    }
  }
  viewTest() {
    this.diagnosisService.viewalltests().subscribe(data => {
      console.log(data);
      this.tests = data;
      this.viewTestFlag = true;  
    },error=>{ console.log(error);
      this.errorMsg=error.error.message;})
    this.newFlag = true;
  }
  
}
