import axios from "./axios"

const GET = 'get'
const POST = 'post'
const PUT = 'put'
const DELETE = 'delete'

class BaseApiService {
  constructor() {
    this.axios = axios;
  }

  async get(url) {
    alert("AXIOS")
    return await this.fetch({ method: GET, url })
  }

  async post(url, data) {
    return await this.fetch({ method: POST, url, data })
  }

  async put(url, data) {
    return this.fetch({ method: PUT, url, data })
  }

  async delete(url) {
    return this.fetch({ method: DELETE, url })
  }

  async fetch({ method, url, data }) {
    const { data: response } = await this.axios[method](url, data);

    return response;
  }
}

export default BaseApiService;

