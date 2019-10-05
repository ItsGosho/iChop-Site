import React, {Component} from 'react';
import TextEditorColorPicker from "./TextEditorColorPicker";
import TextEditorFontSizePicker from "./TextEditorFontSizePicker";
import TextEditorFontPicker from "./TextEditorFontPicker";

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

                <TextEditorFontSizePicker/>

                <TextEditorFontPicker/>

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