import React, {Component, Fragment} from 'react';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";
import FooterSideListWrapper from "../wrappers/FooterSideListWrapper";

class ModeratorFooter extends Component {


    render() {
        let username = '';
        let myProfileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);

        return (
            <Fragment>

                <FooterSideListWrapper text={'VISIT'}>
                    <li>
                        <Link to={myProfileUrl}>
                            <small>👤</small>
                            <span>Profile</span></Link>
                    </li>
                    <li>
                        <Link to={RoutingURLs.USER.OPTIONS.INFORMATION}>
                            <small>⚙</small>
                            <span>Options</span></Link>
                    </li>
                    <li>
                        <Link to={RoutingURLs.THREAD.CREATE}>
                            <small>🚩</small>
                            Create Thread</Link>
                    </li>
                </FooterSideListWrapper>


                <FooterSideListWrapper text={'---'}>

                    <li>
                        <Link to={RoutingURLs.COMMENT.REPORT.ALL}>
                            <small>⚠</small>
                            Reports</Link>
                    </li>

                    <li>
                        <Link to={RoutingURLs.AUTHENTICATION.LOGOUT}>
                            <small>🚪</small>
                            Logout</Link>
                    </li>
                </FooterSideListWrapper>
            </Fragment>
        );
    }

}

export default ModeratorFooter;