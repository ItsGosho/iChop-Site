import React, {Component, Fragment} from 'react';

class TextEditorInsertImageModal extends Component {

    render() {

        return (
            <Fragment>
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
            </Fragment>
        );
    }
}

export default TextEditorInsertImageModal;