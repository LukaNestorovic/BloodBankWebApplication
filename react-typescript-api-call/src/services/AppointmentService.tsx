import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/";

class AppointmentService {
    scheduleAppointment(data: any) {
        return axios.post(API_BASE_URL + "appointment/admin", data)
    }
    findAppointmentsAdmin(data: any) {
        console.log(data)
        return axios.put(API_BASE_URL + "appointment/admin", data)
    }
    findAppointments(role: any, enable:any) {
        return axios.get(API_BASE_URL + "appointments/" + enable + "/" + role)
    }
    updateAppointment(data: any) {
        return axios.put(API_BASE_URL + "appointment", data)
    }
    findScheduledAppointment(data: any, role:any, enable:any) {
        return axios.get(API_BASE_URL + "scheduledappointment/" + data + "/" + role + "/" + enable)
    }
    findPastAppointment(data: any, role:any, enable:any) {
        return axios.get(API_BASE_URL + "pastappointment/" + data + "/" + role + "/" + enable)
    }
    deleteAppointment(data: any) {
        return axios.put(API_BASE_URL + "deleteappointment", data)
    }
    findAppointmentsUser(data: any) {
        return axios.post(API_BASE_URL + "appointment/user", data)
    }
    enrollAppointmentsUser(data: any) {
        return axios.put(API_BASE_URL + "appointment/user", data)
    }
    generateQR(center: any, doctor: any, date: any){
        return axios.get(API_BASE_URL + "qr/" + center + "/" + doctor + "/" + date)
    }
    sendMail(data: any){
        return axios.post(API_BASE_URL + "sendemailqr/" + data)
    }
}

export default new AppointmentService();