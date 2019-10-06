import React, {Component, Fragment} from 'react';
import CommandExecutorHoc from "./command.executor.hoc";
import FormHoc from "../../hocs/form.hoc";
import TextEditorCommands from "./text.editor.commands.constants";

class TextEditorInsertLinkModal extends Component {

    constructor(props) {
        super(props);

        this.state = {
            modal: ''
        };

        this.proceedInsertLink = this.proceedInsertLink.bind(this);
    }

    proceedInsertLink(event) {
        event.preventDefault();
        let {link} = this.props.formData;

        document.execCommand(TextEditorCommands.CREATE_LINK, false, link);
        this.setState({'modal': 'hide'})
    }

    render() {
        let {preventDefault} = this.props;
        let {onChange} = this.props.formMethods;

        return (
            <Fragment>

                <a href="#" i d="button-insertLink-textEditor" data-toggle="modal"
                   data-target="#modal-insertLink-textEditor"
                   title="Insert Link" onClick={preventDefault}><i
                    className="material-icons">insert_link</i>
                </a>

                <div className={'modal fade ' + this.state.modal} id="modal-insertLink-textEditor" tabIndex="-1"
                     role="dialog"
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
                                           onChange={onChange} name='link'
                                           placeholder="Example: https://youtube.com..."/>
                                </div>
                            </div>

                            <div className="modal-footer">
                                <button type="button" className="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                <button id="button-proceedInsertLink-textEditor" type="button" data-dismiss="modal"
                                        className="btn btn-primary" onClick={this.proceedInsertLink}>Insert
                                </button>
                            </div>

                        </div>
                    </div>
                </div>
            </Fragment>
        );
    }
}

export default FormHoc(CommandExecutorHoc(TextEditorInsertLinkModal));