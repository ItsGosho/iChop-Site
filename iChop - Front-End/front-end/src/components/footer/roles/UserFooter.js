import React, {Component} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Link} from "react-router-dom";
import FooterLeftAuthenticatedSide from "../etc/FooterLeftAuthenticatedSide";

class UserFooter extends Component {


    render() {

        return (
            <ul className="list-unstyled">
                <FooterLeftAuthenticatedSide/>
            </ul>
        );
    }

}

export default UserFooter;