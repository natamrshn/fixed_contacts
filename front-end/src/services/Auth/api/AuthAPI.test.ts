import { AuthAPI } from "./AuthAPI";

describe('AuthAPI', () => {
  describe('setTokenToHeader()', () => {
    beforeEach(() => {
      AuthAPI.headers.Authorization = `Bearer token`
    });

    test('Should update token in headers', () => {
      const token = 'some token';

      AuthAPI.setTokenToHeader(token);
  
      const result = AuthAPI.headers;
  
      expect(result.Authorization).toBe(`Bearer ${token}`)
    })

    test('Should throw error if token is false', () => {
      const token = '';

      expect(() => AuthAPI.setTokenToHeader(token)).toThrowError();
    })
  })
})