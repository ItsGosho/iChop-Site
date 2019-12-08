import React, {Fragment} from 'react';
import Footer from "./components/footer/Footer";
import Navbar from "./components/navbar/Navbar";
import ReactNotification from "react-notifications-component";
import ThreadCreate from "./components/thread/create/ThreadCreate";
import UserProfile from "./components/user/profile/UserProfile";
import UserOptions from "./components/user/options/UserOptions";
import {Switch} from "react-bootstrap";
import {Route} from "react-router-dom";
import RoutingURLs from "./constants/routing/routing.constants";
import Logout from "./components/navbar/components/Logout";
import UserChangePasswordByToken from "./components/user/other/UserChangePasswordByToken";
import PrefixURLs from "./constants/routing/prefix.routing.constants";
import ThreadsAll from "./components/thread/all/ThreadsAll";
import ThreadRead from "./components/thread/read/ThreadRead";
import UserControl from "./components/user/control/UserControl";
import Reports from "./components/report/Reports";
import PlayerLinkAccount from "./components/player/PlayerLinkAccount";


const App = () => (
    <Fragment>
        <Navbar/>

        <ReactNotification/>

        <div className="holder">
            <Switch>

                <Route exact path={RoutingURLs.HOME} render={() => (<ThreadsAll/>)}/>
                <Route exact path={RoutingURLs.AUTHENTICATION.TOKEN.RESET_PASSWORD}
                       render={() => (<UserChangePasswordByToken/>)}/>
                <Route exact path={RoutingURLs.USER.PROFILE.VIEW(':username')} render={key(UserProfile, 'username')}/>

                <Route path={PrefixURLs.USER_CONTROL_PREFIX(':username')} render={key(UserControl, 'username')}/>
                <Route path={PrefixURLs.OPTIONS_PREFIX} render={() => (<UserOptions/>)}/>
                <Route path={RoutingURLs.REPORT.ALL} render={() => (<Reports/>)}/>

                <Route exact path={RoutingURLs.THREAD.READ(':id')} render={key(ThreadRead, 'id')}/>
                <Route exact path={RoutingURLs.THREAD.CREATE} render={() => (<ThreadCreate/>)}/>
                <Route exact path={RoutingURLs.PLAYER.ACCOUNT.LINK} render={() => (<PlayerLinkAccount/>)}/>
                <Route exact path={RoutingURLs.AUTHENTICATION.LOGOUT} render={() => (<Logout/>)}/>
            </Switch>
        </div>

        <Footer/>
    </Fragment>
);

export default App;

const key = (Component, key) => {
    return (props) => (<Component key={props.match.params[key]} {...props}/>)
};