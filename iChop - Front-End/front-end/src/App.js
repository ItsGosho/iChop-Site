import React, {Fragment} from 'react';
import Footer from "./components/footer/Footer";
import Navbar from "./components/navbar/Navbar";
import TextEditor from "./components/editor/TextEditor";
import ThreadCreate from "./components/thread/ThreadCreate";
import PlayerLinkAccount from "./components/player/PlayerLinkAccount";
import PlayerProfile from "./components/player/PlayerProfile";

function App() {
    return (
        <Fragment>
            <Navbar/>

            <div style={{'marginTop':'75px'}}>
                <PlayerProfile/>
            </div>

            <Footer/>
        </Fragment>
    );
}

export default App;
