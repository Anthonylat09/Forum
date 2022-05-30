import Appbar from "../components/Appbar";
import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper, Button } from '@mui/material';


function PageSujets() 
{ 
    let sujets = []
    React.useEffect()
    {
        const url = "http://localhost:8080/forum/sujet"
        fetch(url)
        .then(response => response.json())
        .then( response => 
            { 
                sujets = [... response]
            })
    }

    
    return (
        <div className="App">
          <Appbar text ="SE DECONNECTER"> </Appbar> 
          </div> )
}
export {PageSujets}