import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Link} from "react-router-dom";
import FooterLeftAuthenticatedSide from "../etc/FooterLeftAuthenticatedSide";
import FooterLinksWrapper from "../wrappers/FooterLinksWrapper";
import LinkIconLi from "../../other/LinkIconLi";

class AdminFooter extends Component {


    render() {

        return (
            <Fragment>

                <FooterLinksWrapper text={'VISIT'}>
                    <FooterLeftAuthenticatedSide/>
                </FooterLinksWrapper>


                <FooterLinksWrapper text={'---'}>

                    <LinkIconLi url={RoutingURLs.USER.ALL} icon={'👥'} text={'Users'}/>
                    <LinkIconLi url={RoutingURLs.COMMENT.REPORT.ALL} icon={'⚠'} text={'Reports'}/>
                    <LinkIconLi url={RoutingURLs.THREAD.CREATE} icon={'🚩'} text={'Create Thread'}/>

                </FooterLinksWrapper>
            </Fragment>
        );
    }

}

export default AdminFooter;