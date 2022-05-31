import * as React from 'react';
import { useState,useEffect } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';

export default function UserAuth({authenticate}) {

    const[pseudo,setPseudo]=useState('')
    const[email,setEmail]=useState('')
    const[motDePasse,setMotDePasse]=useState('')
    const[users,setUsers]=useState([])

    const navigate = useNavigate()

    const handleClick=(e) =>{
        e.preventDefault()
        const personne = {pseudo,email,motDePasse}
        console.log(personne)
        fetch("http://localhost:8080/forum/personnes",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body: JSON.stringify(personne)
        }).then(()=> (console.log("Nouveau utilisateur créé")))

        authenticate();
    }

    useEffect(()=> {
        fetch("http://localhost:8080/forum/personnes",{
            method:"GET"
        }).then(res => res.json())
          .then((result)=>{
              setUsers(result);
          }
          )
    },[users])

  return (
    <Container>
        <Paper elevation = {3}
               style = {paperStyle}>
            <h1>Inscription</h1>
            <Box
                component="form"
                sx={{
                    '& .MuiTextField-root': { m: 1, width: '65ch'},
                }}
                noValidate
                autoComplete="off"
            >
                <div>
                    <TextField
                        required
                        id="outlined-required"
                        label="Pseudo"
                        value = {pseudo}
                        onChange = {(e)=> setPseudo(e.target.value)}
                    />
                </div>
                <div>
                    <TextField
                        required
                        id="outlined-required"
                        label="Email"
                        value = {email}
                        onChange = {(e)=> setEmail(e.target.value)}
                    />
                </div>
                <div>
                    <TextField
                        required
                        id="outlined-password-input"
                        label="Mot de passe"
                        type="password"
                        autoComplete="current-password"
                        value = {motDePasse}
                        onChange = {(e)=> setMotDePasse(e.target.value)}
                    />
                </div>
                <div>
                    <Button variant="contained"
                            onClick={handleClick}
                            >S'inscrire</Button>
                </div>

            </Box>
        </Paper>

        <Paper elevation={3}
               style={paperStyle}>
            {users.map(user=>(
                <Paper elevation={6} style={{margin: '10px', padding:'15px', textAlign:'left'}} key={user.id}>
                    Id:{user.id} <br/>
                    pseudo:{user.pseudo} <br/>
                    email:{user.email}
                </Paper>
            ))}

        </Paper>
    </Container>
  );
}

const paperStyle = {padding: '100px 50px',
                    width : 800,
                    margin: '20px auto',
                    }