import axios from "axios";

const EMPLOYEE_API_BASE_URL = "http://localhost:8080/api/register";

class UserService {
    saveEmployee(user: any) {
        return axios.post(EMPLOYEE_API_BASE_URL, user);
    }
}

export default new UserService();