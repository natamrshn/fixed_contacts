export interface RegistrationRequest {
  email: string;
  password: string;
  repeatPassword: string;
  firstName: string;
  lastName: string;
  phoneNumber: number;
}

export interface RegistrationResponse {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  phoneNumber: number;
}
