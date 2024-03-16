import Form from 'react-bootstrap/Form';
import {useState} from 'react'
import axios from "axios"
import { Link,useNavigate } from 'react-router-dom';
import '../styles/userlogin.css'
const UserLogin = () => {
  let [email,setemail]=useState("");
  let[password,setpassword]=useState("");

  let navigate=useNavigate();
  function userLogin(e)
  {
    e.preventDefault()
    axios.post(`http://localhost:8080/users/verify-email?email=${email}&password=${password}`)
    .then((res)=>{

      console.log(res.data)
      localStorage.setItem("User",JSON.stringify(res.data.data))
      alert("login successful")
      navigate("/userhomepage")
    })
   
    .catch((err)=>{
       console.log(err.data)
       alert("failed to login")
    })
    
  }
    return ( 
        <div className="userlogin">
            <Form className='form'>
      <Form.Group className="mb-3" controlId="formGroupEmail">
        <Form.Label>Email address</Form.Label>
        <Form.Control type="email" placeholder="Enter email" required value={email} onChange={(e)=>{setemail(e.target.value)}} />
      </Form.Group>
      <Form.Group className="mb-3" controlId="formGroupPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control type="password" placeholder="Password" required value={password} onChange={(e)=>{setpassword(e.target.value)}} />
      </Form.Group>
      <Form.Group>
        <button className='btn btn-success mx-4' onClick={userLogin}>Sign in</button>
        <button className='btn btn-danger mx-4'><Link to="/usersignup">Sign Up</Link></button>
      </Form.Group>
      <Form.Group>
        <p>New user?Click on signup.</p>
      </Form.Group>

    </Form>
        </div>
     );
}
 
export default UserLogin;