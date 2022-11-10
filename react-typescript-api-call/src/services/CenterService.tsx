import axios from "axios";

const CENTER_API_BASE_URL = "http://localhost:8080/api/";

class CenterService {
    getCenters() {
        return axios.get(CENTER_API_BASE_URL + "global-centers");
    }
}

export default new CenterService();