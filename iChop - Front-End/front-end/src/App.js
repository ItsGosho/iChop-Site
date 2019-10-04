import React, {Fragment} from 'react';
import Footer from "./components/footer/Footer";
import Navbar from "./components/navbar/Navbar";
import UserOptions from "./components/user/options/UserOptions";
import UserProfile from "./components/user/profile/UserProfile";

function App() {
    return (
        <Fragment>
            <Navbar/>

            <UserProfile/>

            <Footer/>
        </Fragment>
    );
}

export default App;
