import React, {Component} from 'react';
import CommandExecutorHoc from "./command.executor.hoc";
import TextEditorCommands from "./text.editor.commands.constants";

class TextEditorAlignPicker extends Component {


    render() {
        let {execCommand,preventDefault} = this.props;

        return (
            <span>
                <a href="#"  className="dropdown-toggle"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                   title="Text Align" onClick={preventDefault}>
                    <i className="material-icons">format_align_justify</i>
                </a>

                <ul className="dropdown-menu scrollable-menu" role="menu">

                    <a href="#" onClick={execCommand(TextEditorCommands.ALIGN_LEFT)}>
                        <li><i className="material-icons">format_align_left</i>Align Left</li>
                    </a>

                    <a href="#" onClick={execCommand(TextEditorCommands.ALIGN_CENTER)}>
                        <li><i className="material-icons">format_align_center</i>Align Center</li>
                    </a>

                    <a href="#" onClick={execCommand(TextEditorCommands.ALIGN_RIGHT)}>
                        <li><i className="material-icons">format_align_right</i>Align Right</li>
                    </a>

                </ul>

            </span>
        );
    }
}

export default CommandExecutorHoc(TextEditorAlignPicker);