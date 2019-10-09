import React, {Component} from 'react';
import ServerRoutingURLs from "../../../constants/server.routing.urls";
import formatDate from 'dateformat';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";
import FrontEndResourcesRoutingURLs from "../../../constants/front-end.resources.routings";

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
        let createdOn = formatDate(new Date(), 'dd MMM,yyyy');
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
                                                                className="card-img-top thread-creator-minecraft_username"
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
                                <small>💬</small>
                                <Link to={threadCommentsFragmentUrl}>
                                    <span className="totalComments">{totalComments}</span>
                                </Link>
                                <span>Comments</span>
                            </small>
                        </div>
                    </div>
                    < div
                        class
                            ="dropdown-divider">
                        < /div>
                            <div className="content" th:id="'content' + ${thread.id}">
                                <p className="card-text thread-content" th:utext="*{content}">

                                </p>
                            </div>


                            < div
                                className
                                    ="row">
                                < div
                                    className
                                        ="col-md-8 thread-random_separation">
                                    < /div>
                                </div>

                                <div className="row">
                                    <div className="col-md-12">
                                        <div className="btn-group">
                                            <button sec:authorize="isAuthenticated() && hasAuthority('MODERATOR')"
                                                    className="btn btn-secondary btn-sm dropdown-toggle" type="button"
                                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                <small>⚙</small>
                                                Options
                                            </button>
                                            <div className="dropdown-menu">
                                                <form className="dropdown-item"
                                                      th:action="@{/thread/{id}/delete(id=*{id})}"
                                                      method="post">
                                                    <button
                                                        sec:authorize="isAuthenticated() && hasAuthority('MODERATOR')"
                                                        type="submit"
                                                        className="btn btn-light btn-sm thread-delete_button">
                                                        <small>❌</small>
                                                        Delete
                                                    </button>
                                                </form>
                                            </div>

                                            <button th:id="'thread-report_button-'+*{id}"
                                                    sec:authorize="isAuthenticated()"
                                                    className="btn btn-sm thread-report_button"
                                                    type="button" id="button-reportThread-readThread">
                                                <small>⚠</small>
                                                Report
                                            </button>

                                            <script th:inline="javascript">
                                                run();

                                                function run() {

                                                let userUsername = /*[[${#authentication.name}]]*/ null;
                                                let threadId = /*[[*{id}]]*/ null;
                                                let isLoggedUser = /*[[${#authorization.expression('isAuthenticated()')}]]*/ null;

                                                hideReportButtonsOfThreadIfReportExists(userUsername, threadId, isLoggedUser);
                                            }

                                            </script>


                                            <div sec:authorize="isAuthenticated()" className="modal" role="dialog"
                                                 th:id="*{'modelReportThreadByThreadId-'+id}">
                                                <div className="modal-dialog">

                                                    <div className="modal-content">
                                                        <div className="modal-header">
                                                            <h4 className="modal-title">Report to the kings:</h4>
                                                        </div>
                                                        <form method="post"
                                                              th:action="@{/thread/{id}/report(id=*{id})}">
                                                            <div className="modal-body">
    <textarea className="thread-modal_report-textarea"
              name="reason" placeholder="Reason..."></textarea>
                                                            </div>
                                                            <div className="modal-footer">
                                                                <button type="submit" className="btn btn-default">Report
                                                                </button>
                                                                <button type="button" className="btn btn-default"
                                                                        data-dismiss="modal">
                                                                    Cancel
                                                                </button>
                                                            </div>
                                                        </form>
                                                    </div>

                                                    <script th:inline="javascript">
                                                        run();

                                                        function run() {
                                                        let threadId = /*[[*{id}]]*/ null;
                                                        bindShowModal($("#thread-report_button-" + threadId), $("#modelReportThreadByThreadId-" + threadId));
                                                    }
                                                    </script>

                                                </div>
                                            </div>

                                        </div>
                                        <div className="btn-group thread-right_side_buttons">
                                            <button sec:authorize="isAuthenticated()"
                                                    id="button-commentThread-readThreadPage"
                                                    className="btn btn-sm" type="button"
                                                    aria-haspopup="true" aria-expanded="false">
                                                <small>💬</small>
                                                Comment
                                            </button>
                                            <button th:id="'thread-reaction_buttons-'+*{id}"
                                                    sec:authorize="isAuthenticated()"
                                                    className="btn btn-sm dropdown-toggle"
                                                    type="button"
                                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                <small>💡</small>
                                                React
                                            </button>

                                            <script th:inline="javascript">
                                                run();

                                                function run() {

                                                let userUsername = /*[[${#authentication.name}]]*/ null;
                                                let threadId = /*[[*{id}]]*/ null;
                                                let isLoggedUser = /*[[${#authorization.expression('isAuthenticated()')}]]*/ null;

                                                hideThreadReactionButtonsIfAlreadyReacted(userUsername, threadId, isLoggedUser);
                                            }

                                            </script>

                                            <div className="dropdown-menu">
                                                <form th:action="@{/thread/{id}/reaction/like(id=*{id})}" method="post">
                                                    <button sec:authorize="isAuthenticated()"
                                                            className="btn btn-sm thread-right_side_button-react"
                                                            type="submit">
                                                        <small>👍🏻</small>
                                                        Like
                                                    </button>
                                                </form>
                                                <form th:action="@{/thread/{id}/reaction/dislike(id=*{id})}"
                                                      method="post">
                                                    <button sec:authorize="isAuthenticated()"
                                                            className="btn btn-sm thread-right_side_button-react"
                                                            type="submit">
                                                        <small>👎🏻</small>
                                                        Dislike
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <
                                /div>
                                )
                                ;
                                }

                                }

                                export default ThreadReadMainContent;