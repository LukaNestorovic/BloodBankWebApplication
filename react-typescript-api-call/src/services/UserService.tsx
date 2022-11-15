import axios from "axios";
import http from "../http-common"
const EMPLOYEE_API_BASE_URL = "http://localhost:8080/api/";

class UserService {
    saveUser(user: any) {
        return axios.post(EMPLOYEE_API_BASE_URL + "register", user);
    }
    getUser(user: any){
        return axios.post(EMPLOYEE_API_BASE_URL + "profile",user)
    }
    updateUser(user: any){
        return axios.put(EMPLOYEE_API_BASE_URL + "profile",user)
    }
    logIn(user: any){
        return axios.post(EMPLOYEE_API_BASE_URL + "login", user)
    }
}

export default new UserService();