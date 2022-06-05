import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper, Button } from '@mui/material';
import {useNavigate } from 'react-router-dom';
function UserLog({authenticate}) {
    const[pseudo,setPseudo]=React.useState('')
    const[motDePasse,setMotDePasse]=React.useState('')

    const navigate = useNavigate()

    const[messageDerreur,setMessageDerreur]=React.useState(false)

    const handleLogin = () => 
    {
        const params = {pseudo,motDePasse}
        const options = {
            method: 'POST',
            headers:{"Content-Type":"application/json"},
            body : JSON.stringify(params)
        };
        const url = 'http://localhost:8080/forum/personnes/connect'
        fetch(url,options)
        .then(response => response.json())
        .then (response => 
            {
                if(response.id !== 0) 
                {
                    const idPersonne = response.id
                    
                    authenticate()
                    navigate('/accueil', {
                        state: {
                            idPersonne: idPersonne,
                        }
                    })
                }
                else{
                    setMessageDerreur(true)
                }
            })
      
        
    }


    const handlePress = (e) => {
        if(e.key === 'Enter'){
            handleLogin()
        }
    }

  return (
    <div className="App">
      <Container>
        <Paper elevation = {3}
               style = {paperStyle}>
            <h1>Connexion</h1>
            <Box
                component="form"
                sx={{
                    '& .MuiTextField-root': { m: 1, width: '65ch' },
                }}
                noValidate
                autoComplete="off"
            >
                <div>
                    <TextField
                        required
                        id="outlined-required"
                        label="Pseudo"
                        
                        onChange = {(e)=> setPseudo(e.target.value)}
                        onKeyPress= {handlePress}
                    />
                </div>
                <div>
                    <TextField
                        required
                        id="outlined-password-input"
                        label="Mot de passe"
                        type="password"
                        autoComplete="current-password"
                       
                        onChange = {(e)=> setMotDePasse(e.target.value)}
                        onKeyPress = {handlePress}
                    />
                </div>
                {
                    messageDerreur? 
                    <label style = {{color: 'red'}}>Pseudo ou Mot de passe Erroné</label> :
                    <>
                    </>
                }
                <div>
                    <Button variant="contained"
                            onClick={handleLogin}
                            >Se connecter</Button>
                </div>
            </Box>
        </Paper>
    </Container>
    </div>
  );
}
const paperStyle = {padding: '100px 50px',
                    width : 800,
                    margin: '20px auto',
                    }

export default UserLog;