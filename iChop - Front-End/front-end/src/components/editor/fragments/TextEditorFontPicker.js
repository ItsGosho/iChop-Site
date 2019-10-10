import React, {Component} from 'react';
import TextEditorCommands from "../text.editor.commands.constants";
import MaterialIcons from "../../../constants/material.icons.types.constants";

class TextEditorFontPicker extends Component {

    constructor(props) {
        super(props);

        this.autoFontFamilyProceed = this.autoFontFamilyProceed.bind(this);
    }

    autoFontFamilyProceed(event) {
        let element = event.target;
        let family = element.getAttribute('data-content');
        document.execCommand(TextEditorCommands.SET_FONT_FAMILY, false, family);
    }

    render() {
        return (
            <span>

                <a href="#" className="dropdown-toggle"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                   title="Font Family" onClick={this.preventDefault}>
                    <i className="material-icons">{MaterialIcons.FONT_DOWNLOAD}</i>
                </a>

                <ul className="dropdown-menu scrollable-menu" role="menu"
                    style={{'minHeight': '100px', 'maxHeight': '100px', 'overflow': 'auto'}}>

                    <a href="#" onClick={this.autoFontFamilyProceed}>
                        <li data-content="Arial" style={{'fontFamily': 'Arial'}}>Arial</li>
                    </a>

                    <a href="#" onClick={this.autoFontFamilyProceed}>
                        <li data-content="Book Antiqua" style={{'fontFamily': 'Book Antiqua'}}>Book Antiqua</li>
                    </a>

                    <a href="#" onClick={this.autoFontFamilyProceed}>
                        <li data-content="Courier New" style={{'fontFamily': 'Courier New'}}>Courier New</li>
                    </a>

                    <a href="#" onClick={this.autoFontFamilyProceed}>
                        <li data-content="Georgia" style={{'fontFamily': 'Georgia'}}>Georgia</li>
                    </a>

                    <a href="#" onClick={this.autoFontFamilyProceed}>
                        <li data-content="Tahoma" style={{'fontFamily': 'Tahoma'}}>Tahoma</li>
                    </a>

                    <a href="#" onClick={this.autoFontFamilyProceed}>
                        <li data-content="Times New Roman"
                            style={{'fontFamily': 'Times New Roman'}}>Times New Roman</li>
                    </a>

                    <a href="#" onClick={this.autoFontFamilyProceed}>
                        <li data-content="Trebuchet MS" style={{'fontFamily': 'Trebuchet MS'}}>Trebuchet MS</li>
                    </a>

                    <a href="#" onClick={this.autoFontFamilyProceed}>
                        <li data-content="Verdana" style={{'fontFamily': 'Verdana'}}>Verdana</li>
                    </a>

                </ul>
            </span>
        );
    }
}

export default TextEditorFontPicker;