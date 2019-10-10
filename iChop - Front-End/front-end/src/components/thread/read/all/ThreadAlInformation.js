import React, {Component, Fragment} from 'react';

class ThreadAlInformation extends Component {


    render() {
        let {id, title, createdOn, postTime, username, totalViews, totalReactions, totalComments} = this.props;

        return (
            <Fragment>
                <div className="row">
                    <div className="col-md-8">
                        <h3 className="title" th:text="*{title}">...</h3>
                    </div>
                    <div className="col-md-4">
                        <small className="date">
                            <small className="dateIcon">📅</small>
                            <small
                                th:text="*{#temporals.format(createdOn, 'dd MMM,yyyy')}">1
                                Jan,1970
                            </small>
                        </small>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-8">
                        <small>by <a
                            th:href="@{/user/{username}/profile(username=*{creatorUsername})}">👤<span
                            className="user" th:text="*{creatorUsername}"></span></a> at
                            <span
                                className="postTime"
                                th:text="*{#temporals.format(createdOn, 'HH:mm')}"></span>
                            (<span className="totalViews" th:text="*{totalViews}">0</span>👀
                            / <span
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
            </Fragment>
        );
    }

}

export default ThreadAlInformation;