import React, {Component} from 'react';
import LinkIcon from "./LinkIcon";

class LinkIconLi extends Component {

    render() {
        let {url, icon, text, onClick} = this.props;

        return (
            <li>
                <LinkIcon url={url} icon={icon} text={text} onClick={onClick}/>
            </li>
        );
    }
}

export default LinkIconLi;