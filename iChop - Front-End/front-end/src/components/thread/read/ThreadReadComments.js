import React, {Component, Fragment} from 'react';
import dateFormat from 'dateformat'
import RoutingURLs from "../../../constants/routing.constants";
import FrontEndResourcesRoutingURLs from "../../../constants/front-end.resources.routings";
import ServerRoutingURLs from "../../../constants/server.routing.urls";
import {Link} from "react-router-dom";
import Interweave from "interweave";
import CommentInformation from "./comments/CommentInformation";
import CommentCreatorInformation from "./comments/CommentCreatorInformation";
import CommentReportModal from "./comments/CommentReportModal";
import CommentOptionsDropdown from "./comments/CommentOptionsDropdown";
import CommentReportButton from "./comments/CommentReportButton";

class ThreadReadComments extends Component {


    render() {

        let comments = [
            {
                createdOn: dateFormat(new Date(), 'dd mmm,yyyy'),
                totalLikes: 3,
                totalDislikes: 1,
                content: '<h1>Nicee!</h1>',
                creatorUsername: 'ItsGosho',
                creatorTotalComments: 16,
                creatorMinecraftAccountName: 'ItsGosho',
                creatorMinecraftAccountUUID: '8ed20904-3262-401a-901a-1946504d2eea'
            },
            {
                createdOn: dateFormat(new Date(), 'dd mmm,yyyy'),
                totalLikes: 14,
                totalDislikes: 4,
                content: '<h1>Mhhhm!</h1>',
                creatorUsername: 'Penka',
                creatorTotalComments: 3,
                creatorMinecraftAccountName: 'Penka',
                creatorMinecraftAccountUUID: 'd54e8697-7a78-4816-aac1-30f2ec414b1b'
            },
            {
                createdOn: dateFormat(new Date(), 'dd mmm,yyyy'),
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

                        let isAuthenticated = true;

                        return (
                            <div className="card thread-comments" style={{'borderColor': 'black'}}>

                                <div>
                                    <CommentInformation createdOn={createdOn}
                                                        totalLikes={totalLikes}
                                                        totalDislikes={totalDislikes}/>
                                </div>

                                <div className="row">
                                    <div className="col-md-3 thread-comment-creator_info_section">
                                        <CommentCreatorInformation uuid={creatorMinecraftAccountUUID}
                                                                   username={creatorUsername}
                                                                   totalComments={creatorTotalComments}
                                                                   minecraftAccountName={creatorMinecraftAccountName}/>
                                    </div>

                                    <div className="content thread-comment-content">
                                        <p className="card-text">
                                            <Interweave content={content}/>
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

                                                                        <CommentOptionsDropdown/>

                                                                        <CommentReportButton/>

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
                    }))()
                }
            </section>)
    }
}

export default ThreadReadComments;