import React, {Component} from 'react';
import TextEditorColorPicker from "./TextEditorColorPicker";
import TextEditorFontSizePicker from "./TextEditorFontSizePicker";
import TextEditorFontPicker from "./TextEditorFontPicker";
import TextEditorAlignPicker from "./TextEditorAlignPicker";
import TextEditorInsertLinkModal from "./TextEditorInsertLinkModal";

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

                <TextEditorInsertLinkModal/>

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

                <TextEditorAlignPicker/>


            </div>
        );
    }
}

export default TextEditor;