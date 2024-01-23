import { useEffect } from 'react';
import './MainPage.scss';

export const MainPage: React.FC = () => {
  useEffect(() => {
    document.title = 'Окуліст - головна'
  }, [])

  return <></>;
}