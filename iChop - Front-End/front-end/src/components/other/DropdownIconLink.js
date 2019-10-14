import React, {Component} from 'react';
import {Link} from "react-router-dom";

class DropdownIconLink extends Component {

    render() {
        let {to, icon, text} = this.props;

        return (
            <Link className="dropdown-item" to={to}>
                <small>{icon}</small>
                {text}
            </Link>
        );
    }
}

export default DropdownIconLink;