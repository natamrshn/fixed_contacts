import { BACKEND_URL } from "../../apiConfig";

export class AuthAPI {
  static url = `BACKEND_URL/user`;
  static headers = {
    "Authorization": `Bearer token`
  }

  static setTokenToHeader(token: string) {
    if (!token || token.length === 0) {
      throw new Error('You should give me a token. Not empty argument or undefined');
    }

    this.headers.Authorization = `Bearer ${token}`;
  }
}