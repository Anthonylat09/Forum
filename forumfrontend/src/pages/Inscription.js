import Appbar from "../components/Appbar";
import User from '../components/User'
function Inscription({authenticate}) {
  return (
    <div className="App">
      <Appbar/>
      <User authenticate = {authenticate}/>
    </div>
  );
}

export default Inscription;