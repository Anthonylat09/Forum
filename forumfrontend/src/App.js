import './App.css';
import Inscription from './pages/Inscription'
import Accueil from './pages/Accueil';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import React from 'react';
function App() {
  const [token,setToken] = React.useState(null)
  return (
    <BrowserRouter>
      <Routes>
        <Route path = "/" element= {<Inscription/>}/>
        <Route path = "/accueil" element= {<Accueil/>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
