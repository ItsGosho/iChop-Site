import React, {Component, Fragment} from 'react';
import dateFormat from 'dateformat'
import RoutingURLs from "../../../constants/routing.constants";
import FrontEndResourcesRoutingURLs from "../../../constants/front-end.resources.routings";
import ServerRoutingURLs from "../../../constants/server.routing.urls";
import {Link} from "react-router-dom";
import Interweave from "interweave";

class ThreadReadComments extends Component {

    constructor(props) {
        super(props);

        this.onUserAvatarError = this.onUserAvatarError.bind(this);
    }

    onUserAvatarError(event) {
        event.target.onerror = null;
        event.target.src = FrontEndResourcesRoutingURLs.USER.AVATAR;
    }

    render() {

        let comments = [
            {
                createdOn: dateFormat(new Date(), 'dd MMM,yyyy'),
                totalLikes: 3,
                totalDislikes: 1,
                content: '<h1>Nicee!</h1>',
                creatorUsername: 'ItsGosho',
                creatorTotalComments: 16,
                creatorMinecraftAccountName: 'ItsGosho',
                creatorMinecraftAccountUUID: '8ed20904-3262-401a-901a-1946504d2eea'
            },
            {
                createdOn: dateFormat(new Date(), 'dd MMM,yyyy'),
                totalLikes: 14,
                totalDislikes: 4,
                content: '<h1>Mhhhm!</h1>',
                creatorUsername: 'Penka',
                creatorTotalComments: 3,
                creatorMinecraftAccountName: 'Penka',
                creatorMinecraftAccountUUID: 'd54e8697-7a78-4816-aac1-30f2ec414b1b'
            },
            {
                createdOn: dateFormat(new Date(), 'dd MMM,yyyy'),
                totalLikes: 1,
                totalDislikes: 0,
                content: '<h1>Yeeey!</h1>',
                creatorUsername: 'Roshko',
                creatorTotalComments: 16,
                creatorMinecraftAccountName: 'Roshko',
                creatorMinecraftAccountUUID: '09c5ff23-9bf2-4f5c-b5b1-7feed1802b9d'
            }
        ];

        return (
            <section id="section-thread_read_comments">

                {
                    (() => comments.map((comment, index) => {
                        let {
                            createdOn,
                            totalLikes,
                            totalDislikes,
                            content,
                            creatorUsername,
                            creatorTotalComments,
                            creatorMinecraftAccountName,
                            creatorMinecraftAccountUUID
                        } = comment;

                        let creatorProfileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', creatorUsername);
                        let creatorAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', creatorUsername);
                        let creatorMinecraftPorfileUrl = RoutingURLs.PLAYER.PROFILE.VIEW.replace(':uuid', creatorMinecraftAccountUUID);
                        let creatorMinecraftAvatarUrl = ServerRoutingURLs.OUTSIDE.MINOTAR.MINECRAFT.HEAD.replace(':accountName', creatorMinecraftAccountName);

                        let isAuthenticated = true;

                        return (
                            <div className="card thread-comments">

                                <div>
                                    <small className="thread-comments-date_likes_dislikes">
                                        <small className="dateIcon">📅</small>
                                        <small className="date">{createdOn}</small>
                                    </small>
                                    <small className="thread-comments-date_likes_dislikes">
                                        <small>👍</small>
                                        <span className="totalComments">{totalLikes}</span>
                                    </small>
                                    <small className="thread-comments-date_likes_dislikes">
                                        <small>👎</small>
                                        <span className="totalComments">{totalDislikes}</span>
                                    </small>
                                </div>

                                <div className="row">
                                    <div className="col-md-3 thread-comment-creator_info_section">
                                        <div>
                                            <img
                                                src={creatorAvatarUrl}
                                                alt=' '
                                                onError={this.onUserAvatarError}
                                                className="card-img-top thread-comment-creator_avatar"/>
                                        </div>
                                        <div>
                                            <small>
                                                👤<Link to={creatorProfileUrl}>{creatorUsername}</Link>
                                            </small>
                                        </div>
                                        <div>
                                            <small>
                                                <small>💬</small>
                                                <span
                                                    className="totalComments">{creatorTotalComments} total comments</span>
                                            </small>
                                        </div>

                                        <div>

                                            {
                                                (() => {
                                                    if (creatorMinecraftAccountName !== undefined) {
                                                        return (
                                                            <small>
                                                                <Link to={creatorMinecraftPorfileUrl}>
                                                                    <img
                                                                        src={creatorMinecraftAvatarUrl}
                                                                        className="card-img-top thread-comment-creator-minecraft_username"
                                                                        alt=' '/>
                                                                    {creatorMinecraftAccountName}
                                                                </Link>
                                                            </small>
                                                        );
                                                    }
                                                })()
                                            }

                                        </div>
                                    </div>

                                    <div className="content thread-comment-content">
                                        <p className="card-text">
                                            <Interweave content={content} />
                                        </p>
                                    </div>

                                    <div>

                                        <div>
                                            <div className="btn-group thread-comments-buttons">
                                                <div className="thread-comments-button_options">

                                                    {
                                                        (() => {
                                                            let currentLoggedInUserUsername = 'ItsGosho';
                                                            let hasRoleModerator = true;

                                                            if (isAuthenticated) {
                                                                return (
                                                                    <Fragment>

                                                                        {
                                                                            (() => {
                                                                                if (currentLoggedInUserUsername === creatorUsername || hasRoleModerator) {
                                                                                    return (
                                                                                        <Fragment>

                                                                                            <button
                                                                                                className="btn btn-sm dropdown-toggle"
                                                                                                type="button"
                                                                                                data-toggle="dropdown"
                                                                                                aria-haspopup="true"
                                                                                                aria-expanded="false">
                                                                                                <small>⚙</small>
                                                                                                <span>Options</span>
                                                                                            </button>

                                                                                            <div
                                                                                                className="dropdown-menu">
                                                                                                <button type="submit"
                                                                                                        className="btn btn-light btn-sm thread-delete_button">
                                                                                                    <small>❌</small>
                                                                                                    <span>Delete</span>
                                                                                                </button>
                                                                                            </div>
                                                                                        </Fragment>
                                                                                    )
                                                                                }
                                                                            })()
                                                                        }


                                                                        {
                                                                            (() => {
                                                                                let isCurrentLoggedInUserReportedTheComment = false;

                                                                                if (!isCurrentLoggedInUserReportedTheComment) {
                                                                                    return (
                                                                                        <div
                                                                                            className="thread-comments-button_report">


                                                                                            <button
                                                                                                className="btn btn-sm"
                                                                                                type="button">
                                                                                                <small>⚠</small>
                                                                                                <span>Report</span>
                                                                                            </button>

                                                                                            <div className="modal"
                                                                                                 role="dialog">
                                                                                                <div
                                                                                                    className="modal-dialog">

                                                                                                    <div
                                                                                                        className="modal-content">
                                                                                                        <div
                                                                                                            className="modal-header">
                                                                                                            <h4 className="modal-title">Report
                                                                                                                to the
                                                                                                                kings:</h4>
                                                                                                        </div>
                                                                                                        <div
                                                                                                            className="modal-body">
                                                                                                            <textarea
                                                                                                                className="thread-comments-modal"
                                                                                                                name="reason"
                                                                                                                placeholder="Reason..."/>
                                                                                                        </div>
                                                                                                        <div
                                                                                                            className="modal-footer">
                                                                                                            <button
                                                                                                                type="button"
                                                                                                                className="btn btn-default">
                                                                                                                Report
                                                                                                            </button>
                                                                                                            <button
                                                                                                                type="button"
                                                                                                                className="btn btn-default"
                                                                                                                data-dismiss="modal">
                                                                                                                Cancel
                                                                                                            </button>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    );
                                                                                }
                                                                            })()
                                                                        }

                                                                        {
                                                                            (() => {
                                                                                let isReactedAlready = false;

                                                                                if (isAuthenticated && !isReactedAlready) {
                                                                                    return (
                                                                                        <Fragment>
                                                                                            <div
                                                                                                className="thread-comments-button_options">
                                                                                                <button
                                                                                                    className="btn btn-sm dropdown-toggle"
                                                                                                    type="button"
                                                                                                    data-toggle="dropdown"
                                                                                                    aria-haspopup="true"
                                                                                                    aria-expanded="false">
                                                                                                    <small>💡</small>
                                                                                                    <span>React</span>
                                                                                                </button>
                                                                                                <div
                                                                                                    className="dropdown-menu">

                                                                                                    <button
                                                                                                        className="btn btn-sm thread-right_side_button-react"
                                                                                                        type="submit">
                                                                                                        <small>👍🏻</small>
                                                                                                        <span>Like</span>
                                                                                                    </button>

                                                                                                    <button
                                                                                                        className="btn btn-sm thread-right_side_button-react"
                                                                                                        type="submit">
                                                                                                        <small>👎🏻</small>
                                                                                                        <span>Dislike</span>
                                                                                                    </button>

                                                                                                </div>
                                                                                            </div>
                                                                                        </Fragment>
                                                                                    )
                                                                                }
                                                                            })()
                                                                        }
                                                                    </Fragment>
                                                                )
                                                            }
                                                        })()
                                                    }
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        )
                    })())
                }
            </section>)
    }
}

export default ThreadReadComments;