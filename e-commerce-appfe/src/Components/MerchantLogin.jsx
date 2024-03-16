import Form from 'react-bootstrap/Form';
import { useState} from 'react';
import { Link,useNavigate } from 'react-router-dom';
import axios from "axios"
import '../styles/merchantlogin.css'

const MerchantLogin = () => {
   let [email,setemail]=useState("")
   let [password,setpassword]=useState("")

   let navigate=useNavigate()

   function verifyMerchant(e)
   {
      e.preventDefault()
      axios.post(`http://localhost:8080/merchants/verify?email=${email}&password=${password}`)
      .then((res)=>{
          console.log(res.data.data);
           localStorage.setItem("Merchant",JSON.stringify(res.data.data))//inspect->application->storage->local storage.
           alert("login successful")
           navigate("/merchanthomepage");
           
      })
      .catch((err)=>{
             console.log(err.data)
            alert("invalid credentials")
      })
   }
   
    return ( 
        <div className="merchantlogin">
     <Form className='form'>
      <Form.Group className="mb-3" controlId="formGroupEmail">
        <Form.Label><i>Email address</i></Form.Label>
        <Form.Control value={email} onChange={(e)=>{setemail(e.target.value)}}type="email" placeholder="Enter email" />
      </Form.Group>
      <Form.Group className="mb-3" controlId="formGroupPassword">
        <Form.Label><i>Password</i></Form.Label>
        <Form.Control value={password} onChange={(e)=>{setpassword(e.target.value)}} type="password" placeholder="Password" />
      </Form.Group>
      <Form.Group>
        <button  className='btn btn-success mx-4' onClick={verifyMerchant}>Sign in</button>
        <button  className='btn btn-danger mx-4'><Link to="/merchantsignup">SignUp</Link></button>
      </Form.Group>
      <Form.Group>
        <p>New user?Click on signup.</p>
      </Form.Group>
      
    </Form>
        </div>
     );
}
 
export default MerchantLogin