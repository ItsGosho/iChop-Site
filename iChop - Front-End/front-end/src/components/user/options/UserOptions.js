import React, {Component} from 'react';
import UserOptionsSidebar from "./other/UserOptionsSidebar";
import UserOptionsMinecraft from "./minecraft/UserOptionsMinecraft";
import UserOptionsChangePassword from "./password/UserOptionsChangePassword";
import UserOptionsInformation from "./information/UserOptionsInformation";
import {Redirect, Route, Switch} from "react-router-dom";
import RoutingURLs from "../../../constants/routing/routing.constants";
import PrefixURLs from "../../../constants/routing/prefix.routing.constants";

class UserOptions extends Component {


    render() {
        return (
            <div className="container" style={{'marginLeft': '0'}}>
                <div className="row">

                    <UserOptionsSidebar/>

                    <div className="col-sm">
                        <div className="card">
                            <div className="card-body">

                                <Switch>
                                    <Route exact path={PrefixURLs.OPTIONS_PREFIX} render={() => (<Redirect to={RoutingURLs.USER.OPTIONS.INFORMATION}/>)}/>

                                    <Route exact path={RoutingURLs.USER.OPTIONS.INFORMATION} component={() => (<UserOptionsInformation/>)}/>
                                    <Route exact path={RoutingURLs.USER.OPTIONS.PASSWORD} component={() => (<UserOptionsChangePassword/>)}/>
                                    <Route exact path={RoutingURLs.USER.OPTIONS.MINECRAFT} component={() => (<UserOptionsMinecraft/>)}/>
                                </Switch>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default UserOptions;