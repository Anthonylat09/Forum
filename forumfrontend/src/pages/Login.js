import Appbar from "../components/Appbar";
import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper, Button } from '@mui/material';
function Login() {
  return (
    <div className="App">
      <Appbar text ="INSCRIPTION"> </Appbar>
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
                            //onClick={handleClick}
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

export default Login;