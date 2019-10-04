import React, {Component, Fragment} from 'react';

class UserProfileCentralContent extends Component {


    render() {
        let username = '';
        let role = '';
        let statusMessage = '';
        let isAuthenticated = true;

        return (
            <Fragment>
                <div className="central-content" style={{'margin-left': '5px'}}>

                    <div className="col-md-auto username_and_rank_follow_unfollow">
                        <div style={{'line-height': '20px'}}>
                            <div className="row">
                                <div>
                                    <div className="col-md-auto head">
                                        <span style={{'font-size': '25px'}}>{username}</span>
                                    </div>
                                </div>
                            </div>
                            <div className="row">
                                <div>
                                    <div className="col-md-auto head">
                                        <span style={{'font-size': '25px'}}>{role}</span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        {
                            (() => {
                                if (isAuthenticated) {
                                    return (
                                        <Fragment>
                                            <div className="row">
                                                <div className="col-md-12 head">
                                                    <a href=' '
                                                       onClick={(event) => {
                                                           event.preventDefault();
                                                       }}
                                                       id="a-follow_user-userProfile"
                                                       style={{
                                                           'font-size': '13px',
                                                           'float': 'right',
                                                           'display': 'none'
                                                       }}>Follow</a>
                                                    <a href=' '
                                                       onClick={(event) => {
                                                           event.preventDefault();
                                                       }}
                                                       id="a-unfollow_user-userProfile"
                                                       style={{
                                                           'font-size': '13px',
                                                           'float': 'right',
                                                           'display': 'none'
                                                       }}>Unfollow</a>

                                                </div>
                                            </div>
                                            <div className="row">
                                                <div className="col-md-12 head">
                                                    <small id="a-is_he_followed_you-userProfile"
                                                           style={{
                                                               'font-size': '11px',
                                                               'float': 'right',
                                                               'display': 'inline-block'
                                                           }}/>
                                                </div>
                                            </div>
                                        </Fragment>
                                    )
                                }
                            })()
                        }

                        <div className="dropdown-divider"/>
                    </div>

                    <div className="col-md-auto status">
                        <div style={{'line-height': '20px'}}>
                            <div className="row">
                                <div>
                                    {
                                        (() => {
                                            if (statusMessage != null) {
                                                return (
                                                    <div className="col-md-auto head">
                                                        <span
                                                            style={{'font-size': '13px','font-family': 'Georgia','font-style': 'italic'}}>{statusMessage}</span>
                                                    </div>
                                                );
                                            }
                                        })()
                                    }
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="col-md-auto user-information-navigation" style={{'margin-top': '50px'}}>

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