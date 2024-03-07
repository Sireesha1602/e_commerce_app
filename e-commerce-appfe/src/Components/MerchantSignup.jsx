import { Form } from "react-router-dom";
import '../styles/merchantsignup.css'
const MerchantSignup = () => {
    return ( 
        <Form>
            <label htmlFor="">Name</label>
            <input type="text" placeholder="enter name" required></input>
            <label htmlFor="">Phone</label>
            <input type="tel" placeholder="enter phone" required />
            <label htmlFor="">gst_number</label>
            <input type="text" placeholder="enetr gst number" required />
            <label htmlFor="">Email</label>
            <input type="email" placeholder="enter email" required />
            <label htmlFor="">Password</label>
            <input type="password" placeholder="enter password" required />
        </Form>
     );
}
 
export default MerchantSignup;