import * as React from 'react';
import { useState,useEffect } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper, Button } from '@mui/material';
export default function Messages() {

    const[contenu,setContenu]=useState('')
    const[messages,setMessages]=useState([])


    const clickAjout=(e) =>{
        e.preventDefault()
        const categorie = {contenu}
        console.log(categorie)
        fetch("http://localhost:8080/forum/messages",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body: JSON.stringify(categorie)
        }).then(()=> (console.log("Nouveau utilisateur créé")))
        
    }

    useEffect(()=> {
        fetch("http://localhost:8080/forum/messages",{
            method:"GET"
        }).then(res => res.json())
          .then((result)=>{
              setMessages(result);
          }
          )
    },[messages])


  return (
    <Container style = {{alignItems: 'center',
                         justifyContent: 'center'}}>
         
        <Paper elevation={3}
               style={paperStyle}>
                   <Box
                component="form"
                sx={{
                    '& .MuiTextField-root': { m: 1, width: '65ch' },
                }}
                noValidate
                autoComplete="off"
            >
            <div>
            {messages.map(message=>(
                <Paper elevation={6} style={{margin: '10px', padding:'15px', textAlign:'left'}} key={message.id}>
                    Id: {message.id}<br/>
                    Contenu: {message.contenu}<br/>
                </Paper>
            ))}
            </div>
            <div>
                    <TextField
                        id="outlined-required"
                        label="Message"
                        value = {contenu}
                        onChange = {(e)=> setContenu(e.target.value)}
                    />
                </div>
                <div>
                    <Button variant="contained"
                            onClick={clickAjout}
                            >Envoyer</Button>
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