import React, {Fragment, Component} from "react";
import FrontEndResourcesRoutingURLs from "../../../constants/front-end.resources.routings";

class AuthenticatedNavbarButton extends Component {

    constructor(props) {
        super(props);

        this.state = {
            avatarUrl: FrontEndResourcesRoutingURLs.USER.AVATAR,
            avatarError: false
        };

        this.onUserAvatarError = this.onUserAvatarError.bind(this);
        this.onLoad = this.onLoad.bind(this);
    }

    onUserAvatarError(event) {
        this.setState({avatarError: true});
        event.target.onerror = null;
        event.target.src = FrontEndResourcesRoutingURLs.USER.AVATAR;
    }

    onLoad() {
        this.setState({avatarUrl: 'https://staticassets.hypixel.net/news1/5d9ff5758dd99.skyblock%200.7.2.png'})
    }

    render() {

        return (
            <Fragment>
                <button type="button" className="btn btn-success dropdown-toggle btn-sm"
                        data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                    <img
                        src={this.state.avatarUrl}
                        onError={this.onUserAvatarError}
                        onLoad={this.onLoad}
                        alt=''
                        className="img-user_avatar-top_nav_bar"/>
                    <span>⚙</span>
                </button>
            </Fragment>
        );
    }
};


export default AuthenticatedNavbarButton;