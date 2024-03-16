import {useState} from "react"
import axios from "axios"
import '../styles/addproducts.css'
const Addproduct = () => {

    let [name,setname]=useState("")
    let [brand,setbrand]=useState("")
    let [category,setcategory]=useState("")
    let [description,setdescription]=useState("")
    let [cost,setcost]=useState();
    let [image_url,setimage_url]=useState("")

    let data={name,brand,category,description,cost,image_url}
    let admin=JSON.parse(localStorage.getItem("Merchant"))
    let addData=(e)=>{
        e.preventDefault();
        axios.post(`http://localhost:8080/products/${admin.id}`,data)
        .then((res)=>{
                console.log(res)
                alert("product added successfully")
        })
        .catch((err)=>{
            console.log(err)
            alert("something is fishy")
        })
    }
    return ( 
        <div className="addproduct">
            <form action="" onSubmit={addData}>
                <label htmlFor="">name:</label>
                <input type="text" placeholder="enter name" required value={name} onChange={(e)=>{setname(e.target.value)}} />
                <label htmlFor="">brand:</label>
                <input type="text" required placeholder="enter brand" value={brand} onChange={(e)=>{setbrand(e.target.value)}} />
                <label htmlFor="">category:</label>
                <input type="text" required placeholder="enter category" value={category} onChange={(e)=>{setcategory(e.target.value)}} />
                <label htmlFor="">description:</label>
                <input type="text" placeholder="enter description" required value={description} onChange={(e)=>{setdescription(e.target.value)}} />
                <label htmlFor="">cost:</label>
                <input type="text" placeholder="enter cost" required value={cost} onChange={(e)=>{setcost(e.target.value)}}/>
                <label htmlFor="">image_url:</label>
                <input type="text" required placeholder="enter image url" value={image_url} onChange={(e)=>{setimage_url(e.target.value)}} />
                <button>ADD</button>
            </form>
        </div>
     );
}
 
export default Addproduct;