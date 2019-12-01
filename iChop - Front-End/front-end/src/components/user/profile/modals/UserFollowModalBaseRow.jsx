import React from 'react';
import Image from "../../../other/Image";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";
import {Link} from "react-router-dom";
import RoutingURLs from "../../../../constants/routing/routing.constants";
import ServerRoutingURLs from "../../../../constants/routing/server.routing.urls";

const UserFollowModalBaseRow = ({username,key,children}) => (
    <div className="w-100 div-follow-holder" key={key}>

        <Image url={ServerRoutingURLs.CORE.DATA_STORAGE.GET_USER_AVATAR(username)}
               defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
               className="div-follow-image"/>

        <span className="span-follow-username">
                    <b><Link to={RoutingURLs.USER.PROFILE.VIEW(username)}>{username}</Link></b>
                </span>

        {children}
    </div>
);

export default UserFollowModalBaseRow;