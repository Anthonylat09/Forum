import * as React from 'react';
import { useState,useEffect } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper, Button } from '@mui/material';
import KeyboardBackspaceIcon from '@mui/icons-material/KeyboardBackspace';
export default function Categories() {

    const[nom,setNom]=useState('')
    const[description,setDescription]=useState('')
    const[categories,setCategories]=useState([])
    const[creerCategorie,setCreerCategorie]=useState(false)
    const[track,setTrack]=useState('')

    const clickCreation=(e) =>{
        e.preventDefault()
        setTrack('done')
    }

    const clickRetour= (e)=> {
        e.preventDefault()
        setTrack('')
        console.log(track)
    }

    const clickAjout=(e) =>{
        e.preventDefault()
        const categorie = {nom,description}
        console.log(categorie)
        fetch("http://localhost:8080/forum/categories",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body: JSON.stringify(categorie)
        }).then(()=> (console.log("Nouveau utilisateur créé")))
        setTrack('')
        
    }

    useEffect(()=> {
        fetch("http://localhost:8080/forum/categories",{
            method:"GET"
        }).then(res => res.json())
          .then((result)=>{
              setCategories(result);
          }
          )
    },[categories])

    useEffect(()=> {
        if (track === 'done'){
            setCreerCategorie(true)
        }
        else{
            setCreerCategorie(false)
        }
    }, [track])

  return (
    <Container style = {{alignItems: 'center',
                         justifyContent: 'center'}}>
        {
        !creerCategorie ?
        
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
                <Button variant="contained"
                        onClick={clickCreation}
                        >Ajouter une Catégorie</Button>
            </div>
            <div>
            {categories.map(categorie=>(
                <Button>
                <Paper elevation={6} style={{width: '80ch',margin: '10px', padding:'15px', textAlign:'left'}} key={categorie.id}>
                    Id: {categorie.id}<br/>
                    Nom: {categorie.nom}<br/>
                    Description: {categorie.description}
                </Paper>
                </Button>
            ))}
            </div>
            </Box>
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
            <h1>AJouter une catégorie</h1>
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
                        label="Nom"
                        value = {nom}
                        onChange = {(e)=> setNom(e.target.value)}
                    />
                </div>
                <div>
                    <TextField
                        required
                        id="outlined-required"
                        label="Description"
                        value = {description}
                        onChange = {(e)=> setDescription(e.target.value)}
                    />
                </div>

                <div>
                    <Button variant="contained"
                            onClick={clickAjout}
                            >Ajouter</Button>
                </div>

            </Box>
        </Paper>
        }
        

    </Container>
  );
}

const paperStyle = {padding: '100px 50px',
                    width : 800,
                    margin: '20px auto',
                    
                    }