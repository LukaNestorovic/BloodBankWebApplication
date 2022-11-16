import axios from "axios";

const DONOR_API_BASE_URL = "http://localhost:8080/api/";

class DonorFormService{
    saveDonorForm(donorForm:any){
        return axios.post(DONOR_API_BASE_URL + "donorform", donorForm)
    }

    findUser(id:any){
        return axios.get("http://localhost:8080/api/users/" + id)
    }
}

export default new DonorFormService();