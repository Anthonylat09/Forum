import './App.css';
import Inscription from './pages/Inscription'
import Accueil from './pages/Accueil';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import React from 'react';
import Discussion from './pages/Discussion';
function App() {
  const [token,setToken] = React.useState(null)
  return (
    <Discussion/>
  );
}

export default App;
