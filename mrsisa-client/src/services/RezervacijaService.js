import axios from "axios";

const INSTRUCTOR = "rezervacija";
const COURSE_API_URL = "http://localhost:8080";
const INSTRUCTOR_API_URL = `${COURSE_API_URL}/${INSTRUCTOR}`;

class RezervacijaService {
    pretrazi(name, json) {
        return axios.post(`${INSTRUCTOR_API_URL}/rezultat`, json);

    }
    retrieveRez() {
        return axios.get(`${INSTRUCTOR_API_URL}`);
      }
}

export default new RezervacijaService();