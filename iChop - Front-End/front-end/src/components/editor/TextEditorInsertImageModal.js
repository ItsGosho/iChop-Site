import React, {Component, Fragment} from 'react';
import TextEditorCommands from "./text.editor.commands.constants";
import FormHoc from "../../hocs/form.hoc";
import CommandExecutorHoc from "./command.executor.hoc";

class TextEditorInsertImageModal extends Component {

    constructor(props) {
        super(props);

        this.state = {
            modal: ''
        };

        this.proceedInsertImage = this.proceedInsertImage.bind(this);
    }

    proceedInsertImage(event) {
        event.preventDefault();
        let {link} = this.props.formData;

        document.execCommand(TextEditorCommands.INSERT_IMAGE, false, link);
        this.setState({'modal': 'hide'});

        try {
            let image = document.querySelector(`img[src='${link}']`);
            image.style['height'] = '100%';
            image.style['width'] = '100%';
            image.style['object-fit'] = 'contain';
        } catch (e) {

        }
    }

    render() {
        let {preventDefault} = this.props;
        let {onChange} = this.props.formMethods;

        return (
            <Fragment>

                <a href="#" id="button-insertImage-textEditor" data-toggle="modal"
                   data-target="#modal-insertImage-textEditor"
                   title="Insert Image" onClick={preventDefault}>
                    <i className="material-icons">photo</i>
                </a>

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
                                           onChange={onChange} name='link'
                                           aria-describedby="basic-addon1" placeholder=""/>
                                </div>
                            </div>

                            <div className="modal-footer">
                                <button type="button" className="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                <button id="button-proceedInsertImage-textEditor" type="button" data-dismiss="modal"
                                        className="btn btn-primary" onClick={this.proceedInsertImage}>Insert
                                </button>
                            </div>

                        </div>
                    </div>

                </div>
            </Fragment>
        );
    }
}

export default FormHoc(CommandExecutorHoc(TextEditorInsertImageModal));