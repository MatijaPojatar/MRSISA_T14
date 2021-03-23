import axios from "axios";

const INSTRUCTOR = "lekovi";
const COURSE_API_URL = "http://localhost:8080";
const INSTRUCTOR_API_URL = `${COURSE_API_URL}/${INSTRUCTOR}`;

class LekService {
  retrieveAllLekovi() {
    return axios.get(`${INSTRUCTOR_API_URL}`);
  }
}

export default new LekService();