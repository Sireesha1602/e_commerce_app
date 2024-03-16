import {useState,useEffect} from "react"
import axios from "axios"
import '../styles/productview.css'
const ProductView = () => {
 let [data,setdata]=useState([])

 useEffect(()=>{
         axios.get('http://localhost:8080/products')
         .then((res)=>{
            console.log(res)
            setdata(res.data.data);
         })
         .catch((err)=>{
            console.log(err.data);
         })
 },[])
    return ( 
      <div className="viewproducts">
           <marquee behavior="alternate" direction="right"><h1>Products are here!!!</h1></marquee>
           {data.map((x)=>{
             return(
               <div className="products">
                   <div className="view">
                      <img src={x.image_url} alt="" />
                   </div>
                   <div className="desc">
                     <h2>{x.name}</h2>
                     <h3>{x.category}</h3>
                     <p><b>Description:{x.description}</b></p>
                     <p>Cost:{x.cost}</p>
                   </div>
               </div>
             )
           })}
      </div>
      
        

     );
}
 
export default ProductView;