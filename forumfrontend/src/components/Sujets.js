import * as React from 'react';
import { useState,useEffect } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper, Button } from '@mui/material';
import KeyboardBackspaceIcon from '@mui/icons-material/KeyboardBackspace';
import { useLocation, useNavigate } from 'react-router-dom';
export default function Sujets(props) {
    const[titre,setTitre]=useState('')
    const[sujets,setSujets]=useState([])
    const[creerSujet,setCreerSujet]=useState(false)
    const[track,setTrack]=useState('')

    const navigate = useNavigate();

    const {state} = useLocation()

    const idCategorie = state.idCategorie

    const idPersonne = state.idPersonne


    const clickCreation=(e) =>{
        e.preventDefault()
        setTrack('done')
    }

    

    function clickRetour(e) {
        e.preventDefault();
        setTrack('');
    }
    const clickAjout=(e) =>{

        const sujet = {titre}

        var url = "http://localhost:8080/forum/categories/"+idCategorie

        url = url+ "/personnes/"

        url = url + idPersonne
     
        const options = {
            method: "POST",
            headers:{"Content-Type":"application/json"},
            body: JSON.stringify(sujet)
        }
        fetch(url, options)
        setTrack('')

        setTitre('')
        
    }
    
    const handlePress = (e) => {
        if(e.key === 'Enter'){
            clickAjout()
        }
    }

    useEffect(()=> {
        var url = "http://localhost:8080/forum/categories/"+idCategorie
        url = url + "/sujets"
      fetch(url,{
          method:"GET"
      }).then(res => res.json())
        .then((result)=>{
            setSujets(result);
        }
        )
  },[sujets,idCategorie])

  useEffect(()=> {
      if (track === 'done'){
          setCreerSujet(true)
      }
      else{
          setCreerSujet(false)
      }
  }, [track])

    
  return (
    <Container style = {{alignItems: 'center',
                         justifyContent: 'center'}}>
        {
        !creerSujet?
        
        <Paper elevation={3}
               style={paperStyle}>
                   <Button style= {{position: 'relative',
                             right: '50%',
                             color: 'red',
                             }} 
                    onClick= {()=> {navigate ('/accueil', {
                        state: {
                            idPersonne: idPersonne,
                        }
                    })}}>
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
                <Button variant="contained"
                        onClick={clickCreation}
                        >Ajouter un sujet</Button>
            </div>
            </Box>
            <div>
            {sujets.map(sujet=>(
                <Button onClick = {() => navigate('/discussion', {
                    state: {
                        idPersonne: idPersonne,
                        idSujet: sujet.id,
                        idCategorie: idCategorie
                    }
                })}>
                <Paper elevation={6} style={{width: '80ch',margin: '10px', padding:'15px', textAlign:'center'}} key={sujet.id}>
                <span id = "textSpan"
                          style = {{fontWeight: 'normal'}}>
                            <h1>{sujet.titre}</h1>
                </span>
                    
                </Paper>
                </Button>
            ))}
            </div>
            
        </Paper> :
        <Paper elevation = {3}
               style = {paperStyle}>
            <div>
            <Button style= {{position: 'relative',
                             right: '50%',
                             color: 'red',
                             }} 
                    onClick= {clickRetour}>
                <KeyboardBackspaceIcon sx={{fontSize: 40}}/>
            </Button>
            </div>    
            <h1>Ajouter un sujet</h1>
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
                        label="Titre"
                        value = {titre}
                        onChange = {(e)=> setTitre(e.target.value)}
                        onKeyPress = {handlePress}
                    />
                </div>
                </Box>
                <div>
                    <Button variant="contained"
                            onClick={clickAjout}
                            >Ajouter</Button>
                </div>
            
        </Paper>
        }
        
    </Container>
  );
}
const paperStyle = {padding: '100px 50px',
                    width : 800,
                    margin: '20px auto',
                    
                    }