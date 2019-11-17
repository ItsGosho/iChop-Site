import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../constants/routing/routing.constants";
import FooterLinksWrapper from "../etc/FooterLinksWrapper";
import LinkIconLi from "../../other/LinkIconLi";

class ModeratorFooter extends Component {


    render() {
        let username = '';
        let myProfileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);

        return (
            <Fragment>

                <FooterLinksWrapper text={'VISIT'}>

                    <LinkIconLi url={myProfileUrl} icon={'👤'} text={'Profile'}/>
                    <LinkIconLi url={RoutingURLs.USER.OPTIONS.INFORMATION} icon={'⚙'} text={'Options'}/>
                    <LinkIconLi url={RoutingURLs.THREAD.CREATE} icon={'🚩'} text={'Create Thread'}/>

                </FooterLinksWrapper>


                <FooterLinksWrapper text={'---'}>

                    <LinkIconLi url={RoutingURLs.COMMENT.REPORT.ALL} icon={'⚠'} text={'Reports'}/>
                    <LinkIconLi url={RoutingURLs.AUTHENTICATION.LOGOUT} icon={'🚪'} text={'Logout'}/>

                </FooterLinksWrapper>
            </Fragment>
        );
    }

}

export default ModeratorFooter;