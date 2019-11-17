import React, {Component} from 'react';
import Trumbowyg from "react-trumbowyg";
import PropTypes from "prop-types";

class HTMLEditor extends Component {

    constructor(props) {
        super(props);

        this.getButtons = this.getButtons.bind(this);
    }


    getButtons() {
        return [
            [Button.VIEW_HTML],
            [Button.FORMATTING],
            Button.SEMANTIC,
            [Button.LINK],
            [Button.INSERT_IMAGE],
            Button.JUSTIFY,
            Button.LISTS,
            [Button.FULLSCREEN]
        ];
    }

    render() {
        let {html, placeholder, onChangeHTML} = this.props;

        return (
            <Trumbowyg buttons={this.getButtons()}
                       data={html}
                       placeholder={placeholder == undefined ? '' : placeholder}
                       onChange={(event) => {
                           onChangeHTML(event.target.innerHTML);
                       }}/>
        );
    }

}

export default HTMLEditor;

const Button = {
    VIEW_HTML: 'viewHTML',
    FORMATTING: 'formatting',
    SEMANTIC: 'btnGrp-semantic',
    LINK: 'link',
    INSERT_IMAGE: 'insertImage',
    JUSTIFY: 'btnGrp-justify',
    LISTS: 'btnGrp-lists',
    FULLSCREEN: 'fullscreen',
};

HTMLEditor.propTypes = {
    html: PropTypes.string,
    placeholder: PropTypes.string,
    onChangeHTML: PropTypes.func,
};