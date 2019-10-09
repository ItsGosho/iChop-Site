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
        let creatorUsername = 'ItsGosho';
        let creatorProfileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', creatorUsername);
        let creatorAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', creatorUsername);
        let creatorTotalComments = 15;
        let createdOn = formatDate(new Date(), 'dd MMM,yyyy');
        let postedAt = formatDate(new Date(), 'HH:mm');
        let totalViews = 45;
        let totalReactions = 15;
        let totalComments = 3;
        let content = '<center><h1>Hi!</h1></center>';

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
                                    <span className="totalComments">{creatorTotalComments}</span> total comments
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

                <div class="card-body">

                    <div class="row">
                        <div class="col-md-8">
                            <h3 class="title" th:text="*{title}"></h3>
                        </div>
                        <div class="col-md-4">
                            <small class="thread-createdOn">
                                <small class="dateIcon">📅</small>
                                <small class="date">{createdOn}</small>
                            </small>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-8">
                            <small>
                                <span class="postTime">Posted at {postedAt}</span>
                                (<span class="totalViews">{totalViews}</span>👀 / <span
                                class="totalLikes">{totalReactions}</span>👍 )
                            </small>
                        </div>


                        <div class="col-md-4">
                            <small class="thread-total_comments">
                                <small>💬</small>
                                <a th:href="@{/thread/{id}/read#section-thread_read_comments(id=*{id})}"><span
                                    class="totalComments" th:text="*{totalComments}"></span></a>
                                Comments
                            </small>
                        </div>
                    </div>
                    < div
                        class
                            ="dropdown-divider">
                        < /div>
                            <div class="content" th:id="'content' + ${thread.id}">
                                <p class="card-text thread-content" th:utext="*{content}">

                                </p>
                            </div>


                            < div
                                class
                                    ="row">
                                < div
                                    class
                                        ="col-md-8 thread-random_separation">
                                    < /div>
                                </div>

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="btn-group">
                                            <button sec:authorize="isAuthenticated() && hasAuthority('MODERATOR')"
                                                    class="btn btn-secondary btn-sm dropdown-toggle" type="button"
                                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                <small>⚙</small>
                                                Options
                                            </button>
                                            <div class="dropdown-menu">
                                                <form class="dropdown-item" th:action="@{/thread/{id}/delete(id=*{id})}"
                                                      method="post">
                                                    <button
                                                        sec:authorize="isAuthenticated() && hasAuthority('MODERATOR')"
                                                        type="submit" class="btn btn-light btn-sm thread-delete_button">
                                                        <small>❌</small>
                                                        Delete
                                                    </button>
                                                </form>
                                            </div>

                                            <button th:id="'thread-report_button-'+*{id}"
                                                    sec:authorize="isAuthenticated()"
                                                    class="btn btn-sm thread-report_button"
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


                                            <div sec:authorize="isAuthenticated()" class="modal" role="dialog"
                                                 th:id="*{'modelReportThreadByThreadId-'+id}">
                                                <div class="modal-dialog">

                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title">Report to the kings:</h4>
                                                        </div>
                                                        <form method="post"
                                                              th:action="@{/thread/{id}/report(id=*{id})}">
                                                            <div class="modal-body">
    <textarea class="thread-modal_report-textarea"
              name="reason" placeholder="Reason..."></textarea>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="submit" class="btn btn-default">Report
                                                                </button>
                                                                <button type="button" class="btn btn-default"
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
                                        <div class="btn-group thread-right_side_buttons">
                                            <button sec:authorize="isAuthenticated()"
                                                    id="button-commentThread-readThreadPage"
                                                    class="btn btn-sm" type="button"
                                                    aria-haspopup="true" aria-expanded="false">
                                                <small>💬</small>
                                                Comment
                                            </button>
                                            <button th:id="'thread-reaction_buttons-'+*{id}"
                                                    sec:authorize="isAuthenticated()" class="btn btn-sm dropdown-toggle"
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

                                            <div class="dropdown-menu">
                                                <form th:action="@{/thread/{id}/reaction/like(id=*{id})}" method="post">
                                                    <button sec:authorize="isAuthenticated()"
                                                            class="btn btn-sm thread-right_side_button-react"
                                                            type="submit">
                                                        <small>👍🏻</small>
                                                        Like
                                                    </button>
                                                </form>
                                                <form th:action="@{/thread/{id}/reaction/dislike(id=*{id})}"
                                                      method="post">
                                                    <button sec:authorize="isAuthenticated()"
                                                            class="btn btn-sm thread-right_side_button-react"
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