import React, {Component} from 'react';

class TextEditorFontSizePicker extends Component {

    render() {

        return (
            <span>
                                        <a href="#" id="button-fontSize-textEditor" className="dropdown-toggle"
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                           title="Font Size" onClick={this.preventDefault}><i
                                            className="material-icons">format_size</i></a>
                                        <ul className="dropdown-menu scrollable-menu" role="menu"
                                            style={{'minHeight': '100px', 'maxHeight': '100px', 'overflow': 'auto'}}>
                                        <a id="button-fontSize_1-textEditor" data-content="1" href="#"
                                           onClick={this.preventDefault}><li style={{'fontSize': '9px'}}>1</li></a>
                                        <a id="button-fontSize_2-textEditor" data-content="2" href="#"
                                           onClick={this.preventDefault}><li style={{'fontSize': '10px'}}>2</li></a>
                                        <a id="button-fontSize_3-textEditor" data-content="3" href="#"
                                           onClick={this.preventDefault}><li style={{'fontSize': '12px'}}>3</li></a>
                                        <a id="button-fontSize_4-textEditor" data-content="4" href="#"
                                           onClick={this.preventDefault}><li style={{'fontSize': '15px'}}>4</li></a>
                                        <a id="button-fontSize_5-textEditor" data-content="5" href="#"
                                           onClick={this.preventDefault}><li style={{'fontSize': '18px'}}>5</li></a>
                                        <a id="button-fontSize_6-textEditor" data-content="6" href="#"
                                           onClick={this.preventDefault}><li style={{'fontSize': '22px'}}>6</li></a>
                                        <a id="button-fontSize_7-textEditor" data-content="7" href="#"
                                           onClick={this.preventDefault}><li style={{'fontSize': '26px'}}>7</li></a>
                                    </ul>

            < span>
            </span>
            </span>
        );
    }
}

export default TextEditorFontSizePicker;