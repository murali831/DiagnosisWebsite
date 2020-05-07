import { Component, OnInit } from '@angular/core';
import { DiagnosisService } from '../diagnosis.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-viewslot',
  templateUrl: './viewslot.component.html',
  styleUrls: ['./viewslot.component.css']
})
export class ViewslotComponent implements OnInit {

  slots: any = []
  adminFlag = false;

  constructor(private diagnosisService: DiagnosisService, private route: ActivatedRoute,private router:Router) { }

  ngOnInit() {
    let token = localStorage.getItem('token');
    if (token != null) {
      let role = this.diagnosisService.decrypt(token.split("-")[2]);
      console.log(role, role.trim().length);
      console.log(role=='admin', role.trim()=='admin');
      
      if ( role.trim()=="admin")
         this.adminFlag = true; 
      else
        this.adminFlag = false;
    }
    this.route.paramMap.subscribe(params => {
      let centreId = params.get('cid');
      let testId = params.get('tid');
      this.diagnosisService.viewSlots(centreId, testId).subscribe(data => { console.log(data); this.slots = data; });
     

    });

  }
}
