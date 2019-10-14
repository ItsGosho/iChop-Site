import React from "react";
import CreateReactClass from 'create-react-class';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";

let NavbarAuthenticatedList = CreateReactClass({

    render() {
        let username = '';

        let profileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);
        let informationUrl = RoutingURLs.USER.OPTIONS.INFORMATION;
        let logoutUrl = RoutingURLs.AUTHENTICATION.LOGOUT;

        return (
            <div className="dropdown-menu dropdown-menu-right">

                <DropDownLink to={profileUrl} icon={'👤'} text={'Profile'}/>
                <DropDownLink to={informationUrl} icon={'⚙'} text={'Options'}/>

                {this.props.children}

                <div className="dropdown-divider"/>

                <DropDownLink to={logoutUrl} icon={'🚪'} text={'Logout'}/>
            </div>
        );
    }

});

const DropDownLink = (props) => {
    let {to, icon, text} = props;

    return (
        <Link className="dropdown-item" to={to}>
            <small>{icon}</small>
            {text}
        </Link>
    );
};

export default NavbarAuthenticatedList;