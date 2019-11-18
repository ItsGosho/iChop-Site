import React, {Component, Fragment} from 'react';
import formatDate from 'dateformat';

class CommentInformation extends Component {


    render() {
        let createdOnPattern = 'dd mmm,yyyy';

        let {createdOn, likes, dislikes} = this.props;
        likes = likes !== undefined ? likes : [];
        dislikes = dislikes !== undefined ? dislikes : [];

        return (
            <Fragment>
                <small className="thread-comments-date_likes_dislikes">
                    <small className="dateIcon">📅</small>
                    <small>{formatDate(createdOn,createdOnPattern)}</small>
                </small>
                <small className="thread-comments-date_likes_dislikes">
                    <small>👍</small>
                    <span>{likes.length}</span>
                </small>
                <small className="thread-comments-date_likes_dislikes">
                    <small>👎</small>
                    <span>{dislikes.length}</span>
                </small>
            </Fragment>
        );
    }

}

export default CommentInformation;