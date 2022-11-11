import axios from "axios";

const CENTER_API_BASE_URL = "http://localhost:8080/api/";

class CenterService {
    getCentersGlobal() {
        return axios.get(CENTER_API_BASE_URL + "global-centers");
    }
    getCentersGlobalFilter(data: any){
        return axios.post(CENTER_API_BASE_URL + "global-centers",data)
    }
}

export default new CenterService();