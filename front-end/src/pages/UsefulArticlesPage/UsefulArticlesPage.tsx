import { useEffect } from 'react';
import './UsefulArticlesPage.scss';

export const UsefulArticlesPage: React.FC = () => {
  useEffect(() => {
    document.title = 'Окуліст - корисне'
  }, [])

  return <></>
};
