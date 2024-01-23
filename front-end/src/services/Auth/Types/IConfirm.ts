export interface ConfirmCodeRequest {
  password: string;
  verificationCode: string;
}

export interface ConfirmCodeResponse {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  phoneNumber: number;
}