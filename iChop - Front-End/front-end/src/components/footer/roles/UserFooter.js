import React, {Component} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Link} from "react-router-dom";
import FooterLeftAuthenticatedSide from "../etc/FooterLeftAuthenticatedSide";
import FooterSideListWrapper from "../hoc/FooterSideListWrapper";

class UserFooter extends Component {


    render() {

        return (
            <FooterSideListWrapper text={'VISIT'}>
                <FooterLeftAuthenticatedSide/>
            </FooterSideListWrapper>
        );
    }

}

export default UserFooter;