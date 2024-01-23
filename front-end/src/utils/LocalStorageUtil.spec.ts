import { LocalStorage } from "./LocalStorageUtil";

describe('LocalStorage', () => {
  describe('getToken', () => {
    test('Should return null', () => {
      const token = '';

      localStorage.setItem('optic-okulist__token', token);

      expect(LocalStorage.getToken()).toBe(null);
    });

    test('Should return non-empty token', () => {
      const token = 'the secretToken';

      localStorage.setItem('optic-okulist__token', token);

      expect(LocalStorage.getToken()).toBe(token);
    });
  });

  describe('setToken', () => {
    test('Should set non-empty token', () => {
      const token = '';
      const tokenToSet = 'the secret token'

      localStorage.setItem('optic-okulist__token', token);

      LocalStorage.setToken(tokenToSet);
      expect(LocalStorage.getToken()).toBe(tokenToSet);
    });

    test('Should throw error', () => {
      const token = '';
      const tokenToSet = ''

      localStorage.setItem('optic-okulist__token', token);

      expect(() => LocalStorage.setToken(tokenToSet)).toThrowError();
    });
  });
});