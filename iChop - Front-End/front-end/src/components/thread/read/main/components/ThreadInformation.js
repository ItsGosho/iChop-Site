import React, {Component, Fragment} from 'react';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../../../constants/routing.constants";

class ThreadInformation extends Component {


    render() {
        let {id,title,createdOn,postedAt,totalViews,totalReactions,totalComments} = this.props;
        let threadCommentsFragmentUrl = RoutingURLs.THREAD.VIEW.replace(':id', id) + '#section-thread_read_comments';

        return (
            <Fragment>
                <div className="row">
                    <div className="col-md-8">
                        <h3 className="title">{title}</h3>
                    </div>
                    <div className="col-md-4">
                        <small className="thread-createdOn">
                            <small className="dateIcon">📅</small>
                            <small className="date">{createdOn}</small>
                        </small>
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-8">
                        <small>
                            <span>Posted at {postedAt} ( {totalViews}👀 / {totalReactions}👍 )</span>
                        </small>
                    </div>


                    <div className="col-md-4">
                        <small className="thread-total_comments">
                            <Link to={threadCommentsFragmentUrl}>
                                <span className="totalComments">{totalComments}</span>
                            </Link>
                            <small>💬</small>
                        </small>
                    </div>
                </div>
            </Fragment>
        );
    }

}

export default ThreadInformation;