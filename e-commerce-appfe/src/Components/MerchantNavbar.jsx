import {Link} from "react-router-dom"
import Dropdown from 'react-bootstrap/Dropdown';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import LanguageRoundedIcon from '@mui/icons-material/LanguageRounded';
import '../styles/merchantnavbar.css'
const MerchantNavbar = () => {
    return ( 
        <nav className="merchantnavbar">
            <div className="logo">
                <h1>Shopping W<LanguageRoundedIcon/>rld</h1>
            </div>
            <div className="option">
                <Link  to="/merchanthomepage/productview">View products</Link>
                <Link to="/merchanthomepage/addproducts">Add Products</Link>
            </div>
            <div className="account">
          <Dropdown className="mdrop">
              <Dropdown.Toggle variant="dark" id="dropdown-basic">
                <AccountCircleIcon/> Account
              </Dropdown.Toggle>

            <Dropdown.Menu>
             <Dropdown.Item href="/merchanthomepage/updatemerchant">Edit Account</Dropdown.Item>
             <Dropdown.Item href="/">Logout</Dropdown.Item>
            
             </Dropdown.Menu>
         </Dropdown>
            </div>
        </nav>
     );
}
 
export default MerchantNavbar;