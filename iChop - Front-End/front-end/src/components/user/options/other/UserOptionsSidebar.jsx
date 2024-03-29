import React from 'react';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../../constants/routing/routing.constants";
import './UserOptionsSidebar.css'

const UserOptionsSidebar = () => (
    <div className="col-sm">
        <div className="card card-navigation">

            <div className="card-header">
                Options Menu
            </div>

            <ul className="list-group list-group-flush">

                <Link to={RoutingURLs.USER.OPTIONS.INFORMATION}>
                    <li className="list-group-item control-option">Information</li>
                </Link>

                <Link to={RoutingURLs.USER.OPTIONS.PASSWORD}>
                    <li className="list-group-item control-option">Change Password</li>
                </Link>

                <Link to={RoutingURLs.USER.OPTIONS.MINECRAFT}>
                    <li className="list-group-item control-option">Minecraft</li>
                </Link>

            </ul>
        </div>
    </div>
);

export default UserOptionsSidebar;