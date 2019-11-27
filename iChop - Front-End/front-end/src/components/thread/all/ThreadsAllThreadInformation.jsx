import React, {Fragment} from 'react';
import RoutingURLs from "../../../constants/routing/routing.constants";
import {Link} from "react-router-dom";
import formatDate from 'dateformat';
import PropTypes from 'prop-types';


const CREATION_DATE_PATTERN = "dd mmm,yyyy";
const POST_TIME_PATTERN = "HH:mm";

const ThreadsAllThreadInformation = ({id, title, createdOn, postTime, username, totalViews, totalReactions, totalComments}) => (
    <Fragment>
        <div className="row">
            <div className="col-md-8">
                <h3 className="title">{title}</h3>
            </div>
            <div className="col-md-4">
                <small className="date">
                    <small className="dateIcon">📅 {formatDate(createdOn, CREATION_DATE_PATTERN)}</small>
                </small>
            </div>
        </div>
        <div className="row">
            <div className="col-md-8">
                <small>
                    <span>by </span>
                    <Link to={RoutingURLs.USER.PROFILE.VIEW(username)}>👤 {username}</Link>
                    <span> at {formatDate(postTime, POST_TIME_PATTERN)} ({totalViews}👀/{totalReactions} 👍)</span>
                </small>
            </div>
            <div className="col-md-4">
                <small className="totalComments">
                    <Link to={RoutingURLs.THREAD.READ(id)}>{totalComments}💬</Link>
                </small>
            </div>
        </div>
    </Fragment>
);


export default ThreadsAllThreadInformation;


ThreadsAllThreadInformation.propTypes = {
    id: PropTypes.string,
    title: PropTypes.string,
    createdOn: PropTypes.instanceOf(Date),
    username: PropTypes.string,
    postTime: PropTypes.instanceOf(Date),
    totalViews: PropTypes.number,
    totalReactions: PropTypes.number,
    totalComments: PropTypes.number,
};