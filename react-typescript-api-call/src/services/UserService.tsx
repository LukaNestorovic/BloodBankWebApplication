import axios from "axios";
import http from "../http-common"
const EMPLOYEE_API_BASE_URL = "http://localhost:8080/api/register";

class UserService {
    saveUser(user: any) {
        return axios.post(EMPLOYEE_API_BASE_URL, user);
    }
    getUser(user: any){
        return axios.get("/update",user)
    }
}

export default new UserService();