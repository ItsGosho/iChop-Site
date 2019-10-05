import React, {Component} from 'react';
import TextEditorColorPicker from "./TextEditorColorPicker";
import TextEditorFontSizePicker from "./TextEditorFontSizePicker";
import TextEditorFontPicker from "./TextEditorFontPicker";
import TextEditorAlignPicker from "./TextEditorAlignPicker";
import TextEditorInsertLinkModal from "./TextEditorInsertLinkModal";
import TextEditorInsertImageModal from "./TextEditorInsertImageModal";

class TextEditor extends Component {

    constructor(props) {
        super(props);


        this.execCommand = this.execCommand.bind(this);
    }

    execCommand(command) {
        return (event) => {
            event.preventDefault();
            document.execCommand(command);
        }
    }

    render() {

        return (
            <div className="col-md-8">

                <a href="#" id="button-formatBold-textEditor" title="Bold" onClick={this.execCommand('bold')}>
                    <i className="material-icons">format_bold</i>
                </a>

                <a href="#" id="button-formatItalic-textEditor" title="Italic" onClick={this.execCommand('italic')}>
                    <i className="material-icons">format_italic</i>
                </a>

                <a href="#" id="button-formatUnderline-textEditor" title="Underline"
                   onClick={this.execCommand('underline')}>
                    <i className="material-icons">format_underlined</i>
                </a>

                <TextEditorColorPicker/>

                <TextEditorFontSizePicker/>

                <TextEditorFontPicker/>

                <TextEditorInsertLinkModal/>

                <TextEditorInsertImageModal/>


                <a href="#" id="button-undo-textEditor" title="Undo" onClick={this.execCommand('undo')}>
                    <i className="material-icons">undo</i>
                </a>

                <a href="#" id="button-redo-textEditor" title="Redo" onClick={this.execCommand('redo')}>
                    <i className="material-icons">redo</i>
                </a>

                <a href="#" id="button-removeFormatting-textEditor" title="Remove Formatting"
                   onClick={this.execCommand('removeFormat')}>
                    <i className="material-icons">format_clear</i>
                </a>

                <TextEditorAlignPicker/>


            </div>
        );
    }
}

export default TextEditor;