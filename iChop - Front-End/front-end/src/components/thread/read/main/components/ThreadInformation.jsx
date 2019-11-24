import React, {Fragment} from 'react';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../../../constants/routing/routing.constants";
import withState from "../../../../../hocs/with.state";
import formDate from 'dateformat';

const CREATED_ON_DATE_PATTERN = 'dd mmm,yyyy';
const POSTED_AT_DATE_PATTERN = 'HH:mm';

const ThreadInformation = () => {
    let {id, title, createdOn, postedAt, views, totalReactions, comments} = this.props.threadRead;
    let threadCommentsFragmentUrl = RoutingURLs.THREAD.READ(id) + '#section-thread_read_comments';

    return (
        <Fragment>
            <div className="row">
                <div className="col-md-8">
                    <h3 className="title">{title}</h3>
                </div>
                <div className="col-md-4">
                    <small className="thread-createdOn">
                        <small className="dateIcon">📅</small>
                        <small>{formDate(createdOn, CREATED_ON_DATE_PATTERN)}</small>
                    </small>
                </div>
            </div>

            <div className="row">
                <div className="col-md-8">
                    <small>
                        <span>Posted at {formDate(postedAt, POSTED_AT_DATE_PATTERN)} ( {views}👀 / {totalReactions}👍 )</span>
                    </small>
                </div>


                <div className="col-md-4">
                    <small className="thread-total_comments">
                        <Link to={threadCommentsFragmentUrl}>
                            <span>{comments.length}</span>
                        </Link>
                        <small>💬</small>
                    </small>
                </div>
            </div>
        </Fragment>
    );
}

export default withState(ThreadInformation);