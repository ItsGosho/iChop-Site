import React, {Component} from 'react';
import './ThreadAll.css';
import formatDate from 'dateformat'
import PaginationNav from "../../../report/PaginationNav";
import RoutingURLs from "../../../../constants/routing.constants";

class ThreadAll extends Component {


    render() {
        let threads = [
            {
                id: 'id1',
                title: 'Welcome',
                createdOn: formatDate(new Date(), 'dd mmm,yyyy'),
                creatorUsername: 'ItsGosho',
                postTime: formatDate(new Date(), 'HH:mm'),
                totalViews: 10,
                totalReactions: 5,
                totalComments: 3,
                content: '<h1>Welcome</h1>'
            },
            {
                id: 'id2',
                title: 'Ooops',
                createdOn: formatDate(new Date(), 'dd mmm,yyyy'),
                creatorUsername: 'Mariika',
                postTime: formatDate(new Date(), 'HH:mm'),
                totalViews: 3,
                totalReactions: 16,
                totalComments: 2,
                content: '<h1>Welcome</h1>'
            },
            {
                id: 'id3',
                title: 'Guten tag!',
                createdOn: formatDate(new Date(), 'dd mmm,yyyy'),
                creatorUsername: 'Ivancho',
                postTime: formatDate(new Date(), 'HH:mm'),
                totalViews: 4,
                totalReactions: 7,
                totalComments: 19,
                content: '<h1>Welcome</h1>'
            }
        ];

        return (
            <div id="threads" className="col-xs-6">
                <div className="card">
                    <div className="card-header">
                        <small>📰</small>
                        News:
                    </div>


                    {
                        (() => {
                            if (threads.length > 0) {

                            } else {
                                return (<span>There are no news!</span>);
                            }
                        })()
                    }

                    <PaginationNav totalResults={threads.length} resultsPerPage={10} redirectPage={RoutingURLs.HOME}/>

                    <th:block th:if="${!#lists.isEmpty(threads)}">

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

                        <div className="dropdown-divider"/>

                    </th:block>

                </div>
            </div>

        );
    }

}

export default ThreadAll;