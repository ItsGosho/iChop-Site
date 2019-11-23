import React from 'react';
import {Link} from "react-router-dom";
import PropTypes from 'prop-types';


const DropdownIconLink = ({to, icon, text}) => (
    <Link className="dropdown-item" to={to}>
        <small>{icon}</small>
        {text}
    </Link>
);

export default DropdownIconLink;

DropdownIconLink.propTypes = {
    to: PropTypes.string,
    icon: PropTypes.string,
    text: PropTypes.string,
};