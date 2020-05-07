

import { CanActivate, Router } from '@angular/router';
import { DiagnosisService } from './diagnosis.service';
import { Injectable } from '@angular/core';
@Injectable({
    providedIn: 'root'
  })
export class CgguardService implements CanActivate{
    
  
    constructor(private router:Router,private diagnosisService:DiagnosisService){}
    

  canActivate(route: import("@angular/router").ActivatedRouteSnapshot, 
  state: import("@angular/router").RouterStateSnapshot): boolean | 
  import("@angular/router").UrlTree | import("rxjs").Observable<boolean
   | import("@angular/router").UrlTree> | Promise<boolean 
   | import("@angular/router").UrlTree> {

         let token=localStorage.getItem("token");
         console.log("guard", token);
         if(localStorage.getItem("token")!=null){
             let arr=token.split("-");
             let userName=this.diagnosisService.decrypt(arr[1]);
             let role=this.diagnosisService.decrypt(arr[2]);
             console.log(route.data)
             if(route.data.role==undefined) return true;
             if(route.data.role!=undefined && role!=null && route.data.role == role.trim()){
                 console.log("Is Admin ",route.data.role == role)
                 return true;
             }
         }
         this.router.navigateByUrl("/error");
         return false;
     }
    

}
