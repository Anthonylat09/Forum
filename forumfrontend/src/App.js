import './App.css';
import Inscription from './pages/Inscription'
import Accueil from './pages/Accueil';
import PageSujets from './pages/PageSujets';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import React from 'react';
import Discussion from './pages/Discussion';
import Messages from './components/Messages';
function App() {
  const [token,setToken] = React.useState(null)
  return (
    <Discussion/>
  );
}

export default App;
