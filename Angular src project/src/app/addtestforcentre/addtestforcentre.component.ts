import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Checkup } from '../checkup';
import { Diagnosiscentre } from '../diagnosiscentre';
import { DiagnosisService } from '../diagnosis.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-addtestforcentre',
  templateUrl: './addtestforcentre.component.html',
  styleUrls: ['./addtestforcentre.component.css']
})
export class AddtestforcentreComponent implements OnInit {

  centres: Diagnosiscentre[] = [];
  tests: Checkup[] = [];
  testId: string;
  centreId: string;
  errorMsg: string;

  @ViewChild("frm")
  form: NgForm;
  msg: any;

  constructor(private diagnosisService: DiagnosisService, private route: ActivatedRoute) { }
  ngOnInit() {

    this.diagnosisService.viewCentres().subscribe(data => {
      console.log(data);
      this.centres = data;
    });

    this.diagnosisService.viewalltests().subscribe(data => {
      console.log(data);
      this.tests = data;
    });

  }
  addTestForCentre() {
    this.diagnosisService.addTestForCentre(this.testId, this.centreId).subscribe(data => {
      console.log(data);
      this.msg = data.message;
      this.form.resetForm();
    },
      error => {
        console.log(error);
        this.errorMsg = error.error.message
      });

  }
}
