import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Link} from "react-router-dom";
import FooterLeftAuthenticatedSide from "../etc/FooterLeftAuthenticatedSide";
import FooterSideListWrapper from "../wrappers/FooterSideListWrapper";

class AdminFooter extends Component {


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

export default AdminFooter;