import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper } from '@mui/material';

export default function User() {
  return (
    <Container>
        <Paper elevation = {3}
               style = {paperStyle}>
            <h1>Inscription</h1>
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
                        
                    />
                </div>
                <div>
                    <TextField
                        required
                        id="outlined-required"
                        label="Email"
                        
                    />
                </div>
                <div>
                    <TextField
                        required
                        id="outlined-password-input"
                        label="Mot de passe"
                        type="password"
                        autoComplete="current-password"
                        
                    />
                </div>
            </Box>
        </Paper>
    </Container>
  );
}

const paperStyle = {padding: '100px 50px',
                    width : 800,
                    margin: '20px auto',
                    }

