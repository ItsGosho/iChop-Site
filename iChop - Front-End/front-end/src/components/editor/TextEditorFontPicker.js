import React, {Component} from 'react';

class TextEditorFontPicker extends Component {

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
                    
                    <a id="button-fontFamily_arial-textEditor" data-content="Arial" href="#"
                       onClick={this.preventDefault}>
                        <li style={{'fontFamily': 'Arial'}}>Arial</li>
                    </a>

                    <a id="button-fontFamily_bookAntiqua-textEditor" data-content="Book Antiqua" href="#"
                       onClick={this.preventDefault}>
                        <li style={{'fontFamily': 'Book Antiqua'}}>Book Antiqua</li>
                    </a>

                    <a id="button-fontFamily_courierNew-textEditor" data-content="Courier New" href="#"
                       onClick={this.preventDefault}>
                        <li style={{'fontFamily': 'Courier New'}}>Courier New</li>
                    </a>

                    <a id="button-fontFamily_georgia-textEditor" data-content="Georgia" href="#"
                       onClick={this.preventDefault}>
                        <li style={{'fontFamily': 'Georgia'}}>Georgia</li>
                    </a>

                    <a id="button-fontFamily_tahoma-textEditor" data-content="Tahoma" href="#"
                       onClick={this.preventDefault}>
                        <li style={{'fontFamily': 'Tahoma'}}>Tahoma</li>
                    </a>

                    <a id="button-fontFamily_timesNewRoman-textEditor" data-content="Times New Roman" href="#"
                       onClick={this.preventDefault}>
                        <li style={{'fontFamily': 'Times New Roman'}}>Times New Roman</li>
                    </a>

                    <a id="button-fontFamily_trebuchetMS-textEditor" data-content="Trebuchet MS" href="#"
                       onClick={this.preventDefault}>
                        <li style={{'fontFamily': 'Trebuchet MS'}}>Trebuchet MS</li>
                    </a>

                    <a id="button-fontFamily_verdana-textEditor" data-content="Verdana" href="#"
                       onClick={this.preventDefault}>
                        <li style={{'fontFamily': 'Verdana'}}>Verdana</li>
                    </a>

                </ul>
            </span>
        );
    }
}

export default TextEditorFontPicker;