import { useEffect,useState } from "react";
import '../styles/updatemerchant.css'
import axios from "axios"
const UpdateMerchant = () => {
    let [id,setid]=useState()
    let [name,setname]=useState("")
    let [phone,setphone]=useState()
    let [gst_number,setgstnumber]=useState("")
    let [email,setemail]=useState("")
    let [password,setpassword]=useState("")
     let data={id,name,phone,gst_number,email,password};
     let merchant=JSON.parse(localStorage.getItem("Merchant"));

     useEffect(()=>{
         setid(merchant.id)
         setname(merchant.name)
         setphone(merchant.phone)
         setgstnumber(merchant.gst_number)
         setemail(merchant.email)
         setpassword(merchant.password)
     }, [])

     let updateData=(e)=>{
         e.preventDefault();
         axios.put(`http://localhost:8080/merchants`,data)
         .then((res)=>{
            console.log(res);
            alert("data updated successfully")
         })
         .catch((err)=>{
            console.log(err)
            alert("cant update data")
         })
     }
                
     

    return ( 
        <div className="editmerchant">
          <form action="" onSubmit={updateData}>
            <input type="text" value={id} onChange={(e)=>{setid(e.target.value)}} required placeholder="enter id" />
           <input type="text" value={name} onChange={(e)=>{setname(e.target.value)}} placeholder="enter name" required/>
           <input type="text" value={phone} onChange={(e)=>{setphone(e.target.value)}} placeholder="enter phone" required />
           <input type="text" value={gst_number} onChange={(e)=>{setgstnumber(e.target.value)}} placeholder="enter gst number" required />
           <input type="text" value={email} onChange={(e)=>{setemail(e.target.value)}} placeholder="enter email" required />
           <input type="text" value={password} onChange={(e)=>{setpassword(e.target.value)}} placeholder="enter password" required />
           <button>Update</button>
          </form>
        </div>
     );
}
 
export default UpdateMerchant;