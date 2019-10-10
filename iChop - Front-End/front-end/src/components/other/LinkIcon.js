import React, {Component} from 'react';
import {Link} from "react-router-dom";

class LinkIcon extends Component {

    render() {
        let {url, icon, text,onClick} = this.props;

        url = url === undefined ? '#' : url;

        return (
            <Link to={url} onClick={onClick}>
                <small>{icon}</small>
                <span>{text}</span>
            </Link>
        );
    }
}

export default LinkIcon;