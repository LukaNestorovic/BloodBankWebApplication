import axios from "axios";

const CENTER_API_BASE_URL = "http://localhost:8080/api/centers";

class CenterService {

    getCenters() {
        return axios.get(CENTER_API_BASE_URL);
    }
}

export default new CenterService();