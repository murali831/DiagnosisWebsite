import { Component, OnInit } from '@angular/core';
import { Checkup } from '../checkup';
import { ActivatedRoute } from '@angular/router';
import { DiagnosisService } from '../diagnosis.service';

@Component({
  selector: 'app-searchcentre',
  templateUrl: './searchcentre.component.html',
  styleUrls: ['./searchcentre.component.css']
})
export class SearchcentreComponent implements OnInit {
  centres: any = [];
  tests: any = [];
  testName: string;
  adminFlag = false;
  test: Checkup;
     errorMsg:string;
  constructor(private diagnosisService: DiagnosisService, private route: ActivatedRoute) { }

  ngOnInit() {

    /*let token = localStorage.getItem('token');
    if (token != null) {
      let tname = this.diagnosisService.decrypt(token.split("-")[0]);
      this.testName = tname;
    }-*/


  }

  searchTest() {

    this.diagnosisService.searchCentre(this.testName).subscribe(data => { this.tests = data; 
                                                                          console.log(data);
                                                                          this.testName = this.tests[0].testName; },
                                                               error=>{this.errorMsg=error.error.message}

    );

  }

}
