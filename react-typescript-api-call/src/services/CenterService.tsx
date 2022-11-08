import axios from "axios";

const CENTER_API_BASE_URL = "http://localhost:8080/api/centers";

class CenterService {

    getCenters() {
        return axios.get(CENTER_API_BASE_URL);
    }

    getCenterById(id:any) {
        return axios.get(CENTER_API_BASE_URL + "/" + id);
    }
}

export default new CenterService();