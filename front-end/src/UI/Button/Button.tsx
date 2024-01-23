import { ReactNode } from 'react';
import './Button.scss';

interface Props {
  callback: () => void;
  children: ReactNode;
}

export const Button: React.FC<Props> = ({ callback, children }) => {
  return (
    <button
      onClick={callback}
      className="button"
    >
      { children }
    </button>
  );
}
