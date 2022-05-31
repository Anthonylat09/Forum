import Appbar from "../components/Appbar";
import UserAuth from "../components/UserAuth";
function Inscription({authenticate}) {
  return (
    <div className="App">
      <Appbar text ="Connexion"/>
      <UserAuth authenticate = {authenticate}/>
    </div>
  );
}

export default Inscription;