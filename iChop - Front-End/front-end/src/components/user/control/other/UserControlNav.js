import React, {Component} from 'react';
import {Link} from "react-router-dom";
import ServerRoutingURLs from "../../../../constants/server.routing.urls";
import RoutingURLs from "../../../../constants/routing.constants";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";
import Image from "../../../other/Image";
import './UserControlNav.css'

class UserControlNav extends Component {

    constructor(props) {
        super(props);

        this.onUserAvatarError = this.onUserAvatarError.bind(this);
    }

    onUserAvatarError(event) {
        event.target.onerror = null;
        event.target.src = FrontEndResourcesRoutingURLs.USER.AVATAR;
    }

    render() {
        let username = 'ItsGosho';
        let userAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);
        let profileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);

        return (
            <nav className="navbar navbar-expand-lg navbar-light bg-dark justify-content-md-center">

                <Image url={userAvatarUrl}
                       className="user-nav-picture"
                       defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}/>

                <Link className="navbar-brand" to={profileUrl}>
                    <span className="user-username-nav">{username}</span>
                </Link>

            </nav>
        );
    }

}

export default UserControlNav;