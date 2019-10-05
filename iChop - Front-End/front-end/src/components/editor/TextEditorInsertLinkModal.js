import React, {Component, Fragment} from 'react';

class TextEditorInsertLinkModal extends Component {

    render() {

        return (


            <Fragment>

                <a href="#" i d="button-insertLink-textEditor" data-toggle="modal"
                   data-target="#modal-insertLink-textEditor"
                   title="Insert Link" onClick={this.preventDefault}><i
                    className="material-icons">insert_link</i>
                </a>

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
            </Fragment>


        );
    }
}

export default TextEditorInsertLinkModal;