import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper, Button } from '@mui/material';

import * as React from 'react';
import Box from '@mui/material/Box';
import Collapse from '@mui/material/Collapse';
import IconButton from '@mui/material/IconButton';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import DeleteIcon from '@mui/icons-material/Delete';
import { TransitionGroup } from 'react-transition-group';
import Appbar from '../components/Appbar';

const SUJETS = [
  'COmment on',
  ' Est-ce que vous  avez deja ',
  ' qui  sait '
];

function renderItem({ item, handleRemoveSubject }) {
  return (
    <ListItem
      secondaryAction={
        <IconButton
          edge="end"
          aria-label="delete"
          title="Delete"
          onClick={() => handleRemoveSubject(item)}
        >
          <DeleteIcon />
        </IconButton>
      }
    >
      <ListItemText primary={item} />
    </ListItem>
  );
}

export function PageSujets() {
  const [Sujets, setSujets] = React.useState(SUJETS.slice(0, 3));

  const handleAddSubject = () => {
    const nextHiddenItem = SUJETS.find((i) => !Sujets.includes(i));
    if (nextHiddenItem) {
      setSujets((prev) => [nextHiddenItem, ...prev]);
    }
  };

  const handleRemoveSubject = (item) => {
    setSujets((prev) => [...prev.filter((i) => i !== item)]);
  };

  const addSubjectButton = (
      <div>
          <TextField> Saisir le sujet</TextField>
    <Button
      variant="contained"
    
      onClick={handleAddSubject}
    >
      Ajouter 
    </Button> </div>
  );
  /*React.useEffect()
  {
      const url = "http://localhost:8080/forum/sujet"
      fetch(url)
      .then(response => response.json())
      .then( response => 
          {

          })
        }*/
  return (
    <div>
    <Appbar text = 'se connecter'></Appbar>

      {addSubjectButton}
      <Box sx={{ mt: 1 }}>
        <List> 
          
          <TransitionGroup>
            {Sujets.map((item) => (
              <Collapse key={item}>
                {renderItem({ item, handleRemoveSubject })}
              </Collapse>
            ))}
          </TransitionGroup>
        </List>
      </Box>
    </div>
  );
}


