import UpdateUser from "./UpdateUser";
import{Routes,Route} from "react-router-dom"
import UserNavbar from "./UserNavbar";
const userhomepage = () => {
    return ( 
     <div className="uhp">
           <UserNavbar/>
       <Routes>
           <Route path="/updateuser" element={<UpdateUser/>}></Route>
       </Routes>
     </div>
     );
}
 
export default userhomepage; 
