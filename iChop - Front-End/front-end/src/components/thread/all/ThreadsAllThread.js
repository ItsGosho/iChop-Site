import React, {Component} from 'react';
import ThreadsAllThreadInformation from "./ThreadsAllThreadInformation";
import Interweave from "interweave";
import ThreadsAllThreadOptionsDropdown from "./ThreadsAllThreadOptionsDropdown";
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";

class ThreadsAllThread extends Component {


    render() {
        let {id, title, createdOn, creatorUsername, postTime, totalViews, totalReactions, totalComments, content} = this.props;
        let threadReadUrl = RoutingURLs.THREAD.VIEW.replace(':id', id);

        return (
            <div className="card-body">
                <ThreadsAllThreadInformation id={id}
                                             title={title}
                                             createdOn={createdOn}
                                             username={creatorUsername}
                                             postTime={postTime}
                                             totalViews={totalViews}
                                             totalReactions={totalReactions}
                                             totalComments={totalComments}/>

                <div className="dropdown-divider"/>

                <div className="content thread-content">
                    <p className="card-text">
                        <Interweave content={content}/>
                    </p>
                </div>

                <div className="dropdown-divider"/>

                <div className="row">

                    <div className="col-md-8">
                        <div className="btn-group">
                            <ThreadsAllThreadOptionsDropdown id={id}/>
                        </div>
                    </div>

                    <div className="col-md-4">
                        <Link className="btn btn-primary btn-sm btn-brand btn-reddit continueReading"
                              to={threadReadUrl}>
                            <small>📖</small>
                            <span>Continue reading...</span>
                        </Link>
                    </div>

                </div>
                <div className="dropdown-divider"/>
            </div>
        );
    }

}

export default ThreadsAllThread;