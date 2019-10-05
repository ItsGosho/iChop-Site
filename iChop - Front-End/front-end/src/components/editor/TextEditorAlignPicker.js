import React, {Component} from 'react';

class TextEditorAlignPicker extends Component {

    render() {

        return (
            <span>
                <a href="#" id="button-textAlign-textEditor" className="dropdown-toggle"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                   title="Text Align" onClick={this.preventDefault}>
                    <i className="material-icons">format_align_justify</i>
                </a>

                <ul className="dropdown-menu scrollable-menu" role="menu">

                    <a id="button-alignLeft-textEditor" href="#" onClick={this.preventDefault}>
                        <li><i className="material-icons">format_align_left</i>Align Left</li>
                    </a>

                    <a id="button-alignCenter-textEditor" href="#" onClick={this.preventDefault}>
                        <li><i className="material-icons">format_align_center</i>Align Center</li>
                    </a>

                    <a id="button-alignRight-textEditor" href="#" onClick={this.preventDefault}>
                        <li><i className="material-icons">format_align_right</i>Align Right</li>
                    </a>

                </ul>

            </span>
        );
    }
}

export default TextEditorAlignPicker;