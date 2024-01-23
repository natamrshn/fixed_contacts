import { createContext } from "react";

interface AuthContextType {
  token: string;
  setToken: React.Dispatch<React.SetStateAction<string>>;
};

export const AuthContext = createContext<AuthContextType>({
  token: '',
  setToken: () => {},
});