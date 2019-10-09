import React, {Component, Fragment} from 'react';
import ServerRoutingURLs from "../../../constants/server.routing.urls";
import formatDate from 'dateformat';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";
import FrontEndResourcesRoutingURLs from "../../../constants/front-end.resources.routings";
import Interweave from "interweave";

class ThreadReadMainContent extends Component {

    constructor(props) {
        super(props);

        this.onUserAvatarError = this.onUserAvatarError.bind(this);
    }

    onUserAvatarError(event) {
        event.target.onerror = null;
        event.target.src = FrontEndResourcesRoutingURLs.USER.AVATAR;
    }

    render() {
        let threadId = 'threadId123';
        let creatorUsername = 'ItsGosho';
        let creatorProfileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', creatorUsername);
        let creatorAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', creatorUsername);
        let creatorTotalComments = 15;
        let title = 'Abra kadabra';
        let createdOn = formatDate(new Date(), 'dd mmm,yyyy');
        let postedAt = formatDate(new Date(), 'HH:mm');
        let totalViews = 45;
        let totalReactions = 15;
        let totalComments = 3;
        let content = '<center><h1>Hi!</h1></center>';
        let threadCommentsFragmentUrl = RoutingURLs.THREAD.VIEW.replace(':id', threadId) + '#section-thread_read_comments';

        let uuid = '8ed20904-3262-401a-901a-1946504d2eea';
        let creatorMinecraftAccountName = 'ItsGosho';
        let creatorMinecraftAvatarUrl = ServerRoutingURLs.OUTSIDE.MINOTAR.MINECRAFT.HEAD.replace(':accountName', creatorMinecraftAccountName);
        let creatorMinecraftProfileUrl = RoutingURLs.PLAYER.PROFILE.VIEW.replace(':uuid', uuid);

        let isAuthenticated = true;
        let hasRoleModerator = true;

        let isReportedThreadAlready = false;
        let isLikedThreadAlready = false;

        return (
            <div className="card thread">

                <div className="card-header">
                    <div className="row">
                        <div className="col-md-4">
                            <div align="center">
                                <small>
                                    👤 <Link to={creatorProfileUrl}>{creatorUsername}</Link>
                                </small>
                            </div>
                            <div align="center">
                                <img
                                    src={creatorAvatarUrl}
                                    onError={this.onUserAvatarError}
                                    alt=''
                                    style={{'width': '50px', 'height': '50px'}}
                                    className="card-img-top thread-creator-avatar"/>
                            </div>
                            <div align="center">
                                <small>
                                    <small>💬</small>
                                    <span className="totalComments">{creatorTotalComments} total comments</span>
                                </small>
                            </div>
                        </div>
                        <div className="col-md-8">
                            <div align="center">
                                <div className="row">

                                    {
                                        (() => {
                                            if (creatorMinecraftAccountName !== undefined) {
                                                return (
                                                    <small>
                                                        <Link to={creatorMinecraftProfileUrl}>
                                                            <img
                                                                src={creatorMinecraftAvatarUrl}
                                                                style={{'width': '22px', 'height': '22px'}}
                                                                alt=''/>
                                                            <span
                                                                style={{
                                                                    'fontSize': '15px',
                                                                    'fontFamily': 'Consolas'
                                                                }}>{creatorMinecraftAccountName}
                                                            </span>
                                                        </Link>
                                                    </small>
                                                );
                                            }
                                        })()
                                    }

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="card-body">

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