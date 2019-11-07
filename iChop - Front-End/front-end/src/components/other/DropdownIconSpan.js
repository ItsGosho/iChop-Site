import React, {Component} from 'react';
import {Link} from "react-router-dom";

class DropdownIconSpan extends Component {

    render() {
        let {icon, text,onClick} = this.props;

        return (
            <span className="dropdown-item" onClick={onClick}>
                <small>{icon}</small>
                {text}
            </span>
        );
    }
}

export default DropdownIconSpan;