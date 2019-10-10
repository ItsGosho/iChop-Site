import React,{Component} from 'react';

class ThreadAll extends Component {


    render() {

        return (
            <div id="threads" className="col-xs-6">
                <div className="card">
                    <div className="card-header">
                        <small>📰</small>
                        News:
                    </div>
                    <th:block th:if="${!#lists.isEmpty(threads)}">

                        <!--/*@thymesVar id="threads" type="com.ichop.core.areas.thread.domain.models.view.thread_homepage.ThreadHomepageViewModel"*/-->
                        <!--/*@thymesVar id="thread" type="com.ichop.core.areas.thread.domain.models.view.thread_homepage.ThreadHomepageViewModel"*/-->
                        <th:block th:each="thread : ${threads}" th:object="${thread}">
                            <div th:id="*{id}" className="card-body">
                                <div className="row">
                                    <div className="col-md-8">
                                        <h3 className="title" th:text="*{title}">...</h3>
                                    </div>
                                    <div className="col-md-4">
                                        <small className="date">
                                            <small className="dateIcon">📅</small>
                                            <small th:text="*{#temporals.format(createdOn, 'dd MMM,yyyy')}">1 Jan,1970
                                            </small>
                                        </small>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-md-8">
                                        <small>by <a th:href="@{/user/{username}/profile(username=*{creatorUsername})}">👤<span
                                            className="user" th:text="*{creatorUsername}"></span></a> at
                                            <span
                                                className="postTime"
                                                th:text="*{#temporals.format(createdOn, 'HH:mm')}"></span>
                                            (<span className="totalViews" th:text="*{totalViews}">0</span>👀 / <span
                                                className="totalLikes" th:text="*{totalReactions}">0</span>👍)
                                        </small>
                                    </div>
                                    <div className="col-md-4">
                                        <small className="totalComments">
                                            <small>💬</small>
                                            <a th:href="@{/thread/{id}/read#section-thread_read_comments(id=*{id})}"><span
                                                th:text="*{totalComments}"></span></a> Comments
                                        </small>
                                    </div>
                                </div>
                                <div className="dropdown-divider"></div>

                                <div className="content thread-content" th:id="'content' + ${thread.getId()}">
                                    <p className="card-text" th:utext="${thread.getContent()}">

                                    </p>
                                </div>


                                <div className="dropdown-divider"></div>

                                <div className="row">
                                    <div className="col-md-8">
                                        <div className="btn-group">
                                            <button sec:authorize="isAuthenticated() && hasAuthority('MODERATOR')"
                                                    className="btn btn-secondary btn-sm dropdown-toggle" type="button"
                                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                <small>⚙</small>
                                                Options
                                            </button>
                                            <div className="dropdown-menu">
                                                <form className="dropdown-item"
                                                      th:action="@{/thread/{id}/delete(id=*{id})}" method="post">
                                                    <button
                                                        sec:authorize="isAuthenticated() && hasAuthority('MODERATOR')"
                                                        type="submit"
                                                        className="btn btn-light btn-sm thread-delete_button">
                                                        <small>❌</small>
                                                        Delete
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-md-4">
                                        <a className="dropdown-item" th:href="@{/thread/{id}/read(id=*{id})}">
                                            <button type="button"
                                                    className="btn btn-primary btn-sm btn-brand btn-reddit continueReading">
                                                <small>📖</small>
                                                Continue reading...
                                            </button>
                                        </a>
                                    </div>

                                </div>
                                <div className="dropdown-divider"></div>
                            </div>
                        </th:block>

                        <nav aria-label="Page navigation example"
                             className="d-flex justify-content-center align-items-center">
                            <ul className="pagination">

                                <th:block th:if="${#request.getParameter('page')} != null">

                                    <th:block
                                        th:if="${(#conversions.convert(#request.getParameter('page'), 'Integer')) - 1} >= 0">
                                        <li className="page-item"><a className="page-link"
                                                                     th:href="@{/(page=${(#conversions.convert(#request.getParameter('page'), 'Integer')) - 1})}">Previous</a>
                                        </li>
                                    </th:block>

                                </th:block>

                                <th:block th:each="i: ${#numbers.sequence(1,totalPages)}">
                                    <li className="page-item"><a className="page-link" th:text="${i}"
                                                                 th:href="@{/?page={pId}(pId=${i-1})}"></a></li>
                                </th:block>


                                <th:block th:if="${#request.getParameter('page')} != null">

                                    <th:block
                                        th:if="${(#conversions.convert(#request.getParameter('page'), 'Integer')) + 1 <= totalPages-1}">
                                        <li className="page-item"><a className="page-link"
                                                                     th:href="@{/(page=${(#conversions.convert(#request.getParameter('page'), 'Integer')) + 1})}">Next</a>
                                        </li>
                                    </th:block>

                                </th:block>


                            </ul>
                        </nav>


                        <div className="dropdown-divider"></div>

                    </th:block>

                    <th:block th:if="${#lists.isEmpty(threads)}">
                        There are no news!
                    </th:block>

                </div>
            </div>

        );
    }

}

export default ThreadAll;