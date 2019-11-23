import React from "react";
import Image from "../../other/Image";
import FrontEndResourcesRoutingURLs from "../../../constants/front-end.resources.routings";
import withState from "../../../hocs/with.state";
import ServerRoutingURLs from "../../../constants/routing/server.routing.urls";

const NavbarAuthenticatedButton = () => {
    let {username} = this.props.authenticatedUserInfo;

    return (
        <button type="button"
                className="btn btn-success dropdown-toggle btn-sm"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false">

            <Image url={ServerRoutingURLs.DATA.USER.AVATAR.GET(username)}
                   defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                   className="img-user_avatar-top_nav_bar"/>
        </button>
    );
};


export default withState(NavbarAuthenticatedButton);