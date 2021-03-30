import axios from "axios";

const INSTRUCTOR = "apoteka";
const COURSE_API_URL = "http://localhost:8080";
const INSTRUCTOR_API_URL = `${COURSE_API_URL}/${INSTRUCTOR}`;

class ApotekaService {
    preuzmiLekove() {
      return axios.get(`${INSTRUCTOR_API_URL}/lekovi`);
    }

    pronadjiApoteke(json) {
      return axios.post(`${INSTRUCTOR_API_URL}/rezultat`, json);
    }
}

export default new ApotekaService();