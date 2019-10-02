import React, {Fragment} from 'react';
import Footer from "./components/footer/Footer";
import Navbar from "./components/navbar/Navbar";
import UserOptions from "./components/user/options/UserOptions";

function App() {
    return (
        <Fragment>
            <Navbar/>

            <h1>TEST</h1>
            <UserOptions/>

            <Footer/>
        </Fragment>
    );
}

export default App;
