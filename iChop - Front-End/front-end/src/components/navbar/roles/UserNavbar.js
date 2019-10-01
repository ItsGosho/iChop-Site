import React, {Component} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Link} from "react-router-dom";
import './AuthenticatedNavbar.css'

class UserNavbar extends Component {


    render() {
        let username = '';
        let avatarUrl = RoutingURLs.USER.AVATAR.replace(':username', username);
        let profileUrl = RoutingURLs.USER.PROFILE.replace(':username', username);

        return (
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">

                </ul>
                <ul className="navbar-nav ml-auto">
                    <li className="nav-item dropdown active">
                        <div id="userDiv">
                            <button type="button" className="btn btn-success dropdown-toggle btn-sm"
                                    data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">
                                <img
                                    src={avatarUrl}
                                    onError={
                                        (e) => {
                                            console.log(123);
                                            e.target.onerror = null;
                                            e.target.src = "/img/avatar-user.png"
                                        }
                                    }
                                    className="img-user_avatar-top_nav_bar"/>
                                <span>⚙</span>
                            </button>
                            <div className="dropdown-menu dropdown-menu-right">
                                <Link className="dropdown-item" to={profileUrl}>
                                    <small>👤</small>
                                    <span>Profile</span></Link>
                                <Link className="dropdown-item" to={RoutingURLs.USER.PROFILE_OPTIONS_INFORMATION}>
                                    <small>⚙</small>
                                    <span>Options</span></Link>
                                <div className="dropdown-divider"/>
                                <Link className="dropdown-item" to={RoutingURLs.AUTHENTICATION.LOGOUT}>
                                    <small>🚪</small>
                                    Logout</Link>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        );
    }

}

export default UserNavbar;