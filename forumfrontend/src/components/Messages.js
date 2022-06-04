import * as React from 'react';
import { useState,useEffect } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper, Button } from '@mui/material';
import { useLocation, useNavigate } from 'react-router-dom';
import KeyboardBackspaceIcon from '@mui/icons-material/KeyboardBackspace';

export default function Messages() {

    const[contenu,setContenu]=useState('')
    const[messages,setMessages]=useState([])

    const {state} = useLocation();

    const navigate = useNavigate()

    const idSujet = state.idSujet

    const idCategorie = state.idCategorie

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

        setContenu('')
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
    },[messages,idSujet])


  return (
    <Container style = {{alignItems: 'center',
                         justifyContent: 'center'}}>
                             
         
        <Paper elevation={3}
               style={paperStyle}>
                   <Button style= {{position: 'relative',
                             right: '50%',
                             color: 'red',
                             }} 
                    onClick= {() => navigate('/sujets', {
                        state: {
                            idCategorie: idCategorie
                        }
                    })}>
                <KeyboardBackspaceIcon sx={{fontSize: 40}}/>
            </Button>
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
                    <span id = "textSpan"
                          style = {{fontWeight: 'normal'}}>
                            {message.contenu}
                </span>
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