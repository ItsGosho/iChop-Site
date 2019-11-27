import React, {Fragment} from 'react';
import RoutingURLs from "../../../constants/routing/routing.constants";
import FooterAuthenticatedLinks from "../components/FooterAuthenticatedLinks";
import FooterLinksWrapper from "../components/FooterLinksWrapper";
import LinkIconLi from "../../other/LinkIconLi";

const AdminFooter = () => (
    <Fragment>

        <FooterLinksWrapper text={'VISIT'}>
            <FooterAuthenticatedLinks/>
        </FooterLinksWrapper>


        <FooterLinksWrapper text={'---'}>

            <LinkIconLi url={RoutingURLs.USER.ALL} icon={'👥'} text={'Users'}/>
            <LinkIconLi url={RoutingURLs.COMMENT.REPORT.ALL} icon={'⚠'} text={'Reports'}/>
            <LinkIconLi url={RoutingURLs.THREAD.CREATE} icon={'🚩'} text={'Create Thread'}/>

        </FooterLinksWrapper>
    </Fragment>
);

export default AdminFooter;