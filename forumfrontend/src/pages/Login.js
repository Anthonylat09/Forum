import Appbar from "../components/Appbar";
import UserLog from "../components/UserLog"
function Login({authenticate}) {
  return (
    <div className="App">
      <Appbar text ="Inscription"> </Appbar>
      <UserLog authenticate = {authenticate}/>
    </div>
  );
}

export default Login;
