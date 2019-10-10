import React, {Component} from 'react';
import TextEditorCommands from "./text.editor.commands.constants";
import CommandExecutorHoc from "./command.executor.hoc";

class TextEditorFontSizePicker extends Component {

    constructor(props) {
        super(props);

        this.autoFontSizeProceed = this.autoFontSizeProceed.bind(this);
    }


    autoFontSizeProceed(event) {
        let element = event.target;
        let size = element.getAttribute('data-content');
        document.execCommand(TextEditorCommands.SET_FONT_SIZE, false, Number(size));
    }

    render() {
        let {preventDefault} = this.props;

        return (
            <span>
                <a href="#" className="dropdown-toggle"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                   title="Font Size" onClick={preventDefault}>
                    <i className="material-icons">format_size</i>
                </a>

                <ul className="dropdown-menu scrollable-menu" role="menu"
                    style={{'minHeight': '100px', 'maxHeight': '100px', 'overflow': 'auto'}}>

                    <a href="#" onClick={this.autoFontSizeProceed}>
                        <li data-content="1" style={{'fontSize': '9px'}}>1</li>
                    </a>

                    <a href="#" onClick={this.autoFontSizeProceed}>
                        <li data-content="2" style={{'fontSize': '10px'}}>2</li>
                    </a>

                    <a href="#" onClick={this.autoFontSizeProceed}>
                        <li data-content="3" style={{'fontSize': '12px'}}>3</li>
                    </a>

                    <a href="#" onClick={this.autoFontSizeProceed}>
                        <li data-content="4" style={{'fontSize': '15px'}}>4</li>
                    </a>

                    <a href="#" onClick={this.autoFontSizeProceed}>
                        <li data-content="5" style={{'fontSize': '18px'}}>5</li>
                    </a>

                    <a href="#" onClick={this.autoFontSizeProceed}>
                        <li data-content="6" style={{'fontSize': '22px'}}>6</li>
                    </a>

                    <a href="#" onClick={this.autoFontSizeProceed}>
                        <li data-content="7" style={{'fontSize': '26px'}}>7</li>
                    </a>

                </ul>
            </span>
        );
    }
}

export default CommandExecutorHoc(TextEditorFontSizePicker);