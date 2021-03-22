// import BaseApiService from "./BaseApiService"
// import {API_ROOT, TEST_URLS} from "./config"

// class TestService extends BaseApiService {

//   async getAll() {
//     alert("TEST SERVICE")
//     return await this.get(API_ROOT + TEST_URLS.GET_ALL );
//   }
// }

// export const testService = new TestService()

import axios from "axios";

const INSTRUCTOR = "tests";
const COURSE_API_URL = "http://localhost:8080";
const INSTRUCTOR_API_URL = `${COURSE_API_URL}/api/${INSTRUCTOR}`;

class TestService {
  retrieveAllTests() {
    return axios.get(`${INSTRUCTOR_API_URL}`);
  }
}

export default new TestService();