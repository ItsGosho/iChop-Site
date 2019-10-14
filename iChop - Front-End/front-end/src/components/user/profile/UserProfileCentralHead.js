import React, {Component, Fragment} from 'react';
import Roles from "../../../constants/roles.constants";

class UserProfileCentralHead extends Component {


    render() {
        let username = 'ItsGosho';
        let role = Roles.ADMIN;
        let statusMessage = 'Hello!';
        let isAuthenticated = true;

        return (
            <div className="central-content">

                <div className="col-md-auto username_and_rank_follow_unfollow">
                    <div className="div-first-head">
                        <div className="row">
                            <div>
                                <div className="col-md-auto head">
                                    <span className="div-first-content">{username}</span>
                                </div>
                            </div>
                        </div>
                        <div className="row">
                            <div>
                                <div className="col-md-auto head">
                                    <span className="div-first-content">{role}</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    {isAuthenticated ? (
                        <Fragment>
                            <div className="row">
                                <div className="col-md-12 head">
                                    <a href=' '
                                       onClick={(event) => {
                                           event.preventDefault();
                                       }}
                                       className="follow-controls">Follow</a>
                                    <a href=' ' onClick={(event) => {
                                        event.preventDefault();
                                    }}
                                       className="follow-controls">Unfollow</a>

                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12 head">
                                    <small className="is-followed-you"/>
                                </div>
                            </div>
                        </Fragment>
                    ) : null}

                    <div className="dropdown-divider"/>
                </div>

                <div className="col-md-auto status">
                    <div className="status-div">
                        <div className="row">
                            <div>

                                {statusMessage != null ? (
                                    <div className="col-md-auto head">
                                        <span className="status-message">{statusMessage}</span>
                                    </div>
                                ) : null}

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default UserProfileCentralHead;