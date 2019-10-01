import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Link} from "react-router-dom";
import './AuthenticatedNavbar.css'

class AdminNavbar extends Component {


    render() {
        let username = '';
        let avatarUrl = RoutingURLs.USER.AVATAR.replace(':username', username);
        let profileUrl = RoutingURLs.USER.PROFILE.replace(':username', username);

        return (
            <Fragment>
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
                                        src={RoutingURLs.USER.AVATAR}
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
                                    <Link className="dropdown-item"
                                          to={profileUrl}>
                                        <small>👤</small>
                                        <span>Profile</span></Link>
                                    <Link className="dropdown-item" to={RoutingURLs.USER.PROFILE_OPTIONS_INFORMATION}>
                                        <small>⚙</small>
                                        <span>Options</span></Link>
                                    <Link className="dropdown-item" to={RoutingURLs.THREAD.CREATE}>
                                        <small>🚩</small>
                                        Create Thread</Link>
                                    <Link className="dropdown-item" to={RoutingURLs.USER.ALL}>
                                        <small>👥</small>
                                        Users</Link>
                                    <Link className="dropdown-item" to={RoutingURLs.COMMENT.REPORTS_ALL}>
                                        <small>⚠</small>
                                        Reports</Link>
                                    <div className="dropdown-divider"/>
                                    <Link className="dropdown-item" to={RoutingURLs.AUTHENTICATION.LOGOUT}>
                                        <small>🚪</small>
                                        Logout</Link>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </Fragment>
        );
    }

}

export default AdminNavbar;