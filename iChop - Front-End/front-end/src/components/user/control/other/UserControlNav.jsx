import React from 'react';
import {Link} from "react-router-dom";
import ServerRoutingURLs from "../../../../constants/routing/server.routing.urls";
import RoutingURLs from "../../../../constants/routing/routing.constants";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";
import Image from "../../../other/Image";
import './UserControlNav.css'
import PropTypes from 'prop-types';


const UserControlNav = ({username}) => (
    <nav className="navbar navbar-expand-lg navbar-light bg-dark justify-content-md-center">

        <Image url={ServerRoutingURLs.DATA.USER.AVATAR.GET(username)}
               className="user-nav-picture"
               defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}/>

        <Link className="navbar-brand" to={RoutingURLs.USER.PROFILE.VIEW(username)}>
            <span className="user-username-nav">{username}</span>
        </Link>

    </nav>
);


export default UserControlNav;


UserControlNav.propTypes = {
    username: PropTypes.string
};