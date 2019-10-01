import React,{Component} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Link} from "react-router-dom";

class UserFooter extends Component {


    render() {
        let username = '';
        let myProfileUrl = RoutingURLs.USER.PROFILE.replace(':username', username);

        return (
            <div className="col-md-2 mx-auto">
                <h5 className="font-weight-bold text-uppercase mt-3 mb-4">---</h5>
                <ul className="list-unstyled">
                    <li>
                        <Link to={myProfileUrl}>
                            <small>👤</small>
                            <span>Profile</span></Link>
                    </li>
                    <li>
                        <Link to={RoutingURLs.USER.PROFILE_OPTIONS_INFORMATION}>
                            <small>⚙</small>
                            <span>Options</span></Link>
                    </li>
                    <li>
                        <Link to={RoutingURLs.AUTHENTICATION.LOGOUT}>
                            <small>🚪</small>
                            Logout</Link>
                    </li>
                </ul>

            </div>
        );
    }

}

export default UserFooter;