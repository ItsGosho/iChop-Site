import React,{Component} from 'react';
import TextEditor from "../editor/TextEditor";

class ThreadCreate extends Component {

    render() {

        return (
            <div id="createThread-page" className="container d-flex justify-content-center align-items-center">
                <div className="row">

                    <div id="news" className="col-xs-6">
                        <div className="card">
                            <div className="card-header">
                                <small>🆕</small>
                                Create a new thread:
                            </div>
                            <div className="card-body">
                                <form method="post">
                                    <div className="row">
                                        <div className="col-md-12">
                                            <div className="input-group mb-3">
                                                <input id="input-title-threadCreate" type="text"
                                                       className="form-control" aria-label="Title"
                                                       aria-describedby="basic-addon1" name="title"
                                                       placeholder="Thread title"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="dropdown-divider"/>


                                    <TextEditor/>

                                    <div className="dropdown-divider"/>
                                    <div className="col-md-13">

                                        <a href="" type="button" data-toggle="modal" data-target="#exampleModal">
                                            ❓
                                        </a>
                                        <div className="modal fade" id="exampleModal" tabIndex="-1" role="dialog"
                                             aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div className="modal-dialog" role="document">
                                                <div className="modal-content">
                                                    <div className="modal-header">
                                                        <h5 className="modal-title" id="exampleModalLabel">How to show
                                                            only first X rows from thread on the news page:</h5>
                                                        <button type="button" className="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div className="modal-body">
                                                        If you dont want all of the content to be show on the news page
                                                        /better/ ,place SHOW_TO_NOW whether you want to end.
                                                    </div>
                                                    <div className="modal-footer">
                                                        <button type="button" className="btn btn-secondary"
                                                                data-dismiss="modal">Close
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div id="textarea-content-createThread" contentEditable="true">

                                        </div>
                                    </div>

                                    <div className="dropdown-divider"/>

                                    <div className="row">
                                        <button id="button-proceedCreateThread-createThread" type="button"
                                                className="btn btn-success btn-lg btn-block">Create
                                        </button>
                                    </div>

                                </form>
                            </div>

                            <div className="dropdown-divider"/>
                        </div>
                    </div>

                </div>
            </div>
        );
    }
}

export default ThreadCreate;