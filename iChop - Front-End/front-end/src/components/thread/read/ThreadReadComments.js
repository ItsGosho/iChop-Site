import React, {Component} from 'react';
import dateFormat from 'dateformat'

class ThreadReadComments extends Component {


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

                        return (
                            <div className="card thread-comments">

                                <div>
                                    <small className="thread-comments-date_likes_dislikes">
                                        <small className="dateIcon">📅</small>
                                        <small className="date"
                                               th:text="*{#temporals.format(createdOn, 'dd MMM,yyyy')}"></small>
                                    </small>
                                    <small className="thread-comments-date_likes_dislikes">
                                        <small>👍</small>
                                        <span className="totalComments" th:text="*{totalLikes}">0</span>
                                    </small>
                                    <small className="thread-comments-date_likes_dislikes">
                                        <small>👎</small>
                                        <span className="totalComments" th:text="*{totalDislikes}">0</span>
                                    </small>
                                </div>

                                <div className="row">
                                    <div className="col-md-3 thread-comment-creator_info_section">
                                        <div>
                                            <img
                                                th:src="@{http://localhost:8001/data/user/{username}/avatar(username=*{commentCreator.username})}"
                                                alt="example"
                                                onError="this.onerror = null;this.src = '/res/img/avatar-user.png'"
                                                className="card-img-top thread-comment-creator_avatar">
                                        </div>
                                        <div>
                                            <small>👤<a
                                                th:href="@{/user/{username}/profile(username=*{commentCreator.username})}"
                                                th:text="*{commentCreator.username}"></a></small>
                                        </div>
                                        <div>
                                            <small>
                                                <small>💬</small>
                                                <span className="totalComments"
                                                      th:text="*{commentCreator.totalComments}"></span> total
                                                comments
                                            </small>
                                        </div>

                                        <div>
                                            <th:block th:if="*{commentCreator.minecraftAccountName != null}">
                                                <small>
                                                    <a th:href="@{/player/{uuid}(uuid=*{commentCreator.minecraftAccountUUID})}"><img
                                                        th:src="@{https://minotar.net/avatar/{minecraftAccountName}(minecraftAccountName=*{commentCreator.minecraftAccountName})}"
                                                        className="card-img-top thread-comment-creator-minecraft_username"
                                                        alt="Card image cap"><span
                                                        th:text="*{commentCreator.minecraftAccountName}"></span></a>
                                                </small>
                                            </th:block>
                                        </div>
                                    </div>

                                    <div className="content thread-comment-content">
                                        <p className="card-text" th:utext="*{content}">

                                        </p>
                                    </div>

                                </div>

                                <div>
                                    <div className="btn-group thread-comments-buttons">
                                        <div className="thread-comments-button_options">
                                            <th:block sec:authorize="isAuthenticated()">
                                                <button
                                                    th:if="${@commentServicesImp.findById(comment.id).creator.username.equals(#authentication.principal.username)} or ${#authorization.expression('hasAuthority(''MODERATOR'')')}"
                                                    className="btn btn-sm dropdown-toggle" type="button"
                                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <small>⚙</small>
                                                    Options
                                                </button>
                                                <div className="dropdown-menu">
                                                    <form className="dropdown-item"
                                                          th:action="@{/comment/{id}/delete(id=*{id})}" method="post">
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

                                                    <div className="modal" th:id="*{'modelReportCommentByThreadId-'+id}"
                                                         role="dialog">
                                                        <div className="modal-dialog">

                                                            <div className="modal-content">
                                                                <div className="modal-header">
                                                                    <h4 className="modal-title">Report to the kings:</h4>
                                                                </div>
                                                                <form method="post"
                                                                      th:action="@{/comment/{id}/report(id=*{id})}">
                                                                    <div className="modal-body">
                                        <textarea className="thread-comments-modal"
                                                  name="reason" placeholder="Reason..."></textarea>
                                                                    </div>
                                                                    <div className="modal-footer">
                                                                        <button type="submit" className="btn btn-default">
                                                                            Report
                                                                        </button>
                                                                        <button type="button" className="btn btn-default"
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
                                                <button className="btn btn-sm dropdown-toggle" type="button"
                                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <small>💡</small>
                                                    React
                                                </button>
                                                <div className="dropdown-menu">
                                                    <form th:action="@{/comment/{id}/reaction/like(id=*{id})}"
                                                          method="post">
                                                        <button className="btn btn-sm thread-right_side_button-react"
                                                                type="submit">
                                                            <small>👍🏻</small>
                                                            Like
                                                        </button>
                                                    </form>
                                                    <form th:action="@{/comment/{id}/reaction/dislike(id=*{id})}"
                                                          method="post">
                                                        <button className="btn btn-sm thread-right_side_button-react"
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

                            </div>
                        );
                    }))()
                }

            </section>
        );
    }

}

export default ThreadReadComments;