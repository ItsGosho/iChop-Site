import React, {Component} from 'react';
import UserControlInformation from "./information/UserControlInformation";
import UserControlRole from "./role/UserControlRole";
import UserControlNav from "./other/UserControlNav";
import UserControlSidebar from "./other/UserControlSidebar";
import {Redirect, Route, Switch, withRouter} from "react-router-dom";
import RoutingURLs from "../../../constants/routing/routing.constants";
import PrefixURLs from "../../../constants/routing/prefix.routing.constants";

class UserControl extends Component {

    render() {
        let {username} = this.props.match.params;

        return (
            <div>
                <UserControlNav username={username}/>

                <div className="container" style={{'marginLeft': '0', 'marginTop': '10px'}}>
                    <div className="row">

                        <UserControlSidebar username={username}/>

                        <div className="col-sm">
                            <div className="card">
                                <div className="card-body">

                                    <Switch>
                                        <Route exact path={PrefixURLs.USER_CONTROL_PREFIX(':username')}
                                               render={() => (<Redirect to={RoutingURLs.USER.CONTROL.INFORMATION(username)}/>)}/>

                                        <Route exact path={RoutingURLs.USER.CONTROL.INFORMATION(':username')}
                                               component={() => (<UserControlInformation username={username}/>)}/>
                                        <Route exact path={RoutingURLs.USER.CONTROL.ROLE(':username')}
                                               component={() => (<UserControlRole username={username}/>)}/>
                                    </Switch>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default withRouter(UserControl);