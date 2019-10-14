import React, {Component, Fragment} from 'react';
import './UserProfileCentralContent.css'
import UserProfileCentralHead from "./UserProfileCentralHead";

class UserProfileCentralContent extends Component {


    render() {

        return (
            <Fragment>

                <UserProfileCentralHead/>

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