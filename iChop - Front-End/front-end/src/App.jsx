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

function App() {


    return (
        <Fragment>
            <Navbar/>

            <ReactNotification/>

            <div style={{'marginTop': '75px'}}>
                <Switch>
                    <Route exact path={RoutingURLs.HOME} render={() => (<ThreadsAll/>)}/>

                    <Route exact path={RoutingURLs.AUTHENTICATION.TOKEN.RESET_PASSWORD}
                           render={() => (<UserChangePasswordByToken/>)}/>

                    <Route exact path={RoutingURLs.USER.PROFILE.VIEW(':username')}
                           render={(props) => (<UserProfile key={props.match.params.username} {...props}/>)}/>

                    <Route path={PrefixURLs.OPTIONS_PREFIX} render={() => (<UserOptions/>)}/>


                    <Route exact path={RoutingURLs.THREAD.READ(':id')}
                           render={(props) => (<ThreadRead key={props.match.params.id} {...props}/>)}/>

                    <Route exact path={RoutingURLs.THREAD.CREATE} render={() => (<ThreadCreate/>)}/>

                    <Route exact path={RoutingURLs.AUTHENTICATION.LOGOUT} render={() => (<Logout/>)}/>
                </Switch>
            </div>

            {/*<div style={{'marginTop': '75px'}}>

                <Switch>
                    <Route exact path={RoutingURLs.USER.CONTROL.INFORMATION} render={({match})=>(<UserControl route={match}/>)}/>
                    <Route exact path={RoutingURLs.USER.CONTROL.ROLE} render={({match})=>(<UserControl route={match}/>)}/>
                </Switch>

            </div>*/}

            <Footer/>
        </Fragment>
    );
}

export default App;
