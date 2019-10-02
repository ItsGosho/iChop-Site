import React, {Component} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Link} from "react-router-dom";
import ServerRoutingURLs from "../../../constants/server.routing.urls";

class UserControlHome extends Component {


    render() {
        let username = 'ItsGosho';
        let userAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);
        let profileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);
        let roleManagementUrl = RoutingURLs.USER.CONTROL_PANEL.ROLE.replace(':username', userAvatarUrl);

        return (
            <div>
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <Link className="navbar-brand" to={profileUrl}>
                        <span>
                          <img src={userAvatarUrl}
                               alt=''
                               style={{'width': '20px', 'height': '20px'}}/>
                        </span>
                        <span>{username}</span></Link>
                    <button className="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"/>
                    </button>

                    <div className="collapse navbar-collapse" id="navbarSupportedContent">

                    </div>
                </nav>

                <div className="container" style={{'margin-left': '0', 'margin-top': '10px'}}>
                    <div className="row">
                        <div className="col-sm">
                            <div className="card" style={{'width': '15rem'}}>
                                <div className="card-header">
                                    Options Menu
                                </div>
                                <ul className="list-group list-group-flush">
                                    <Link to={roleManagementUrl}>
                                        <li className="list-group-item control-option">Role Management</li>
                                    </Link>
                                    <li className="list-group-item control-option">Option2</li>
                                    <li className="list-group-item control-option">Option3</li>
                                </ul>
                            </div>
                        </div>
                        <div className="col-sm">
                            <div className="card">
                                <div className="card-body">

                                    {/*<th:block th:insert="${controlPage}"/>*/}

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                {/*<script>
                    runMenuSelectorColorizer();
                </script>*/}
            </div>
        );
    }

}

export default UserControlHome;