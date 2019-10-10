import React, {Component, Fragment} from 'react';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";
import FooterSideListWrapper from "../wrappers/FooterSideListWrapper";
import LinkIconLi from "../../other/LinkIconLi";

class ModeratorFooter extends Component {


    render() {
        let username = '';
        let myProfileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);

        return (
            <Fragment>

                <FooterSideListWrapper text={'VISIT'}>

                    <LinkIconLi url={myProfileUrl} icon={'👤'} text={'Profile'}/>
                    <LinkIconLi url={RoutingURLs.USER.OPTIONS.INFORMATION} icon={'⚙'} text={'Options'}/>
                    <LinkIconLi url={RoutingURLs.THREAD.CREATE} icon={'🚩'} text={'Create Thread'}/>

                </FooterSideListWrapper>


                <FooterSideListWrapper text={'---'}>

                    <LinkIconLi url={RoutingURLs.COMMENT.REPORT.ALL} icon={'⚠'} text={'Reports'}/>
                    <LinkIconLi url={RoutingURLs.AUTHENTICATION.LOGOUT} icon={'🚪'} text={'Logout'}/>
                    
                </FooterSideListWrapper>
            </Fragment>
        );
    }

}

export default ModeratorFooter;