import React, {Component} from 'react';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../../constants/routing.constants";

class UserControlSidebar extends Component {

    render() {
        let username = 'ItsGosho';

        let informationUrl = RoutingURLs.USER.CONTROL.INFORMATION.replace(':username', username);
        let roleUrl = RoutingURLs.USER.CONTROL.ROLE.replace(':username', username);

        return (
            <div className="col-sm">
                <div className="card" style={{'width': '15rem'}}>
                    <div className="card-header">Options Menu</div>
                    <ul className="list-group list-group-flush">

                        <Link to={informationUrl}>
                            <li className="list-group-item control-option">Information</li>
                        </Link>

                        <Link to={roleUrl}>
                            <li className="list-group-item control-option">Role Management</li>
                        </Link>

                    </ul>
                </div>
            </div>
        );
    }

}

export default UserControlSidebar;