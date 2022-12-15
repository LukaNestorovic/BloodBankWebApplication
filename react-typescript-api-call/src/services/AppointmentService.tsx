import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/";

class AppointmentService {
    scheduleAppointment(data: any) {
        return axios.post(API_BASE_URL + "appointment/admin", data)
    }
    findAppointmentsAdmin() {
        return axios.get(API_BASE_URL + "appointment/admin")
    }
    findAppointments(){
        return axios.get(API_BASE_URL + "appointments")
    }
    updateAppointment(data:any) {
        return axios.put(API_BASE_URL + "appointment", data)
    }
}

export default new AppointmentService();