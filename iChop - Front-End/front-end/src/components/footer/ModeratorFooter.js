import React, {Component, Fragment} from 'react';

class ModeratorFooter extends Component {


    render() {

        return (
            <Fragment>
                <div className="col-md-2 mx-auto">

                    <h5 className="font-weight-bold text-uppercase mt-3 mb-4">---</h5>
                    <ul className="list-unstyled">
                        <li>
                            <a th:href="@{'/user/'+${#authentication.name}+'/profile'}">
                                <small>👤</small>
                                <span>Profile</span></a>
                        </li>
                        <li>
                            <a href="/user/my-profile/options/information">
                                <small>⚙</small>
                                <span>Options</span></a>
                        </li>
                        <li>
                            <a href="/thread/create">
                                <small>🚩</small>
                                Create Thread</a>
                        </li>
                    </ul>

                </div>

                <hr className="clearfix w-100 d-md-none">

                    <div className="col-md-2 mx-auto">

                        <h5 className="font-weight-bold text-uppercase mt-3 mb-4">---</h5>

                        <ul className="list-unstyled">

                            <li>
                                <a href="/comment/reports/all">
                                    <small>⚠</small>
                                    Reports</a>
                            </li>

                            <li>
                                <a href="/logout">
                                    <small>🚪</small>
                                    Logout</a>
                            </li>
                        </ul>

                    </div>
            </Fragment>
    );
    }

    }

    export default ModeratorFooter;