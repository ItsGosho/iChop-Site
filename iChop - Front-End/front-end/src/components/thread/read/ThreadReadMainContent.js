import React, {Component, Fragment} from 'react';
import ServerRoutingURLs from "../../../constants/server.routing.urls";
import formatDate from 'dateformat';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";
import FrontEndResourcesRoutingURLs from "../../../constants/front-end.resources.routings";
import Interweave from "interweave";
import ThreadCreatorInformation from "./ThreadCreatorInformation";
import ThreadInformation from "./ThreadInformation";

class ThreadReadMainContent extends Component {

    render() {
        let threadId = 'threadId123';
        let creatorUsername = 'ItsGosho';
        let creatorTotalComments = 15;
        let title = 'Abra kadabra';
        let createdOn = formatDate(new Date(), 'dd mmm,yyyy');
        let postedAt = formatDate(new Date(), 'HH:mm');
        let totalViews = 45;
        let totalReactions = 15;
        let totalComments = 3;
        let content = '<center><h1>Hi!</h1></center>';

        let uuid = '8ed20904-3262-401a-901a-1946504d2eea';
        let creatorMinecraftAccountName = 'ItsGosho';

        let isAuthenticated = true;
        let hasRoleModerator = true;

        let isReportedThreadAlready = false;
        let isLikedThreadAlready = false;

        return (
            <div className="card thread">

                <div className="card-header">
                    <ThreadCreatorInformation uuid={uuid}
                                              username={creatorUsername}
                                              totalComments={creatorTotalComments}
                                              minecraftAccountName={creatorMinecraftAccountName}/>
                </div>

                <div className="card-body">

                    <ThreadInformation
                        id={threadId}
                        title={title}
                        createdOn={createdOn}
                        postedAt={postedAt}
                        totalViews={totalViews}
                        totalReactions={totalReactions}
                        totalComments={totalComments}/>

                    <div className="dropdown-divider"/>

                    <div className="content">
                        <p className="card-text thread-content">
                            <Interweave content={content}/>
                        </p>
                    </div>


                    <div className="row">
                        <div className="col-md-8 thread-random_separation"/>

                        <div className="row">
                            <div className="col-md-12">
                                <div className="btn-group">

                                    {
                                        (() => {

                                            if (isAuthenticated && hasRoleModerator) {
                                                return (
                                                    <button
                                                        className="btn btn-secondary btn-sm dropdown-toggle"
                                                        type="button"
                                                        data-toggle="dropdown" aria-haspopup="true"
                                                        aria-expanded="false">
                                                        <small>⚙</small>
                                                        <span>Options</span>
                                                    </button>
                                                );
                                            }

                                        })()
                                    }

                                    <div className="dropdown-menu">
                                        {
                                            (() => {
                                                if (isAuthenticated && hasRoleModerator) {
                                                    return (
                                                        <button
                                                            type="button"
                                                            className="btn btn-light btn-sm thread-delete_button">
                                                            <small>❌</small>
                                                            <span>Delete</span>
                                                        </button>
                                                    );
                                                }
                                            })()
                                        }

                                    </div>

                                    {
                                        (() => {
                                            if (isAuthenticated && !isReportedThreadAlready) {
                                                return (
                                                    <Fragment>

                                                        <button className="btn btn-sm thread-report_button"
                                                                type="button" id="button-reportThread-readThread">
                                                            <small>⚠</small>
                                                            Report
                                                        </button>

                                                        <div className="modal" role="dialog">
                                                            <div className="modal-dialog">

                                                                <div className="modal-content">
                                                                    <div className="modal-header">
                                                                        <h4 className="modal-title">Report to the
                                                                            kings:</h4>
                                                                    </div>

                                                                    <div className="modal-body">
                                                                        <textarea
                                                                            className="thread-modal_report-textarea"
                                                                            name="reason" placeholder="Reason..."/>
                                                                    </div>

                                                                    <div className="modal-footer">

                                                                        <button type="button"
                                                                                className="btn btn-default">Report
                                                                        </button>

                                                                        <button type="button"
                                                                                className="btn btn-default"
                                                                                data-dismiss="modal">Cancel
                                                                        </button>

                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </Fragment>
                                                );
                                            }
                                        })()
                                    }

                                </div>
                                <div className="btn-group thread-right_side_buttons">
                                    {
                                        (() => {
                                            if (isAuthenticated) {
                                                return (
                                                    <Fragment>
                                                        <button
                                                            id="button-commentThread-readThreadPage"
                                                            className="btn btn-sm" type="button"
                                                            aria-haspopup="true" aria-expanded="false">
                                                            <small>💬</small>
                                                            <span>Comment</span>
                                                        </button>

                                                        <button className="btn btn-sm dropdown-toggle"
                                                                type="button"
                                                                data-toggle="dropdown" aria-haspopup="true"
                                                                aria-expanded="false">
                                                            <small>💡</small>
                                                            React
                                                        </button>

                                                        {
                                                            (() => {
                                                                if (!isLikedThreadAlready) {
                                                                    return (
                                                                        <div className="dropdown-menu">
                                                                            <button
                                                                                className="btn btn-sm thread-right_side_button-react"
                                                                                type="button">
                                                                                <small>👍🏻</small>
                                                                                <span> Like</span>
                                                                            </button>
                                                                            <button
                                                                                className="btn btn-sm thread-right_side_button-react"
                                                                                type="button">
                                                                                <small>👎🏻</small>
                                                                                <span> Dislike</span>
                                                                            </button>
                                                                        </div>
                                                                    );
                                                                }
                                                            })()
                                                        }

                                                    </Fragment>
                                                );
                                            }
                                        })()
                                    }
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default ThreadReadMainContent;