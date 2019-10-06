import React, {Component} from 'react';
import TextEditorColorPicker from "./TextEditorColorPicker";
import TextEditorFontSizePicker from "./TextEditorFontSizePicker";
import TextEditorFontPicker from "./TextEditorFontPicker";
import TextEditorAlignPicker from "./TextEditorAlignPicker";
import TextEditorInsertLinkModal from "./TextEditorInsertLinkModal";
import TextEditorInsertImageModal from "./TextEditorInsertImageModal";
import CommandExecutorHoc from "./CommandExecutorHoc";

class TextEditor extends Component {

    render() {
        let {execCommand} = this.props;

        return (
            <div className="col-md-8">

                <a href="#" id="button-formatBold-textEditor" title="Bold" onClick={execCommand('bold')}>
                    <i className="material-icons">format_bold</i>
                </a>

                <a href="#" id="button-formatItalic-textEditor" title="Italic" onClick={execCommand('italic')}>
                    <i className="material-icons">format_italic</i>
                </a>

                <a href="#" id="button-formatUnderline-textEditor" title="Underline"
                   onClick={execCommand('underline')}>
                    <i className="material-icons">format_underlined</i>
                </a>

                <TextEditorColorPicker/>

                <TextEditorFontSizePicker/>

                <TextEditorFontPicker/>

                <TextEditorInsertLinkModal/>

                <TextEditorInsertImageModal/>


                <a href="#" id="button-undo-textEditor" title="Undo" onClick={execCommand('undo')}>
                    <i className="material-icons">undo</i>
                </a>

                <a href="#" id="button-redo-textEditor" title="Redo" onClick={execCommand('redo')}>
                    <i className="material-icons">redo</i>
                </a>

                <a href="#" id="button-removeFormatting-textEditor" title="Remove Formatting"
                   onClick={execCommand('removeFormat')}>
                    <i className="material-icons">format_clear</i>
                </a>

                <TextEditorAlignPicker execCommand={execCommand} preventDefault={this.preventDefault}/>


            </div>
        );
    }
}

export default CommandExecutorHoc(TextEditor);