import './App.css';
import Inscription from './pages/Inscription'
import Accueil from './pages/Accueil';
import PageSujets from './pages/PageSujets';
import { Navigate, Route, Routes, useNavigate } from 'react-router-dom';
import React from 'react';
import Discussion from './pages/Discussion';
function App() {
  const [token,setToken] = React.useState(1)

  const navigate = useNavigate()


  return (
    <Routes>
      { !token && (
        <Route path = '/' element = {<Inscription/>}/> 
      )}
      {token && (
        <>
        <Route path = '/accueil' element = {<Accueil/>}/>
        <Route path = '/sujets' element = {<PageSujets/>}/>
        <Route path = '/discussion' element = {<Discussion/>}/>
        </>
      )}
      
      <Route path = '*' element = {<Navigate to = {token? "/accueil" : "/"}/>}/>
    </Routes>
  );
}

export default App;
