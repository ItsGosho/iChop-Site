import React, {Component, Fragment} from 'react';
import './UserProfileCentralContent.css'
import UserProfileCentralHead from "./UserProfileCentralHead";
import {Link, Route, Switch} from "react-router-dom";

class UserProfileCentralContent extends Component {


    /*TODO: Със Link ...*/
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
                                    <Link className="nav-link" to={'/posts'}>Profile posts</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to={'/soon'}>Soon</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to={'/latest-in-game-activity'}>Latest In-Game Activity</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to={'/information'}>Information</Link>
                                </li>
                            </ul>
                        </div>


                        <div className="tab-content">

                            <div className="tab-pane container active">Posts</div>
                            <div className="tab-pane container fade">Latest activity</div>
                            <div className="tab-pane container fade">Latest in-game activity</div>
                            <div className="tab-pane container fade">Information</div>

                            {/*<Switch>
                                <Route exact path={RoutingURLs.THREAD.REPORT.ALL} component={() => (<ReportsThread/>)}/>
                                <Route exact path={RoutingURLs.COMMENT.REPORT.ALL} component={() => (<ReportsComment/>)}/>
                                <Route exact path={RoutingURLs.POST.REPORT.ALL} component={() => (<ReportsPost/>)}/>
                            </Switch>*/}
                        </div>

                    </div>
                </div>
            </Fragment>
        );
    }

}

export default UserProfileCentralContent;