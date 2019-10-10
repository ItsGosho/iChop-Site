import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../../constants/routing.constants";
import {Link} from "react-router-dom";

class ThreadAlInformation extends Component {


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
                            <small className="dateIcon">📅</small>
                            <small>{createdOn}</small>
                        </small>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-8">
                        <small>by <Link
                            to={userProfileUrl}>👤<span
                            className="user">{username}</span></Link> at
                            <span
                                className="postTime">{postTime}</span>
                            (<span className="totalViews">{totalViews}</span>👀
                            / <span
                                className="totalLikes">{totalReactions}</span>👍)
                        </small>
                    </div>
                    <div className="col-md-4">
                        <small className="totalComments">
                            <small>💬</small>
                            <Link to={threadReadUrl}><span>{totalComments}</span></Link> Comments
                        </small>
                    </div>
                </div>
            </Fragment>
        );
    }

}

export default ThreadAlInformation;