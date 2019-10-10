import React, {Component, Fragment} from 'react';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";
import FooterLeftAuthenticatedSide from "../etc/FooterLeftAuthenticatedSide";
import FooterSideListWrapper from "../wrappers/FooterSideListWrapper";
import LinkIconLi from "../../other/LinkIconLi";

class OwnerFooter extends Component {


    render() {

        return (
            <Fragment>


                <FooterSideListWrapper text={'VISIT'}>
                    <FooterLeftAuthenticatedSide/>
                </FooterSideListWrapper>


                <FooterSideListWrapper text={'---'}>

                    <LinkIconLi url={RoutingURLs.USER.ALL} icon={'👥'} text={'Users'}/>
                    <LinkIconLi url={RoutingURLs.COMMENT.REPORT.ALL} icon={'⚠'} text={'Reports'}/>
                    <LinkIconLi url={RoutingURLs.THREAD.CREATE} icon={'🚩'} text={'Create Thread'}/>

                </FooterSideListWrapper>
            </Fragment>
        );
    }

}

export default OwnerFooter;