import React, {Fragment, Component} from "react";
import FrontEndResourcesRoutingURLs from "../../../constants/front-end.resources.routings";

class AuthenticatedNavbarButton extends Component {

    constructor(props) {
        super(props);
        this.state = {
            userImageDefaultPath: FrontEndResourcesRoutingURLs.USER.AVATAR
        };

        this.onImageError = this.onImageError.bind(this);
    }

    onImageError(event) {
        event.target.onerror = null;
        this.setState({userImageDefaultPath: FrontEndResourcesRoutingURLs.USER.AVATAR});
    }

    componentDidMount() {
        this.setState({userImageDefaultPath: 'https://staticassets.hypixel.net/news/5d793c5292000.skyblock%200.7.1.png'})
    }

    render() {
        return (
            <Fragment>
                <button type="button" className="btn btn-success dropdown-toggle btn-sm"
                        data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                    <img
                        src={this.state.userImageDefaultPath}
                        onError={this.onImageError}
                        className="img-user_avatar-top_nav_bar"/>
                    <span>⚙</span>
                </button>
            </Fragment>
        );
    }
};


export default AuthenticatedNavbarButton;