import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../../constants/routing.constants";
import {Link} from "react-router-dom";
import '../NavbarAuthenticated.css'
import NavbarAuthenticatedButton from "../../other/NavbarAuthenticatedButton";
import NavbarAuthenticatedList from "../../other/NavbarAuthenticatedList";

class NavbarAdmin extends Component {


    render() {

        return (
            <Fragment>

                <NavbarAuthenticatedButton/>

                <NavbarAuthenticatedList>

                    <DropDownLink to={RoutingURLs.THREAD.CREATE} icon={'🚩'} text={'Create Thread'}/>
                    <DropDownLink to={RoutingURLs.USER.ALL} icon={'👥'} text={'Users'}/>
                    <DropDownLink to={RoutingURLs.COMMENT.REPORT.ALL} icon={'⚠'} text={'Reports'}/>

                </NavbarAuthenticatedList>

            </Fragment>
        );
    }

}

const DropDownLink = (props) => {
    let {to, icon, text} = props;

    return (
        <Link className="dropdown-item" to={to}>
            <small>{icon}</small>
            {text}
        </Link>
    );
};

export default NavbarAdmin;