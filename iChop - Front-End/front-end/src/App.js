import React, {Fragment} from 'react';
import Footer from "./components/footer/Footer";
import Navbar from "./components/navbar/Navbar";
import ReactNotification from "react-notifications-component";
import TextEditor from "./components/editor/TextEditor";
import ThreadCreate from "./components/thread/create/ThreadCreate";
import PlayerLinkAccount from "./components/player/PlayerLinkAccount";
import PlayerProfile from "./components/player/PlayerProfile";
import Reports from "./components/report/Reports";
import ThreadRead from "./components/thread/read/ThreadRead";
import ThreadsAll from "./components/thread/all/ThreadsAll";
import TextEditorInsertImageModal from "./components/editor/fragments/TextEditorInsertImageModal";
import ThreadCreateHelpModal from "./components/thread/create/ThreadCreateHelpModal";
import UserProfile from "./components/user/profile/UserProfile";
import UserOptions from "./components/user/options/UserOptions";
import UserControl from "./components/user/control/UserControl";
import {Switch} from "react-bootstrap";
import {Route} from "react-router-dom";
import RoutingURLs from "./constants/routing.constants";

function App() {



    return (
        <Fragment>
            <Navbar/>

            <ReactNotification/>

            {/*<div style={{'marginTop': '75px'}}>

                <Switch>
                    <Route exact path={RoutingURLs.USER.CONTROL.INFORMATION} render={({match})=>(<UserControl route={match}/>)}/>
                    <Route exact path={RoutingURLs.USER.CONTROL.ROLE} render={({match})=>(<UserControl route={match}/>)}/>
                </Switch>

            </div>*/}

            <ThreadsAll/>

            <Footer/>
        </Fragment>
    );
}

export default App;
