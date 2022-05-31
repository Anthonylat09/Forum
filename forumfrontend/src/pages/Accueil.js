import Appbar from "../components/Appbar";
import Categories from "../components/Categories";
function Accueil({logout}) {
  return (
    <div className="App">
      <Appbar text ="Déconnexion" logout = {logout}/>
      <Categories/>
    </div>
  );
}

export default Accueil
