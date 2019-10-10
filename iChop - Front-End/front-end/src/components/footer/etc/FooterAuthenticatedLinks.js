import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import LinkIconLi from "../../other/LinkIconLi";

class FooterAuthenticatedLinks extends Component {


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

export default FooterAuthenticatedLinks;