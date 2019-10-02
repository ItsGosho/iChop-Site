import React, {Fragment} from 'react';
import Footer from "./components/footer/Footer";
import Navbar from "./components/navbar/Navbar";
import UserControlHome from "./components/user/control/UserControlHome";

function App() {
    return (
        <Fragment>
            <Navbar/>

            <h1>TEST</h1>
            <UserControlHome/>

            <Footer/>
        </Fragment>
    );
}

export default App;
