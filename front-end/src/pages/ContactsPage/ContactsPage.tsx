import { useEffect } from 'react';
import './ContactsPage.scss';

export const ContactsPage: React.FC = () => {
  useEffect(() => {
    document.title = 'Окуліст - Контакти'
  }, [])

  return <></>
}
