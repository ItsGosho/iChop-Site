import React, {Component} from 'react';
import TextEditorCommands from "../text.editor.commands.constants";
import MaterialIcons from "../../../constants/other/material.icons.types.constants";

class TextEditorFontPicker extends Component {


    render() {
        return (
            <span>

                <a href="#" className="dropdown-toggle"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                   title="Font Family" onClick={this.preventDefault}>
                    <i className="material-icons">{MaterialIcons.FONT_DOWNLOAD}</i>
                </a>

                <ul className="dropdown-menu scrollable-menu" role="menu">

                    <Font type="Arial"/>
                    <Font type="Book Antiqua"/>
                    <Font type="Courier New"/>
                    <Font type="Georgia"/>
                    <Font type="Tahoma"/>
                    <Font type="Times New Roman"/>
                    <Font type="Trebuchet MS"/>
                    <Font type="Verdana"/>

                </ul>
            </span>
        );
    }
}

const Font = (props) => {
    let {type} = props;

    return (
        <a href='#'>
            <li style={{'fontFamily': type}} onClick={()=>{
                document.execCommand(TextEditorCommands.SET_FONT_FAMILY, false, type)
            }}>{type}</li>
        </a>

    );
};

export default TextEditorFontPicker;