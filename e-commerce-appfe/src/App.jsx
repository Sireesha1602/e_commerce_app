import logo from './logo.svg';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import './Components/LandingPage.jsx';
import {BrowserRouter,Routes,Route}from "react-router-dom";
import LandingPage from './Components/LandingPage.jsx';
import MerchantLogin from './Components/MerchantLogin.jsx';
import UserLogin from './Components/UserLogin.jsx';
import MerchantSignup from './Components/MerchantSignup.jsx';
function App() {
  return (
    <div className="App">
        <BrowserRouter>
           <Routes>
              <Route path="/" element={<LandingPage/>}/>
              <Route path="/merchant" element={<MerchantLogin/>}/>
              <Route path="/user" element={<UserLogin/>}/>
              <Route path="/merchantsignup" element={<MerchantSignup/>}/>
           </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
