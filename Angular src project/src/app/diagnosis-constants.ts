export class DiagnosisConstants {
    public static SPRING_WEB="http://localhost:8082/springrest/";

    public static ADD_CENTRE_URL= DiagnosisConstants.SPRING_WEB +"adddiagnosis";
    public static VIEW_CENTRES_URL= DiagnosisConstants.SPRING_WEB+"viewalldiagnosis";
    public static EDIT_CENTRE_URL= DiagnosisConstants.SPRING_WEB+"editdiagnosis";
    public static VIEW_TESTS_URL = DiagnosisConstants.SPRING_WEB+"viewalltests";
    public static VIEW_TESTS_DIAGNOSIS_URL= DiagnosisConstants.SPRING_WEB +"viewtestfordiagnosisid";
    public static VIEW_CENTRES_TEST_URL= DiagnosisConstants.SPRING_WEB +"viewalldiagnosiscenter";
    public static SEARCH_CENTRE_URL= DiagnosisConstants.SPRING_WEB +"searchtest";
    public static VIEW_SLOTS_URL = DiagnosisConstants.SPRING_WEB + "viewslots";
    public static ADD_TEST_CENTRE_URL = DiagnosisConstants.SPRING_WEB + "addtestfordiagnosiscentre";
    public static ADD_SLOT_URL = DiagnosisConstants.SPRING_WEB + "createnewslot";
    public static MAKE_APMT_URL=DiagnosisConstants.SPRING_WEB + "makeAppointment";
    public static VIEW_USER_APMTS_URL= DiagnosisConstants.SPRING_WEB +"viewUserAppointments";
    public static REMOVE_APMT_URL= DiagnosisConstants.SPRING_WEB +"removeAppointment";
    public static VIEW_ADMIN_APMTS_URL= DiagnosisConstants.SPRING_WEB +"viewAdminAppointments";


}
