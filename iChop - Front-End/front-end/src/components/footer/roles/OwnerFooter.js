import React, {Component, Fragment} from 'react';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";
import FooterLeftAuthenticatedSide from "../etc/FooterLeftAuthenticatedSide";

class OwnerFooter extends Component {


    render() {

        return (
            <Fragment>


                <ul className="list-unstyled">
                    <FooterLeftAuthenticatedSide/>
                </ul>


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
                            <Link to={RoutingURLs.THREAD.CREATE}>
                                <small>🚩</small>
                                Create Thread</Link>
                        </li>
                    </ul>

                </div>
            </Fragment>
        );
    }

}

export default OwnerFooter;