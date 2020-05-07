import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Diagnosiscentre } from './diagnosiscentre';
import { DiagnosisConstants } from './diagnosis-constants';
import { Slotform } from './slotform';
import { Appointmentform } from './appointmentform';
@Injectable({
  providedIn: 'root'
})
export class DiagnosisService {
  userName: string;
  constructor(private http: HttpClient) { }
 
  addCentre(centre : Diagnosiscentre) : Observable<any>{
    let utoken=localStorage.getItem("token");
  if(utoken==null)utoken="";
  const httpHeaders=new HttpHeaders({"userId":utoken});
      return this.http.post(DiagnosisConstants.ADD_CENTRE_URL, centre,{headers:httpHeaders});
  }
  
  editCentre(centre:Diagnosiscentre):Observable<any>{
    return this.http.put(DiagnosisConstants.EDIT_CENTRE_URL, centre);
  }

  viewCentres():Observable<any>{
    return this.http.get(DiagnosisConstants.VIEW_CENTRES_URL);
  }
  viewalltests():Observable<any>{
    return this.http.get(DiagnosisConstants.VIEW_TESTS_URL)
  }
  viewTestsForCentre(centreId:string):Observable<any>{
    return this.http.get(DiagnosisConstants.VIEW_TESTS_DIAGNOSIS_URL + '/' + centreId);
  }
  viewCentresForTest(testId:string):Observable<any>{
    return this.http.get(DiagnosisConstants.VIEW_CENTRES_TEST_URL+ '/' + testId);
  }
  searchCentre(str:string):Observable<any>{
    return this.http.get(DiagnosisConstants.SEARCH_CENTRE_URL + '/' + str.trim());
  }
  viewSlots(testId: string, centreId: string): Observable<any> {
    let utoken = localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders = new HttpHeaders({"userId": utoken});
    console.log(utoken);
    return this.http.get(DiagnosisConstants.VIEW_SLOTS_URL + "/" + testId + "/" + centreId);
  }
  addTestForCentre(testId: string, centreId: string): Observable<any> {
    let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
    return this.http.post(DiagnosisConstants.ADD_TEST_CENTRE_URL + "/" + testId + "/" + centreId, testId,{headers:httpHeaders});
  }
  
  addSlot(sform: Slotform): Observable<any> {
    let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
    return this.http.post(DiagnosisConstants.ADD_SLOT_URL, sform,{headers:httpHeaders});

  }
  public makeAppointment(frm:Appointmentform):Observable<any>{
    return this.http.post(DiagnosisConstants.MAKE_APMT_URL, frm);
 }
 public viewUserAppointments(contact:string):Observable<any>{
       return this.http.get(DiagnosisConstants.VIEW_USER_APMTS_URL+"/"+ contact);
 }
 public removeAppointment(apmtId:string){
  return this.http.delete(DiagnosisConstants.REMOVE_APMT_URL+"/"+ apmtId);
 }
 public viewadminAppointments(slotid:string):Observable<any>{
  let utoken=localStorage.getItem("token");
  if(utoken==null)utoken="";
  const httpHeaders=new HttpHeaders({"userId":utoken});
  return this.http.get(DiagnosisConstants.VIEW_ADMIN_APMTS_URL+"/"+ slotid,{headers:httpHeaders});
}
  decrypt(token : string){
    let str = " ";
    for ( let i = 0 ; i < token.length ; ++i){
      str = str + String.fromCharCode(token.charCodeAt(i) - 3)
    }
    console.log(str);
    return str;
  }
  public dologin(userId:string, pwd:string):Observable<any>{

    let postData = new FormData(); 
    postData.append('userid' , userId); 
    postData.append('password', pwd);
 return this.http.post("http://localhost:8082/springrest/login",postData, {responseType: 'text'});
 }
 
 public dologout(){
 let utoken = localStorage.getItem("token");
  if(utoken == null) utoken=""; 
  const httpHeaders = new HttpHeaders({"userId": utoken});
   return this.http.get("http://localhost:8082/springrest/logout", {headers:httpHeaders, responseType: 'text'});
 }
}
