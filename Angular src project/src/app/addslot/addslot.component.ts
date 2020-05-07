import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Slotform } from '../slotform';
import { DiagnosisService } from '../diagnosis.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-addslot',
  templateUrl: './addslot.component.html',
  styleUrls: ['./addslot.component.css']
})
export class AddslotComponent implements OnInit {

  sform: Slotform = new Slotform();
  msg: string;
  errorMsg: string;
  @ViewChild("slotfrm")
  form: NgForm;

  constructor(private diagnosisService: DiagnosisService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => { let diagnosisId = params.get('dtid');
                                              this.sform.diagnosisTestId = diagnosisId;
                                              console.log(diagnosisId);
    });
  }

  addSlot() {
    this.diagnosisService.addSlot(this.sform).subscribe(data => {
    this.msg = data.message;
    this.form.resetForm()
    },
    error => {
      console.log(error);
      this.errorMsg = error.error.message
    });
  }



}
