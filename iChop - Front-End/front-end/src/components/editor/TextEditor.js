import React, {Component} from 'react';
import TextEditorColorPicker from "./TextEditorColorPicker";

class TextEditor extends Component {

    constructor(props) {
        super(props);

        this.preventDefault = this.preventDefault.bind(this);
    }


    preventDefault(event) {
        event.preventDefault();
    }

    render() {

        return (
            <div className="col-md-8">
                <a href="#" id="button-formatBold-textEditor" title="Bold" onClick={this.preventDefault}><i
                    className="material-icons">format_bold</i></a>
                <a href="#" id="button-formatItalic-textEditor" title="Italic" onClick={this.preventDefault}><i
                    className="material-icons">format_italic</i></a>
                <a href="#" id="button-formatUnderline-textEditor" title="Underline" onClick={this.preventDefault}><i
                    className="material-icons">format_underlined</i></a>

                <TextEditorColorPicker/>

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
                                </span>

                <span>
                                        <a href="#" id="button-fontFamily-textEditor" className="dropdown-toggle"
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                           title="Font Family" onClick={this.preventDefault}><i
                                            className="material-icons">font_download</i></a>
                                        <ul className="dropdown-menu scrollable-menu" role="menu"
                                            style={{'minHeight': '100px', 'maxHeight': '100px', 'overflow': 'auto'}}>
                                        <a id="button-fontFamily_arial-textEditor" data-content="Arial" href="#"
                                           onClick={this.preventDefault}><li style={{'fontFamily': 'Arial'}}>Arial</li></a>
                                        <a id="button-fontFamily_bookAntiqua-textEditor" data-content="Book Antiqua"
                                           href="#" onClick={this.preventDefault}><li
                                            style={{'fontFamily': 'Book Antiqua'}}>Book Antiqua</li></a>
                                        <a id="button-fontFamily_courierNew-textEditor" data-content="Courier New"
                                           href="#" onClick={this.preventDefault}><li
                                            style={{'fontFamily': 'Courier New'}}>Courier New</li></a>
                                        <a id="button-fontFamily_georgia-textEditor" data-content="Georgia" href="#"
                                           onClick={this.preventDefault}><li
                                            style={{'fontFamily': 'Georgia'}}>Georgia</li></a>
                                        <a id="button-fontFamily_tahoma-textEditor" data-content="Tahoma" href="#"
                                           onClick={this.preventDefault}><li
                                            style={{'fontFamily': 'Tahoma'}}>Tahoma</li></a>
                                        <a id="button-fontFamily_timesNewRoman-textEditor"
                                           data-content="Times New Roman" href="#" onClick={this.preventDefault}><li
                                            style={{'fontFamily': 'Times New Roman'}}>Times New Roman</li></a>
                                        <a id="button-fontFamily_trebuchetMS-textEditor" data-content="Trebuchet MS"
                                           href="#" onClick={this.preventDefault}><li
                                            style={{'fontFamily': 'Trebuchet MS'}}>Trebuchet MS</li></a>
                                        <a id="button-fontFamily_verdana-textEditor" data-content="Verdana" href="#"
                                           onClick={this.preventDefault}><li
                                            style={{'fontFamily': 'Verdana'}}>Verdana</li></a>
                                    </ul>
                                </span>

                <a href="#" id="button-insertLink-textEditor" data-toggle="modal"
                   data-target="#modal-insertLink-textEditor"
                   title="Insert Link" onClick={this.preventDefault}><i
                    className="material-icons">insert_link</i></a>
                <div className="modal fade" id="modal-insertLink-textEditor" tabIndex="-1" role="dialog"
                     aria-labelledby="modalLabelInsertLink" aria-hidden="true">
                    <div className="modal-dialog" role="document">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title" id="modalLabelInsertLink">Insert Link</h5>
                                <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div className="modal-body">
                                <div className="input-group mb-3">
                                    <input id="input-insertLink-textEditor" type="text" className="form-control"
                                           aria-describedby="basic-addon1"
                                           placeholder="Example: https://youtube.com..."/>
                                </div>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                <button id="button-proceedInsertLink-textEditor" type="button"
                                        className="btn btn-primary">Insert
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <a href="#" id="button-insertImage-textEditor" data-toggle="modal"
                   data-target="#modal-insertImage-textEditor"
                   title="Insert Image" onClick={this.preventDefault}><i className="material-icons">photo</i></a>
                <div className="modal fade" id="modal-insertImage-textEditor" tabIndex="-1" role="dialog"
                     aria-labelledby="modalLabelInsertImage" aria-hidden="true">
                    <div className="modal-dialog" role="document">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title" id="modalLabelInsertImage">Image URL</h5>
                                <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div className="modal-body">
                                <div className="input-group mb-3">
                                    <input id="input-insertImage-textEditor" type="text" className="form-control"
                                           aria-describedby="basic-addon1" placeholder=""/>
                                </div>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                <button id="button-proceedInsertImage-textEditor" type="button"
                                        className="btn btn-primary">Insert
                                </button>
                            </div>
                        </div>
                    </div>
                </div>


                <a href="#" id="button-undo-textEditor" title="Undo" onClick={this.preventDefault}><i
                    className="material-icons">undo</i></a>
                <a href="#" id="button-redo-textEditor" title="Redo" onClick={this.preventDefault}><i
                    className="material-icons">redo</i></a>
                <a href="#" id="button-removeFormatting-textEditor" title="Remove Formatting"
                   onClick={this.preventDefault}><i
                    className="material-icons">format_clear</i></a>

                <span>
                                        <a href="#" id="button-textAlign-textEditor" className="dropdown-toggle"
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                           title="Text Align" onClick={this.preventDefault}><i
                                            className="material-icons">format_align_justify</i></a>
                                    <ul className="dropdown-menu scrollable-menu" role="menu">
                                        <a id="button-alignLeft-textEditor" href="#" onClick={this.preventDefault}><li><i
                                            className="material-icons">format_align_left</i>Align Left</li></a>
                                        <a id="button-alignCenter-textEditor" href="#" onClick={this.preventDefault}><li><i
                                            className="material-icons">format_align_center</i>Align Center</li></a>
                                        <a id="button-alignRight-textEditor" href="#" onClick={this.preventDefault}><li><i
                                            className="material-icons">format_align_right</i>Align Right</li></a>
                                    </ul>
                                </span>


            </div>
        );
    }
}

export default TextEditor;