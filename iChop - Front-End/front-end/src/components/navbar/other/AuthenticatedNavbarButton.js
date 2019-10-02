import React, {Fragment, Component} from "react";
import FrontEndResourcesRoutingURLs from "../../../constants/front-end.resources.routings";

class AuthenticatedNavbarButton extends Component {

    constructor(props) {
        super(props);

        this.onUserAvatarError = this.onUserAvatarError.bind(this);
    }

    onUserAvatarError(event) {
        event.target.onerror = null;
        event.target.src = FrontEndResourcesRoutingURLs.USER.AVATAR;
    }

    render() {
        let userImage = '';

        return (
            <Fragment>
                <button type="button" className="btn btn-success dropdown-toggle btn-sm"
                        data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                    <img
                        src={userImage}
                        onError={this.onUserAvatarError}
                        alt=''
                        className="img-user_avatar-top_nav_bar"/>
                    <span>⚙</span>
                </button>
            </Fragment>
        );
    }
};


export default AuthenticatedNavbarButton;