import React, {Fragment} from 'react';
import Footer from "./components/footer/Footer";
import Navbar from "./components/navbar/Navbar";
import TextEditor from "./components/editor/TextEditor";
import ThreadCreate from "./components/thread/ThreadCreate";

function App() {
    return (
        <Fragment>
            <Navbar/>

            <div style={{'marginTop':'75px'}}>
                <ThreadCreate/>
            </div>

            <Footer/>
        </Fragment>
    );
}

export default App;
