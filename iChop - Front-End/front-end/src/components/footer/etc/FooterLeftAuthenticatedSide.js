import React, {Component, Fragment} from 'react';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";
import LinkIcon from "../../other/LinkIcon";
import LinkIconLi from "../../other/LinkIconLi";

class FooterLeftAuthenticatedSide extends Component {


    render() {
        let username = '';
        let myProfileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);

        return (
            <Fragment>

                <LinkIconLi url={myProfileUrl} icon={'👤'} text={'Profile'}/>
                <LinkIconLi url={RoutingURLs.USER.OPTIONS.INFORMATION} icon={'⚙'} text={'Options'}/>
                <LinkIconLi url={RoutingURLs.AUTHENTICATION.LOGOUT} icon={'🚪'} text={'Logout'}/>

            </Fragment>
        );
    }

}

export default FooterLeftAuthenticatedSide;