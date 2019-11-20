import React from 'react';
import Image from "../../../other/Image";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";
import {Link} from "react-router-dom";
import CreateReactClass from "create-react-class";
import RoutingURLs from "../../../../constants/routing/routing.constants";
import ServerRoutingURLs from "../../../../constants/routing/server.routing.urls";

const UserFollowModalBaseRow = CreateReactClass({


    render() {
        let {username,key} = this.props;

        let profileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);
        let avatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);

        return (
            <div className="w-100 div-follow-holder" key={key}>

                <Image url={avatarUrl} defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR} className="div-follow-image"/>

                <span className="span-follow-username">
                    <b><Link to={profileUrl}>{username}</Link></b>
                </span>

                {this.props.children}
            </div>
        );
    }

});

export default UserFollowModalBaseRow;