import React, {Component, Fragment} from 'react';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../../../constants/routing/routing.constants";
import withState from "../../../../../hocs/with.state";
import formDate from 'dateformat';

class ThreadInformation extends Component {


    render() {
        let createdOnPattern = 'dd mmm,yyyy';
        let postedAtPattern = 'HH:mm';

        let {id, title, createdOn, postedAt, views, totalReactions, comments} = this.props.threadRead;
        let threadCommentsFragmentUrl = RoutingURLs.THREAD.READ.replace(':id', id) + '#section-thread_read_comments';

        return (
            <Fragment>
                <div className="row">
                    <div className="col-md-8">
                        <h3 className="title">{title}</h3>
                    </div>
                    <div className="col-md-4">
                        <small className="thread-createdOn">
                            <small className="dateIcon">📅</small>
                            <small>{formDate(createdOn, createdOnPattern)}</small>
                        </small>
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-8">
                        <small>
                            <span>Posted at {formDate(postedAt, postedAtPattern)} ( {views}👀 / {totalReactions}👍 )</span>
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

}

export default withState(ThreadInformation);