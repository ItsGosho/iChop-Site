import React from 'react';
import PropTypes from 'prop-types';


const DropdownIconSpan = ({icon, text, onClick}) => (
    <span className="dropdown-item" onClick={onClick}>
        <small>{icon}</small>
        {text}
    </span>
);

export default DropdownIconSpan;

DropdownIconSpan.propTypes = {
    icon: PropTypes.string,
    text: PropTypes.string,
    onClick: PropTypes.func,
};