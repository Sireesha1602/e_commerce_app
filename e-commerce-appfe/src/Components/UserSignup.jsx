import {useState} from "react"
import axios from "axios"
import '../styles/usersignup.css'
const UserSignup = () => 
{
   let [name,setname]=useState("")
   let [phone,setphone]=useState()
   let [gender,setgender]=useState("")
   let [age,setage]=useState()
   let [email,setemail]=useState("")
   let [password,setpassword]=useState("")
   let data={name,phone,gender,age,email,password};

    let addUser=(e)=>{
        e.preventDefault()
        axios.post("http://localhost:8080/users",data)
        .then((res)=>{
            console.log(res.data);
            alert("Registration successful");
        })
        .catch((err)=>{
            console.log(err.data)
            alert("unable to register")
        })

        
    }
    
    return ( 
        <div className="usersignup">
            <form action="" onSubmit={addUser}>
                <input type="text" placeholder="enter  name" required  value={name} onChange={(e)=>{setname(e.target.value)}}/>
                <input type="tel" placeholder="enter phone no." required  value={phone} onChange={(e)=>{setphone(e.target.value)}}/>
                <input type="text" placeholder="enter gender" required value={gender} onChange={(e)=>{setgender(e.target.value)}} />
                <input type="number" placeholder="enter age" required value={age} onChange={(e)=>{setage(e.target.value)}} />
                <input type="email" placeholder="enter  email" required value={email} onChange={(e)=>{setemail(e.target.value)}} />
                <input type="password" placeholder="enter password" required value={password} onChange={(e)=>{setpassword(e.target.value)}}/>
                <button>Register</button>
            </form>
        </div>
     );
}
 
export default UserSignup;