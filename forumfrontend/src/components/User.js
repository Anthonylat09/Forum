import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper, Button } from '@mui/material';

export default function User() {

    const[pseudo,setPseudo]=React.useState('')
    const[email,setEmail]=React.useState('')
    const[motDePasse,setMotDePasse]=React.useState('')
    const[users,setUsers]=React.useState([])
    const handleClick=(e) =>{
        e.preventDefault()
        const personne = {pseudo,email,motDePasse}
        console.log(personne)
        fetch("http://localhost:8080/forum/personnes",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body: JSON.stringify(personne)
        }).then(()=> (console.log("Nouveau utilisateur créé")))
    }

    React.useEffect(()=> {
        fetch("http://localhost:8080/forum/personnes",{
            method:"GET"
        }).then(res => res.json())
          .then((result)=>{
              setUsers(result);
          }
          )
    },[])

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

