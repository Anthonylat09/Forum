import Appbar from "../components/Appbar";
import Sujets from "../components/Sujets"
function PageSujets({logout}) {
  return (
    <div className="App">
      <Appbar text ="Déconnexion" logout = {logout}/>
      <Sujets/>
    </div>
  );
}

export default PageSujets;
