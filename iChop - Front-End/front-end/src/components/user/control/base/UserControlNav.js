import React,{Component} from 'react';
import {Link} from "react-router-dom";
import ServerRoutingURLs from "../../../../constants/server.routing.urls";
import RoutingURLs from "../../../../constants/routing.constants";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";

class UserControlNav extends Component {

    constructor(props) {
        super(props);

        this.onImageError = this.onImageError.bind(this);
    }

    onImageError(event) {
        event.target.onerror = null;
        event.target.src = FrontEndResourcesRoutingURLs.USER.AVATAR;
    }

    render() {
        let username = 'ItsGosho';
        let userAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);
        let profileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);

        return (
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <Link className="navbar-brand" to={profileUrl}>
                        <span>
                          <img src={userAvatarUrl} onError={this.onImageError}
                               style={{'width': '20px', 'height': '20px'}}/>
                        </span>
                    <span>{username}</span>
                </Link>
            </nav>
        );
    }

}

export default UserControlNav;