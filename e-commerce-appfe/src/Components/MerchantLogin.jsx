import Form from 'react-bootstrap/Form';
import { useState ,axios} from 'react';
import { Link } from 'react-router-dom';

const MerchantLogin = () => {
   let[email,setEmail]=useState("");
   let[password,setPassword]=useState("");

  //  function verifyMerchant(e)
  //  {
  //     e.preveventDefault();
  //     axios.post(`http://localhost:8080/merchants/verify/${email}${password}`)
  //     .then((res)=>{
  //        console.log(res);
  //        alert("login successful")
  //     })
  //     .catch((err)=>{
  //           console.log(err)
  //           alert("invalid credentials")
  //     })
  //  }
   
    return ( 
        <div className="merchantlogin">
     <Form>
      <Form.Group className="mb-3" controlId="formGroupEmail">
        <Form.Label>Email address</Form.Label>
        <Form.Control value={email} onChange={(e)=>setEmail(e.target.value)}type="email" placeholder="Enter email" />
      </Form.Group>
      <Form.Group className="mb-3" controlId="formGroupPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control value={password} onChange={(e)=>setPassword(e.target.value)} type="password" placeholder="Password" />
      </Form.Group>
      <Form.Group>
        <button  className='btn btn-success mx-4'>Sign in</button>
        <button  className='btn btn-danger mx-4'><Link to="/merchantsignup">SignUp</Link></button>
      </Form.Group>
      
    </Form>
        </div>
     );
}
 
export default MerchantLogin