import React, {Fragment} from 'react';
import RoutingURLs from "../../../constants/routing/routing.constants";
import FooterLinksWrapper from "../components/FooterLinksWrapper";
import LinkIconLi from "../../other/LinkIconLi";
import withState from "../../../hocs/with.state";

const ModeratorFooter = (props) => {
    let {username} = props.authenticatedUserInfo;
    let myProfileUrl = RoutingURLs.USER.PROFILE.VIEW(username);

    return (
        <Fragment>

            <FooterLinksWrapper text={'VISIT'}>

                <LinkIconLi url={myProfileUrl} icon={'👤'} text={'Profile'}/>
                <LinkIconLi url={RoutingURLs.USER.OPTIONS.INFORMATION} icon={'⚙'} text={'Options'}/>
                <LinkIconLi url={RoutingURLs.THREAD.CREATE} icon={'🚩'} text={'Create Thread'}/>

            </FooterLinksWrapper>


            <FooterLinksWrapper text={'---'}>

                <LinkIconLi url={RoutingURLs.REPORT.ALL} icon={'⚠'} text={'Reports'}/>
                <LinkIconLi url={RoutingURLs.AUTHENTICATION.LOGOUT} icon={'🚪'} text={'Logout'}/>

            </FooterLinksWrapper>
        </Fragment>
    );
};

export default withState(ModeratorFooter);