import React, {Component, Fragment} from 'react';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";
import FooterLeftAuthenticatedSide from "../etc/FooterLeftAuthenticatedSide";
import FooterSideListWrapper from "../hoc/FooterSideListWrapper";

class OwnerFooter extends Component {


    render() {

        return (
            <Fragment>


                <FooterSideListWrapper text={'VISIT'}>
                    <FooterLeftAuthenticatedSide/>
                </FooterSideListWrapper>


                <FooterSideListWrapper text={'---'}>

                    <li>
                        <Link to={RoutingURLs.USER.ALL}>
                            <small>👥</small>
                            Users</Link>
                    </li>
                    <li>
                        <Link to={RoutingURLs.COMMENT.REPORTS_ALL}>
                            <small>⚠</small>
                            Reports</Link>
                    </li>
                    <li>
                        <Link to={RoutingURLs.THREAD.CREATE}>
                            <small>🚩</small>
                            Create Thread</Link>
                    </li>

                </FooterSideListWrapper>
            </Fragment>
        );
    }

}

export default OwnerFooter;