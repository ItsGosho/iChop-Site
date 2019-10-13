import React, {Fragment} from 'react';
import Footer from "./components/footer/Footer";
import Navbar from "./components/navbar/Navbar";
import TextEditor from "./components/editor/TextEditor";
import ThreadCreate from "./components/thread/create/ThreadCreate";
import PlayerLinkAccount from "./components/player/PlayerLinkAccount";
import PlayerProfile from "./components/player/PlayerProfile";
import Reports from "./components/report/Reports";
import ThreadRead from "./components/thread/read/ThreadRead";
import ThreadAll from "./components/thread/read/all/ThreadAll";
import TextEditorInsertImageModal from "./components/editor/fragments/TextEditorInsertImageModal";
import ThreadCreateHelpModal from "./components/thread/create/ThreadCreateHelpModal";
import UserProfile from "./components/user/profile/UserProfile";
import UserOptions from "./components/user/options/UserOptions";
import UserControl from "./components/user/control/UserControl";

function App() {
    return (
        <Fragment>
            <Navbar/>

            <div style={{'marginTop': '75px'}}>
                <UserControl/>
            </div>

            <Footer/>
        </Fragment>
    );
}

export default App;
