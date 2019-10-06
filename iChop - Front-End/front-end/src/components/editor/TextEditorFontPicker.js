import React, {Component} from 'react';
import TextEditorCommands from "./text.editor.commands.constants";

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

                <a href="#" id="button-fontFamily-textEditor" className="dropdown-toggle"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                   title="Font Family" onClick={this.preventDefault}>
                    <i className="material-icons">font_download</i>
                </a>

                <ul className="dropdown-menu scrollable-menu" role="menu"
                    style={{'minHeight': '100px', 'maxHeight': '100px', 'overflow': 'auto'}}>

                    <a id="button-fontFamily_arial-textEditor" href="#" onClick={this.autoFontFamilyProceed}>
                        <li data-content="Arial" style={{'fontFamily': 'Arial'}}>Arial</li>
                    </a>

                    <a id="button-fontFamily_bookAntiqua-textEditor" href="#" onClick={this.autoFontFamilyProceed}>
                        <li data-content="Book Antiqua" style={{'fontFamily': 'Book Antiqua'}}>Book Antiqua</li>
                    </a>

                    <a id="button-fontFamily_courierNew-textEditor" href="#" onClick={this.autoFontFamilyProceed}>
                        <li data-content="Courier New" style={{'fontFamily': 'Courier New'}}>Courier New</li>
                    </a>

                    <a id="button-fontFamily_georgia-textEditor" href="#" onClick={this.autoFontFamilyProceed}>
                        <li data-content="Georgia" style={{'fontFamily': 'Georgia'}}>Georgia</li>
                    </a>

                    <a id="button-fontFamily_tahoma-textEditor" href="#" onClick={this.autoFontFamilyProceed}>
                        <li data-content="Tahoma" style={{'fontFamily': 'Tahoma'}}>Tahoma</li>
                    </a>

                    <a id="button-fontFamily_timesNewRoman-textEditor" href="#" onClick={this.autoFontFamilyProceed}>
                        <li data-content="Times New Roman"
                            style={{'fontFamily': 'Times New Roman'}}>Times New Roman</li>
                    </a>

                    <a id="button-fontFamily_trebuchetMS-textEditor" href="#" onClick={this.autoFontFamilyProceed}>
                        <li data-content="Trebuchet MS" style={{'fontFamily': 'Trebuchet MS'}}>Trebuchet MS</li>
                    </a>

                    <a id="button-fontFamily_verdana-textEditor" href="#" onClick={this.autoFontFamilyProceed}>
                        <li data-content="Verdana" style={{'fontFamily': 'Verdana'}}>Verdana</li>
                    </a>

                </ul>
            </span>
        );
    }
}

export default TextEditorFontPicker;