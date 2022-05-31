import Appbar from "../components/Appbar";
import Messages from '../components/Messages'
function Discussion({logout}) {
  return (
    <div className="App">
      <Appbar text ="Déconnexion" logout = {logout}/>
      <Messages/>
    </div>
  );
}

export default Discussion;