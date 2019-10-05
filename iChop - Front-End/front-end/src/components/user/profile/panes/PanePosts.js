import React, {Component, Fragment} from 'react';
import PanePostsCreate from "./posts/PanePostsCreate";

class PanePosts extends Component {

    render() {
        let isAuthenticated = true;

        return (
            <Fragment>
                <div className="dropdown-divider"/>

                {
                    (() => {
                        if (isAuthenticated) {
                            return (
                                <div className="create-post">
                                    <PanePostsCreate/>
                                </div>
                            )
                        }
                    })()
                }

                <div className="dropdown-divider"/>

                <div className="all-posts" style="margin-top: 10px">

                    <th:block th:each="post : *{posts}" th:object="${post}">

                        <div className="card post" style="margin-top: 10px">
                            <div className="card-body" style="margin-bottom: -15px">
                                
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