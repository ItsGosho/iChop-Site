import React, {Component} from 'react';
import UserControlInformation from "./information/UserControlInformation";
import UserControlRole from "./role/UserControlRole";
import UserControlNav from "./other/UserControlNav";
import UserControlSidebar from "./other/UserControlSidebar";
import {Route, Switch} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";

class UserControl extends Component {

    render() {

        return (
            <div>
                <UserControlNav/>

                <div className="container" style={{'marginLeft': '0', 'marginTop': '10px'}}>
                    <div className="row">

                        <UserControlSidebar/>

                        <div className="col-sm">
                            <div className="card">
                                <div className="card-body">

                                    <Switch>
                                        <Route exact path={RoutingURLs.USER.CONTROL.INFORMATION} component={() => (<UserControlInformation/>)}/>
                                        <Route exact path={RoutingURLs.USER.CONTROL.ROLE} component={() => (<UserControlRole/>)}/>
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

export default UserControl;