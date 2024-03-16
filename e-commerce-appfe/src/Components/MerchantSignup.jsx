
import '../styles/merchantsignup.css'
import { useState } from "react";
import axios from "axios"
const MerchantSignup = () => {
    let [name,setName]=useState("");
    let [phone,setPhone]=useState();
    let [gst_number,setGst_number]=useState("");
    let [email,setEmail]=useState("");
    let [password,setPassword]=useState("");

    let data={name,phone,gst_number,email,password}

    let addMerchant=(e)=>{
      e.preventDefault();
      axios.post('http://localhost:8080/merchants',data)
      .then((res)=>{
        console.log(res);
        alert("data added successfully")
      })
      .catch((err)=>{
        console.log(err)
        alert("data not found")
      })
    }
    return ( 
        
       
            
            <div className="signup">
              <div className="welcome">
               <h2>WELCOME</h2>
              </div>
              <form onSubmit={addMerchant}>
                      {/* <label htmlFor="">Name</label> */}
            <input type="text" placeholder="enter name" required value={name} onChange={(e)=>{setName(e.target.value)}} ></input>
            {/* <label htmlFor="">Phone</label> */}
            <input type="tel" placeholder="enter phone" required value={phone} onChange={(e)=>{setPhone(e.target.value)}} />
            {/* <label htmlFor="">gst_number</label> */}
            <input type="text" placeholder="enetr gst number" required  value={gst_number} onChange={(e)=>{setGst_number(e.target.value)}}/>
            {/* <label htmlFor="">Email</label> */}
            <input type="email" placeholder="enter email" required value={email} onChange={(e)=>{setEmail(e.target.value)}}/>
            {/* <label htmlFor="">Password</label> */}
            <input type="password" placeholder="enter password" required value={password} onChange={(e)=>{setPassword(e.target.value)}} />
            <button >Register</button>
              </form>
           
          </div>
        
     );

}
 
export default MerchantSignup;