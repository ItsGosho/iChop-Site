import React from 'react';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../../constants/routing/routing.constants";
import './UserControlSidebar.css'
import PropTypes from 'prop-types';


const UserControlSidebar = ({username}) => (
    <div className="col-sm">
        <div className="card card-holder">
            <div className="card-header">Options Menu</div>
            <ul className="list-group list-group-flush">

                <Link to={RoutingURLs.USER.CONTROL.INFORMATION(username)}>
                    <li className="list-group-item control-option">Information</li>
                </Link>

                <Link to={RoutingURLs.USER.CONTROL.ROLE(username)}>
                    <li className="list-group-item control-option">Role Management</li>
                </Link>

            </ul>
        </div>
    </div>
);

export default UserControlSidebar;

UserControlSidebar.propTypes = {
    username: PropTypes.string
};