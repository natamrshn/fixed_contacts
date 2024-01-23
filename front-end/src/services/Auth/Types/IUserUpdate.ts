export interface UserUpdateRequest {
  firstName: string;
  lastName: string;
  phoneNumber: number;
}

export interface UserUpdateResponse {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  phoneNumber: number;
}