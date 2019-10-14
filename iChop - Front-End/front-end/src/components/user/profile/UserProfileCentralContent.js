import React, {Component, Fragment} from 'react';
import './UserProfileCentralContent.css'
import Roles from "../../../constants/roles.constants";

class UserProfileCentralContent extends Component {


    render() {
        let username = 'ItsGosho';
        let role = Roles.ADMIN;
        let statusMessage = 'Hello!';
        let isAuthenticated = true;

        return (
            <Fragment>
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

                <div className="col-md-auto user-information-navigation">

                    <div className="navigation">
                        <ul className="nav nav-tabs">
                            <li className="nav-item">
                                <a className="nav-link active" data-toggle="tab" href="#posts">Profile posts</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" data-toggle="tab" href="#latest-activity">Latest Site
                                    Activity</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" data-toggle="tab" href="#latest-in-game-activity">Lates In-Game
                                    Activity</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" data-toggle="tab" href="#information">Information</a>
                            </li>
                        </ul>
                    </div>


                    <div className="tab-content">

                        <div className="tab-pane container active" id="posts">
                            {/* <th:block th:insert="user/profile/panes/user-profile-pane-posts"></th:block>*/}
                        </div>

                        <div className="tab-pane container fade" id="latest-activity">Latest activity</div>
                        <div className="tab-pane container fade" id="latest-in-game-activity">Latest in-game activity
                        </div>
                        <div className="tab-pane container fade" id="information">
                            {/*<th:block th:insert="user/profile/panes/user-profile-pane-information"></th:block>*/}
                        </div>
                    </div>

                </div>
            </Fragment>
        );
    }

}

export default UserProfileCentralContent;