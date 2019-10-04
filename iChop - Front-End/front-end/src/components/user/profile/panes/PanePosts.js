import React, {Component, Fragment} from 'react';

class PanePosts extends Component {

    render() {
        let isAuthenticated = false;

        return (
            <Fragment>
                <div className="dropdown-divider"/>

                <div className="create-post" sec:authorize="isAuthenticated()">
                    <form method="post" th:action="@{/post/{userUsername}/create(userUsername=*{username})}">
                        <div className="row">

                            <div className="col-md-1">
                                <object
                                    th:data="@{http://localhost:8001/data/user/{username}/avatar(username=${#authentication.principal.username})}"
                                    type="image/png"
                                    className="img-user-avatar" style="width: 30px;height: 30px">
                                    <img src="/res/img/avatar-user.png" alt="example" className="img-user-avatar"
                                         style="width: 30px;height: 30px">
                                </object>
                            </div>

                            <div className="col-lg">
                <textarea name="content" id="textarea-createPost-userProfile"
                          style="border:1px solid #ccc;border-radius: 3px;height: 60px;overflow: auto;width: 100%;resize: none"></textarea>
                            </div>

                        </div>

                        <div className="row" style="margin-top: 3px">
                            <div className="col-lg">
                                <button type="button" id="button-createPost-userProfile" className="btn btn-info btn-sm"
                                        style="float: right;display: inline-block">Post
                                </button>
                                <small id="small-leftPostCreationCharacters-userProfile"
                                       style="float: right;display: inline-block;margin-right: 3px;color: darkgreen;font-size: 13px">150
                                </small>
                            </div>
                        </div>
                    </form>

                    <script>
                        runPostCreateScript();

                    </script>

                </div>

                <div className="dropdown-divider"/>

                <div className="all-posts" style="margin-top: 10px">

                    <th:block th:each="post : *{posts}" th:object="${post}">

                        <div className="card post" style="margin-top: 10px">
                            <div className="card-body" style="margin-bottom: -15px">
                                <div className="row" style="margin-top: -15px">
                                    <div className="col-md-1" style="margin-top: 9px">
                                        <img
                                            th:src="@{http://localhost:8001/data/user/{username}/avatar(username=*{creatorUsername})}"
                                            alt="example"
                                            onError="this.onerror = null;this.src = '/res/img/avatar-user.png'"
                                            style="width: 30px;height: 30px">
                                    </div>
                                    <div className="col-lg" style="width: 150px">
                                        <a href="" style="color: #775322;font-size: 13px"><b
                                            th:text="*{creatorUsername}">Creator
                                            Username...</b> </a>
                                        <small th:text="*{content}">Content...</small>
                                    </div>
                                </div>
                                <div className="row" sec:authorize="isAuthenticated()">
                                    <div className="col-md-12">
                        <span style="color: #7f7f7f;font-size: 10px"
                              th:text="*{#temporals.format(createdOn, 'MMM dd,yyyy')}">Jan 00,0000</span>

                                        <th:block th:if="${@postServicesImp.findById(post.id).creator.username.equals(#authentication.principal.username)
                                                or @postServicesImp.findById(post.id).user.username.equals(#authentication.principal.username)}
                                                or ${#authorization.expression('hasAuthority(''MODERATOR'')')}">
                                            <form method="post" th:action="@{/post/{postId}/delete(postId=*{id})}"
                                                  style="all: initial">
                                                <button type="submit" style="all:initial;cursor: pointer;"><span
                                                    style="font-size: 10px;color: #007bff">❌Delete</span></button>
                                            </form>
                                        </th:block>

                                        <button type="button" th:id="'button-report_post-'+*{id}"
                                                style="all:initial;cursor: pointer;">
                                            <span style="font-size: 10px;color: #007bff">🎌Report</span></button>

                                        <script th:inline="javascript">
                                            run();

                                            function run() {

                                            let userUsername = /*[[${#authentication.name}]]*/ null;
                                            let postId = /*[[*{id}]]*/ null;
                                            let isLoggedUser = /*[[${#authorization.expression('isAuthenticated()')}]]*/ null;

                                            hideReportButtonsOfPostIfReportExists(userUsername, postId, isLoggedUser);
                                        }

                                        </script>

                                        <div className="modal" th:id="'modal-report_post-'+*{id}" role="dialog">
                                            <div className="modal-dialog">

                                                <div className="modal-content">
                                                    <div className="modal-header">
                                                        <h4 className="modal-title">Report to the kings:</h4>
                                                    </div>
                                                    <form method="post"
                                                          th:action="@{/post/{postId}/report(postId=*{id})}">
                                                        <div className="modal-body">
                                        <textarea className="post-modal_report-textarea"
                                                  name="reason" placeholder="Reason..."
                                                  style="width: 100%;overflow-y: scroll;height: 200px;resize: none;"></textarea>
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
                                            </div>
                                        </div>

                                        <script th:inline="javascript">
                                            run();
                                            function run() {
                                            let postId = /*[[*{id}]]*/ null;
                                            bindShowModal($(`#button-report_post-${postId}`), $(`#modal-report_post-${postId}`));
                                        }
                                        </script>

                                    </div>
                                </div>
                            </div>
                        </div>

                    </th:block>

                </div>
            </Fragment>
    );
    }
    }

    export default PanePosts;