import React, {Component} from 'react';
import TextEditorColorPicker from "./fragments/TextEditorColorPicker";
import TextEditorFontSizePicker from "./fragments/TextEditorFontSizePicker";
import TextEditorFontPicker from "./fragments/TextEditorFontPicker";
import TextEditorAlignPicker from "./fragments/TextEditorAlignPicker";
import TextEditorInsertLinkModal from "./fragments/TextEditorInsertLinkModal";
import TextEditorInsertImageModal from "./fragments/TextEditorInsertImageModal";
import CommandExecutorHoc from "./command.executor.hoc";
import TextEditorCommands from "./text.editor.commands.constants";
import './TextEditor.css'
import MaterialIcons from "../../constants/other/material.icons.types.constants";
import ModalOpen from "../modal/ModalOpen";

class TextEditor extends Component {

    render() {

        return (
            <div className="col-md-8">


                <Option title="Bold" command={TextEditorCommands.FORMAT_BOLD} type={MaterialIcons.FORMAT_BOLD}/>
                <Option title="Italic" command={TextEditorCommands.FORMAT_ITALIC} type={MaterialIcons.FORMAT_ITALIC}/>
                <Option title="Underline" command={TextEditorCommands.FORMAT_UNDERLINE}
                        type={MaterialIcons.FORMAT_UNDERLINE}/>

                <TextEditorColorPicker/>
                <TextEditorFontSizePicker/>
                <TextEditorFontPicker/>


                <ModalOpen relationTo={'insertLink'} title={'Insert Link'}>
                    <i className="material-icons btn-link">{MaterialIcons.INSERT_LINK}</i>
                </ModalOpen>

                <TextEditorInsertLinkModal/>


                <ModalOpen relationTo={'insertImage'} title={'Insert Image'}>
                    <i className="material-icons btn-link">{MaterialIcons.PHOTO}</i>
                </ModalOpen>

                <TextEditorInsertImageModal/>

                <Option title="Undo" command={TextEditorCommands.UNDO} type={MaterialIcons.UNDO}/>
                <Option title="Redo" command={TextEditorCommands.REDO} type={MaterialIcons.REDO}/>
                <Option title="Remove" command={TextEditorCommands.REMOVE_FORMAT} type={MaterialIcons.REMOVE_FORMAT}/>

                <TextEditorAlignPicker/>
            </div>
        );
    }
}

const Option = CommandExecutorHoc((props) => {
    let {title, command, type, execCommand} = props;

    return (
        <a href=' ' title={title} onClick={execCommand(command)}>
            <i className="material-icons">{type}</i>
        </a>
    );
});

export default TextEditor;