import React, {Component, Fragment} from 'react';
import TextEditorColorPicker from "./TextEditorColorPicker";
import TextEditorFontSizePicker from "./TextEditorFontSizePicker";
import TextEditorFontPicker from "./TextEditorFontPicker";
import TextEditorAlignPicker from "./TextEditorAlignPicker";
import TextEditorInsertLinkModal from "./TextEditorInsertLinkModal";
import TextEditorInsertImageModal from "./TextEditorInsertImageModal";
import CommandExecutorHoc from "./command.executor.hoc";
import TextEditorCommands from "./text.editor.commands.constants";
import './TextEditor.css'

class TextEditor extends Component {

    render() {
        let {execCommand} = this.props;

        return (
            <div className="col-md-8">

                <a href="#" id="button-formatBold-textEditor" title="Bold"
                   onClick={execCommand(TextEditorCommands.FORMAT_BOLD)}>
                    <i className="material-icons">format_bold</i>
                </a>

                <a href="#" id="button-formatItalic-textEditor" title="Italic"
                   onClick={execCommand(TextEditorCommands.FORMAT_ITALIC)}>
                    <i className="material-icons">format_italic</i>
                </a>

                <a href="#" id="button-formatUnderline-textEditor" title="Underline"
                   onClick={execCommand(TextEditorCommands.FORMAT_UNDERLINE)}>
                    <i className="material-icons">format_underlined</i>
                </a>

                <TextEditorColorPicker/>

                <TextEditorFontSizePicker/>

                <TextEditorFontPicker/>

                <TextEditorInsertLinkModal/>

                <TextEditorInsertImageModal/>


                <a href="#" id="button-undo-textEditor" title="Undo" onClick={execCommand(TextEditorCommands.UNDO)}>
                    <i className="material-icons">undo</i>
                </a>

                <a href="#" id="button-redo-textEditor" title="Redo" onClick={execCommand(TextEditorCommands.REDO)}>
                    <i className="material-icons">redo</i>
                </a>

                <a href="#" id="button-removeFormatting-textEditor" title="Remove Formatting"
                   onClick={execCommand(TextEditorCommands.REMOVE_FORMAT)}>
                    <i className="material-icons">format_clear</i>
                </a>

                <TextEditorAlignPicker/>
            </div>
        );
    }
}

export default CommandExecutorHoc(TextEditor);