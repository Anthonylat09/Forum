import Appbar from "../components/Appbar";
import UserAuth from "../components/UserAuth";
function Inscription({authenticate}) {
  return (
    <div className="App">
      <Appbar/>
      <UserAuth authenticate = {authenticate}/>
    </div>
  );
}

export default Inscription;