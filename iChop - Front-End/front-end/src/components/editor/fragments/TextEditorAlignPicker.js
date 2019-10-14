import React, {Component} from 'react';
import CommandExecutorHoc from "../command.executor.hoc";
import TextEditorCommands from "../text.editor.commands.constants";
import MaterialIcons from "../../../constants/material.icons.types.constants";

class TextEditorAlignPicker extends Component {


    render() {
        return (
            <span>

                <a href="#"
                   className="dropdown-toggle"
                   data-toggle="dropdown"
                   aria-haspopup="true"
                   aria-expanded="false"
                   title="Text Align" onClick={(event => {
                    event.preventDefault()
                })}>
                    <i className="material-icons">{MaterialIcons.FORMAT_ALIGN_JUSTIFY}</i>
                </a>

                <ul className="dropdown-menu scrollable-menu" role="menu">

                    <Option title="Left"
                            command={TextEditorCommands.ALIGN_LEFT}
                            type={MaterialIcons.FORMAT_ALIGN_LEFT}
                            text="Align Left"/>

                    <Option title="Center"
                            command={TextEditorCommands.ALIGN_CENTER}
                            type={MaterialIcons.FORMAT_ALIGN_CENTER}
                            text="Align Center"/>

                    <Option title="Right"
                            command={TextEditorCommands.ALIGN_RIGHT}
                            type={MaterialIcons.FORMAT_ALIGN_RIGHT}
                            text="Align Right"/>

                </ul>

            </span>
        );
    }
}

const Option = CommandExecutorHoc((props) => {
    let {title, command, type, text, execCommand} = props;

    return (
        <a href=' ' title={title} onClick={execCommand(command)}>
            <li><i className="material-icons">{type}</i>{text}</li>
        </a>
    );
});

export default TextEditorAlignPicker;