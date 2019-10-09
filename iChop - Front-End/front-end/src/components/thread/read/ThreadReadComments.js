import React, {Component} from 'react';
import dateFormat from 'dateformat'
import RoutingURLs from "../../../constants/routing.constants";
import FrontEndResourcesRoutingURLs from "../../../constants/front-end.resources.routings";
import ServerRoutingURLs from "../../../constants/server.routing.urls";
import {Link} from "react-router-dom";

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
                                        <
                                            /div>

                                            <div className="content thread-comment-content">
                                                <p className="card-text" th:utext="*{content}">

                                                </p>
                                            </div>

                                            < /div>

                                                <div>
                                                    <div className="btn-group thread-comments-buttons">
                                                        <div className="thread-comments-button_options">
                                                            <th:block sec:authorize="isAuthenticated()">
                                                                <button
                                                                    th:if="${@commentServicesImp.findById(comment.id).creator.username.equals(#authentication.principal.username)} or ${#authorization.expression('hasAuthority(''MODERATOR'')')}"
                                                                    className="btn btn-sm dropdown-toggle"
                                                                    type="button"
                                                                    data-toggle="dropdown" aria-haspopup="true"
                                                                    aria-expanded="false">
                                                                    <small>⚙</small>
                                                                    Options
                                                                </button>
                                                                <div className="dropdown-menu">
                                                                    <form className="dropdown-item"
                                                                          th:action="@{/comment/{id}/delete(id=*{id})}"
                                                                          method="post">
                                                                        <button
                                                                            th:if="${@commentServicesImp.findById(comment.id).creator.username.equals(#authentication.principal.username)} or ${#authorization.expression('hasAuthority(''MODERATOR'')')}"
                                                                            type="submit"
                                                                            className="btn btn-light btn-sm thread-delete_button">
                                                                            <small>❌</small>
                                                                            Delete
                                                                        </button>
                                                                    </form>
                                                                </div>
                                                                <div className="thread-comments-button_report">


                                                                    <button className="btn btn-sm" type="button"
                                                                            th:id="'thread-comment-report_button-'+*{id}">
                                                                        <small>⚠</small>
                                                                        Report
                                                                    </button>

                                                                    <script th:inline="javascript">
                                                                        run();

                                                                        function run() {

                                                                        let userUsername = /*[[${#authentication.name}]]*/ null;
                                                                        let commentId = /*[[*{id}]]*/ null;
                                                                        let isLoggedUser = /*[[${#authorization.expression('isAuthenticated()')}]]*/ null;

                                                                        hideReportButtonsOfCommentIfReportExists(userUsername, commentId, isLoggedUser);
                                                                    }

                                                                    </script>

                                                                    <div className="modal"
                                                                         th:id="*{'modelReportCommentByThreadId-'+id}"
                                                                         role="dialog">
                                                                        <div className="modal-dialog">

                                                                            <div className="modal-content">
                                                                                <div className="modal-header">
                                                                                    <h4 className="modal-title">Report
                                                                                        to the
                                                                                        kings:</h4>
                                                                                </div>
                                                                                <form method="post"
                                                                                      th:action="@{/comment/{id}/report(id=*{id})}">
                                                                                    <div className="modal-body">
                                        <textarea className="thread-comments-modal"
                                                  name="reason" placeholder="Reason..."></textarea>
                                                                                    </div>
                                                                                    <div
                                                                                        className="modal-footer">
                                                                                        <button type="submit"
                                                                                                className="btn btn-default">
                                                                                            Report
                                                                                        </button>
                                                                                        <button type="button"
                                                                                                className="btn btn-default"
                                                                                                data-dismiss="modal">
                                                                                            Cancel
                                                                                        </button>
                                                                                    </div>
                                                                                </form>
                                                                            </div>

                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <script th:inline="javascript">
                                                                    run();

                                                                    function run() {
                                                                    let commentId = /*[[*{id}]]*/ null;
                                                                    bindShowModal($("#thread-comment-report_button-" + commentId), $("#modelReportCommentByThreadId-" + commentId));
                                                                }
                                                                </script>
                                                            </th:block>
                                                        </div>
                                                        <th:block sec:authorize="isAuthenticated()">
                                                            <div className="thread-comments-button_options"
                                                                 th:id="'thread-comment_react_buttons-'+*{id}">
                                                                <button className="btn btn-sm dropdown-toggle"
                                                                        type="button"
                                                                        data-toggle="dropdown"
                                                                        aria-haspopup="true"
                                                                        aria-expanded="false">
                                                                    <small>💡</small>
                                                                    React
                                                                </button>
                                                                <div className="dropdown-menu">
                                                                    <form
                                                                        th:action="@{/comment/{id}/reaction/like(id=*{id})}"
                                                                        method="post">
                                                                        <button
                                                                            className="btn btn-sm thread-right_side_button-react"
                                                                            type="submit">
                                                                            <small>👍🏻</small>
                                                                            Like
                                                                        </button>
                                                                    </form>
                                                                    <form
                                                                        th:action="@{/comment/{id}/reaction/dislike(id=*{id})}"
                                                                        method="post">
                                                                        <button
                                                                            className="btn btn-sm thread-right_side_button-react"
                                                                            type="submit">
                                                                            <small>👎🏻</small>
                                                                            Dislike
                                                                        </button>
                                                                    </form>
                                                                </div>
                                                            </div>
                                                            <script th:inline="javascript">
                                                                run();

                                                                function run() {

                                                                let userUsername = /*[[${#authentication.name}]]*/ null;
                                                                let commentId = /*[[*{id}]]*/ null;
                                                                let isLoggedUser = /*[[${#authorization.expression('isAuthenticated()')}]]*/ null;

                                                                hideCommentReactionButtonsIfAlreadyReacted(userUsername, commentId, isLoggedUser);
                                                            }

                                                            </script>
                                                        </th:block>
                                                    </div>
                                                </div>

                                                < /div>
                                                    )
                                                    ;
                                                    }
                                                    ))()
                                                    }

                                                </section>
                                                )
                                                ;
                                                }

                                                }

                                                export default ThreadReadComments;