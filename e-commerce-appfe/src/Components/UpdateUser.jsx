import { useEffect, useState } from "react"
import axios from "axios"
const UpdateUser = () => {
    let [id,setid]=useState()
    let [name,setname]=useState("")
   let [phone,setphone]=useState()
   let [gender,setgender]=useState("")
   let [age,setage]=useState()
   let [email,setemail]=useState("")
   let [password,setpassword]=useState("")
   let data={id,name,phone,gender,age,email,password};
   let user=JSON.parse(localStorage.getItem("User"));

   useEffect(()=>{
      setid(user.id)
      setname(user.name)
      setphone(user.phone)
      setgender(user.gender)
      setage(user.age)
      setemail(user.email)
      setpassword(user.password)
   })

   let updateData=(e)=>{
      axios.put(`http://localhost:8080/users`,data)
      .then((res)=>{
        console.log(res.data)
        alert("user updated successfully")

      })
      .catch((err)=>{
        console.log(err.data)
        alert("cant update user")
      })
   }


    return ( 
        <div className="updateuser">
           <form action="" onSubmit={updateData}>
           <input type="" placeholder="enter id" value={id} onChange={(e)=>{setid(e.target.value)}} />
           <input type="text" placeholder="enter name" value={name} onChange={(e)=>{setname(e.target.value)}} />
           <input type="text" placeholder="enter phone" value={phone} onChange={(e)=>{setphone(e.target.value)}}/>
           <input type="text" placeholder="enter gender" value={gender} onChange={(e)=>{setphone(e.target.value)}} />
           <input type="text" placeholder="enter age" value={age} onChange={(e)=>{setage(e.target.value)}} />
           <input type="text" placeholder="enter email" value={email} onChange={(e)=>{setemail(e.target.value)}} />
           <input type="text" placeholder="enter password" value={password} onChange={(e)=>{setpassword(e.target.value)}} />
           <button>Update</button>
           </form>
        </div>
     );
}
 
export default UpdateUser;