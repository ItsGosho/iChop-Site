import React, {Component} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Link} from "react-router-dom";
import './AuthenticatedNavbar.css'

class UserNavbar extends Component {

    constructor(props) {
        super(props);

        this.state = {
            userImageDefaultPath: '/img/avatar-user.png'
        };

        this.onImageError = this.onImageError.bind(this);
    }

    onImageError(event) {
        event.target.onerror = null;
        this.setState({userImageDefaultPath: '/img/avatar-user.png'});
    }

    componentDidMount() {
        this.setState({userImageDefaultPath: 'https://staticassets.hypixel.net/news/5d793c5292000.skyblock%200.7.1.png'})
    }

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
                                    src={this.state.userImageDefaultPath}
                                    onError={this.onImageError}
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