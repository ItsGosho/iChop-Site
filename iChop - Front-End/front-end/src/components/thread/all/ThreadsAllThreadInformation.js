import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../constants/routing/routing.constants";
import {Link} from "react-router-dom";
import formatDate from 'dateformat';

class ThreadsAllThreadInformation extends Component {


    render() {
        let {id, title, createdOn, postTime, username, totalViews, totalReactions, totalComments} = this.props;

        let userProfileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);
        let threadReadUrl = RoutingURLs.THREAD.VIEW.replace(':id', id);

        let datePattern = 'dd mmm,yyyy';
        let postPattern = 'HH:mm';

        return (
            <Fragment>
                <div className="row">
                    <div className="col-md-8">
                        <h3 className="title">{title}</h3>
                    </div>
                    <div className="col-md-4">
                        <small className="date">
                            <small className="dateIcon">📅 {formatDate(createdOn,datePattern)}</small>
                        </small>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-8">
                        <small>
                            <span>by </span>
                            <Link to={userProfileUrl}>👤 {username}</Link>
                            <span> at {formatDate(postTime,postPattern)} ({totalViews}👀/{totalReactions} 👍)</span>
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

export default ThreadsAllThreadInformation;