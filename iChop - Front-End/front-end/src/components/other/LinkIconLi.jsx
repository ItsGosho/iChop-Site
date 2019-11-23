import React from 'react';
import LinkIcon from "./LinkIcon";
import PropTypes from 'prop-types';


const LinkIconLi = ({url, icon, text, onClick}) => (
    <li>
        <LinkIcon url={url}
                  icon={icon}
                  text={text}
                  onClick={onClick}/>
    </li>
);

export default LinkIconLi;


LinkIconLi.propTypes = {
    url: PropTypes.string,
    icon: PropTypes.string,
    text: PropTypes.string,
    onClick: PropTypes.func,
};