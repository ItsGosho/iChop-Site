import React, {Component} from 'react';
import TextEditorCommands from "../text.editor.commands.constants";
import CommandExecutorHoc from "../command.executor.hoc";
import MaterialIcons from "../../../constants/material.icons.types.constants";

class TextEditorFontSizePicker extends Component {

    render() {
        let {preventDefault} = this.props;

        return (
            <span>
                <a href="#"
                   className="dropdown-toggle"
                   data-toggle="dropdown"
                   aria-haspopup="true"
                   aria-expanded="false"
                   title="Font Size"
                   onClick={preventDefault}>
                    <i className="material-icons">{MaterialIcons.FORMAT_SIZE}</i>
                </a>

                <ul className="dropdown-menu scrollable-menu" role="menu">

                    <Size i={1} size={9}/>
                    <Size i={2} size={10}/>
                    <Size i={3} size={12}/>
                    <Size i={4} size={15}/>
                    <Size i={5} size={18}/>
                    <Size i={6} size={22}/>
                    <Size i={7} size={26}/>

                </ul>
            </span>
        );
    }
}

const Size = (props) => {
    let {i, size} = props;

    return (
        <a href='#'>
            <li data-content={i}
                style={{'fontSize': `${size}px`}}
                onClick={() => {
                    document.execCommand(TextEditorCommands.SET_FONT_SIZE, false, Number(i))
                }}>
                {i}
            </li>
        </a>

    );
};

export default CommandExecutorHoc(TextEditorFontSizePicker);