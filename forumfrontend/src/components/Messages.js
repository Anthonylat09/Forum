import * as React from 'react';
import { useState,useEffect } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper, Button } from '@mui/material';
import { useLocation } from 'react-router-dom';
export default function Messages() {

    const[contenu,setContenu]=useState('')
    const[messages,setMessages]=useState([])

    const {state} = useLocation();

    const idSujet = state.idSujet

    const clickAjout=(e) =>{
        e.preventDefault()
        const message = {contenu}

        const url = "http://localhost:8080/forum/sujets/"+idSujet

        const options = {
            method: "POST",
            headers:{"Content-Type":"application/json"},
            body: JSON.stringify(message)
        }

        fetch(url,options)
    }

    useEffect(()=> {

        var url = "http://localhost:8080/forum/sujets/"+idSujet
        url = url + "/messages"
        fetch(url,{
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
            <div>
            {messages.map(message=>(
                <Paper elevation={6} style={{margin: '10px', padding:'15px', textAlign:'left'}} key={message.id}>
                    Id: {message.id}<br/>
                    Contenu: {message.contenu}<br/>
                </Paper>
            ))}
            </div>
        </Paper>
        
        

    </Container>
  );
}

const paperStyle = {padding: '100px 50px',
                    width : 800,
                    margin: '20px auto',
                    
                    }