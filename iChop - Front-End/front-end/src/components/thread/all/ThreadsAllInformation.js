import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Link} from "react-router-dom";

class ThreadsAllInformation extends Component {


    render() {
        let {id, title, createdOn, postTime, username, totalViews, totalReactions, totalComments} = this.props;

        let userProfileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);
        let threadReadUrl = RoutingURLs.THREAD.VIEW.replace(':id', id);

        return (
            <Fragment>
                <div className="row">
                    <div className="col-md-8">
                        <h3 className="title">{title}</h3>
                    </div>
                    <div className="col-md-4">
                        <small className="date">
                            <small className="dateIcon">📅 {createdOn}</small>
                        </small>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-8">
                        <small>
                            <span>by </span>
                            <Link to={userProfileUrl}>👤 {username}</Link>
                            <span> at {postTime} ({totalViews}👀/{totalReactions} 👍)</span>
                        </small>
                    </div>
                    <div className="col-md-4">
                        <small className="totalComments">
                            <Link to={threadReadUrl}>{totalComments}💬</Link>
                        </small>
                    </div>
                </div>
            </Fragment>
        );
    }
}

export default ThreadsAllInformation;