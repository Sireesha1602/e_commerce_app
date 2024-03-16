
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import './Components/LandingPage.jsx';
import {BrowserRouter,Routes,Route}from "react-router-dom";
import LandingPage from './Components/LandingPage.jsx';
import MerchantLogin from './Components/MerchantLogin.jsx';
import UserLogin from './Components/UserLogin.jsx';
import MerchantSignup from './Components/MerchantSignup.jsx';
import MerchantHomePage from './Components/MerchantHomePage.jsx';
import UserHomePage from './Components/UserHomePage.jsx';
import UserSignup from './Components/UserSignup.jsx';
import Error from './Components/Error.jsx';
import Protect from './Components/Protect.jsx'
function App() {
  return (
    <div className="App">
        <BrowserRouter>
           <Routes>
              <Route path="/" element={<LandingPage/>}/>
              <Route path="/*" element={<Error/>}/>
              <Route path="/merchant" element={<MerchantLogin/>}/>
              <Route path="/user" element={<UserLogin/>}/>
              <Route path="/merchantsignup" element={<MerchantSignup/>}/>
              <Route path="/merchanthomepage/*" element={<Protect Child={MerchantHomePage}/>}/>
              <Route path="/userhomepage" element={<UserHomePage/>}/>
              <Route path="/usersignup" element={<UserSignup/>}/>
           </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
