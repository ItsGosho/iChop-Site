import React, {Fragment} from 'react';
import formatDate from 'dateformat';
import PropTypes from 'prop-types';


const CREATED_ON_DATE_PATTERN = "dd mmm,yyyy";

const CommentInformation = ({createdOn, likes, dislikes}) => (
    <Fragment>

        <small className="thread-comments-date_likes_dislikes">
            <small className="dateIcon">📅</small>
            <small>{formatDate(createdOn, CREATED_ON_DATE_PATTERN)}</small>
        </small>

        <small className="thread-comments-date_likes_dislikes">
            <small>👍</small>
            <span>{likes !== undefined ? likes.length : 0}</span>
        </small>

        <small className="thread-comments-date_likes_dislikes">
            <small>👎</small>
            <span>{dislikes !== undefined ? dislikes.length : 0}</span>
        </small>

    </Fragment>
);

export default CommentInformation;


CommentInformation.propTypes = {
    createdOn: PropTypes.instanceOf(Date),
    likes: PropTypes.array,
    dislikes: PropTypes.array,
};