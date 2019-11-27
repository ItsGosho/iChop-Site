import React from 'react';
import {Link} from "react-router-dom";
import PropTypes from 'prop-types';


const LinkIcon = ({url, icon, text, onClick}) => (
    <Link to={url === undefined ? '#' : url} onClick={onClick}>
        <small>{icon}</small>
        <span>{text}</span>
    </Link>
);

export default LinkIcon;

LinkIcon.propTypes = {
    url: PropTypes.string,
    icon: PropTypes.string,
    text: PropTypes.string,
    onClick: PropTypes.func,
};