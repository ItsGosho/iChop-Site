import React, {Component, Fragment} from 'react';

class CommentInformation extends Component {


    render() {
        let {createdOn,totalLikes,totalDislikes} = this.props;

        return (
            <Fragment>
                <small className="thread-comments-date_likes_dislikes">
                    <small className="dateIcon">📅</small>
                    <small>{createdOn}</small>
                </small>
                <small className="thread-comments-date_likes_dislikes">
                    <small>👍</small>
                    <span>{totalLikes}</span>
                </small>
                <small className="thread-comments-date_likes_dislikes">
                    <small>👎</small>
                    <span>{totalDislikes}</span>
                </small>
            </Fragment>
        );
    }

}

export default CommentInformation;