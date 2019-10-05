import React, {Fragment} from 'react';
import Footer from "./components/footer/Footer";
import Navbar from "./components/navbar/Navbar";
import TextEditor from "./components/editor/TextEditor";

function App() {
    return (
        <Fragment>
            <Navbar/>

            <TextEditor/>

            <Footer/>
        </Fragment>
    );
}

export default App;
