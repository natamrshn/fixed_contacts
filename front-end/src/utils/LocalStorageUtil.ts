export class LocalStorage {
  static getToken() {
    return localStorage.getItem('optic-okulist__token') || null;
  }

  static setToken(token: string) {
    if (!token.length) {
      throw new Error(
        `LocalStorageUtil.ts:
        LocalStorage.setToken -> the token is empty! Pass not-empty token!`
      );
    }

    localStorage.setItem('optic-okulist__token', token)
  }
}
