import './Input.scss';

interface Props {
  type: 'text' | 'password' | 'email' | 'tel';
  placeHolder?: string;
  value: string;
  setValue: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

export const Input: React.FC<Props> = ({ type, placeHolder = '',value, setValue }) => {
  return (
    <input
      type={type}
      placeholder={placeHolder}
      value={value}
      onChange={e => setValue(e)}
    />
  );
}