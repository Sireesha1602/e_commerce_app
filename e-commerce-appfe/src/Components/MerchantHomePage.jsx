import '../styles/merchanthome.css'
import {Routes,Route} from "react-router-dom"
import MerchantNavbar from './MerchantNavbar';
import ProductView from './ProductView'
import UpdateMerchant from './UpdateMerchant';
import Addproduct from './Addproduct';
const MerchantHomePage = () => {
    return ( 
      
        <div className="mhp">
            <MerchantNavbar/>
            <Routes>
                <Route path="/productview" element={<ProductView/>}/>
                <Route path="/updatemerchant" element={<UpdateMerchant/>}/>
                <Route path="/addproducts" element={<Addproduct/>}/>
            </Routes>
           
        </div> 
          
     );
}
 
export default MerchantHomePage;