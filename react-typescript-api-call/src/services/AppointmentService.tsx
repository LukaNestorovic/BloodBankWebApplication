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
    findAppointmentsUser(data: any) {
        return axios.post(API_BASE_URL + "appointment/user", data)
    }
    enrollAppointmentsUser(data: any) {
        return axios.put(API_BASE_URL + "appointment/user", data)
    }
}

export default new AppointmentService();