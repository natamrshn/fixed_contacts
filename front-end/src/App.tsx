import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';

import { MainPage } from './pages/MainPage';
import { AboutUsPage } from './pages/AboutUsPage';
import { ReviewsPage } from './pages/ReviewsPage';
import { ServicesPage } from './pages/ServicesPage';
import { UsefulArticlesPage }  from './pages/UsefulArticlesPage';
import { ContactsPage } from './pages/ContactsPage';

function App() {

  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={ <MainPage /> } />
          <Route path="/about-us" element={ <AboutUsPage /> } />
          <Route path="/reviews" element={ <ReviewsPage /> } />
          <Route path="/services" element={ <ServicesPage /> } />
          <Route path="/contacts" element={ <UsefulArticlesPage /> } />
          <Route path="/useful-articles" element={ <ContactsPage /> } />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
