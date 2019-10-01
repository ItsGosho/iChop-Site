import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Link} from "react-router-dom";

class AdminFooter extends Component {


    render() {
        let username = '';
        let myProfileUrl = RoutingURLs.USER.PROFILE.replace(':username', username);


        return (
            <Fragment>
                <div className="col-md-2 mx-auto">

                    <h5 className="font-weight-bold text-uppercase mt-3 mb-4">VISIT</h5>

                    <ul className="list-unstyled">
                        <li>
                            <Link to={myProfileUrl}>
                                <small>👤</small>
                                <span>Profile</span></Link>
                        </li>
                        <li>
                            <Link to={RoutingURLs.USER.PROFILE_OPTIONS_INFORMATION}>
                                <small>⚙</small>
                                <span>Options</span></Link>
                        </li>
                        <li>
                            <Link to={RoutingURLs.THREAD.CREATE}>
                                <small>🚩</small>
                                Create Thread</Link>
                        </li>
                    </ul>

                </div>


                    <div className="col-md-2 mx-auto">

                        <h5 className="font-weight-bold text-uppercase mt-3 mb-4">---</h5>

                        <ul className="list-unstyled">
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
                                <Link to={RoutingURLs.AUTHENTICATION.LOGOUT}>
                                    <small>🚪</small>
                                    Logout</Link>
                            </li>
                        </ul>

                    </div>
            </Fragment>
        );
    }

}

export default AdminFooter;