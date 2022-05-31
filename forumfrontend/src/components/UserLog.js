import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper, Button } from '@mui/material';
function UserLog() {
    const[pseudo,setPseudo]=React.useState('')
    const[motDePasse,setMotDePasse]=React.useState('')
    const handleLogin = () => 
    {
        const params = {
            pseudo: pseudo,
            motDePasse:motDePasse
        };
        const options = {
            method: 'POST',
            body : JSON.stringify(params)
        };
        const url = 'http://localhost:8080/forum/personnes/connect'
        fetch(url,options)
        .then(response => response.json())
        .then (response => 
            {
                if(response == true) 
                {
                    alert("Vous etes connecté bg")
                }
            })
      
        
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
                    />
                </div>
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