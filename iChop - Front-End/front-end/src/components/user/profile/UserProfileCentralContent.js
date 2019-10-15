import React, {Component, Fragment} from 'react';
import './UserProfileCentralContent.css'
import UserProfileCentralHead from "./UserProfileCentralHead";
import {Link, Route, Switch} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";

class UserProfileCentralContent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isPostsTabSelected: true,
            isSoonTabSelected: false,
            isInGameActivitySelected: false,
            isInformationSelected: false,
        }
    }

    allTabsSelectionToFalse() {
        this.setState({
            isPostsTabSelected: false,
            isSoonTabSelected: false,
            isInGameActivitySelected: false,
            isInformationSelected: false,
        })
    }

    selectPostsTab() {
        this.allTabsSelectionToFalse();
        this.setState({isPostsTabSelected: true})
    }

    selectSoonTab() {
        this.allTabsSelectionToFalse();
        this.setState({isSoonTabSelected: true})
    }

    selectInGameActivityTab() {
        this.allTabsSelectionToFalse();
        this.setState({isInGameActivitySelected: true})
    }

    selectInformationTab() {
        this.allTabsSelectionToFalse();
        this.setState({isInformationSelected: true})
    }

    render() {

        return (
            <Fragment>
                <div className="central-content">

                    <UserProfileCentralHead/>

                    <Link to={'/test'}>test</Link>

                    <div className="col-md-auto user-information-navigation">

                        <div className="navigation">
                            <ul className="nav nav-tabs">
                                <li className="nav-item">
                                    <a className="nav-link active" data-toggle='tab' href='#'>Profile posts</a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" data-toggle='tab' href='#'>Soon</a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" data-toggle='tab' href='#'>Latest In-Game
                                        Activity</a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" data-toggle='tab' href='#'>Information</a>
                                </li>
                            </ul>
                        </div>


                        <div className="tab-content">

                            <div className="tab-pane container active">Posts</div>
                            <div className="tab-pane container fade">Latest activity</div>
                            <div className="tab-pane container fade">Latest in-game activity</div>
                            <div className="tab-pane container fade">Information</div>

                        </div>

                    </div>
                </div>
            </Fragment>
        );
    }

}

export default UserProfileCentralContent;