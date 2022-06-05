import './App.css';
import Login from './pages/Login'
import Inscription from './pages/Inscription'
import Accueil from './pages/Accueil';
import PageSujets from './pages/PageSujets';
import { Navigate, Route, Routes} from 'react-router-dom';
import React, { useEffect } from 'react';
import Discussion from './pages/Discussion';
function App() {
  const [token,setToken] = React.useState(false)

  useEffect(()=> {
    const u = localStorage.getItem("token")
    u && JSON.parse(u) ? setToken(true) : setToken(false)
  }, []);

  useEffect(()=> {
    localStorage.setItem("token", token)
  },[token]);

  return (
    <Routes>
      { !token && (
        <>
        <Route path = '/' element = {<Inscription authenticate = {() => setToken(true)} />}/> 
        <Route path = '/connexion' element = {<Login authenticate = {() => setToken(true)} />}/> 
        </>
      )}
      {token && (
        <>
        <Route path = '/accueil' element = {<Accueil logout = {() => setToken(false)}/>}/>
        <Route path = '/sujets' element = {<PageSujets logout = {() => setToken(false)}/>}/>
        <Route path = '/discussion' element = {<Discussion logout = {() => setToken(false)}/>}/>
        </>
      )}

      <Route path = '*' element = {<Navigate to = {token? '/accueil' : '/'}/>}/>
      
    </Routes>
  );
}

export default App;
