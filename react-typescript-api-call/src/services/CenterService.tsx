import axios from "axios";

const CENTER_API_BASE_URL = "http://localhost:8080/api/";

class CenterService {
    getCentersGlobal() {
        return axios.get(CENTER_API_BASE_URL + "global-centers");
    }
    getCentersGlobalFilter(data: any){
        return axios.post(CENTER_API_BASE_URL + "global-centers", data)
    }    
    getCenters() {
        return axios.get(CENTER_API_BASE_URL + "centers");
    }

    getCenterById(id:any) {
        return axios.get(CENTER_API_BASE_URL + "centers" + "/" + id);
    }
}

export default new CenterService();