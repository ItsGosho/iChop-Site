import React, {Component} from 'react';
import TextEditor from "../editor/TextEditor";
import './ThreadCreate.css'
import ThreadCreateHelperButton from "./ThreadCreateHelperButton";

class ThreadCreate extends Component {

    constructor(props) {
        super(props);

        this.onCreate = this.onCreate.bind(this);

        this.titleRef = React.createRef();
        this.contentRef = React.createRef();
    }


    onCreate() {
        let title = this.titleRef.current.value;
        let content = this.contentRef.current.innerHTML;

        console.log(title);
        console.log(content);
    }


    render() {

        return (
            <div id="createThread-page" className="container d-flex justify-content-center align-items-center">
                <div className="row">

                    <div id="news" className="col-xs-6">
                        <div className="card">

                            <div className="card-header">
                                <small>🆕</small>
                                <span>Create a new thread:</span>
                            </div>

                            <div className="card-body">
                                <form>

                                    <div className="row">
                                        <div className="col-md-12">
                                            <div className="input-group mb-3">
                                                <input id="input-title-threadCreate" type="text"
                                                       className="form-control" aria-label="Title"
                                                       aria-describedby="basic-addon1" name="title"
                                                       placeholder="Thread title" ref={this.titleRef}/>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="dropdown-divider"/>

                                    <TextEditor/>

                                    <div id="textarea-content" contentEditable="true" ref={this.contentRef}/>

                                    <div className="dropdown-divider"/>

                                    <div className="col-md-13">

                                        <ThreadCreateHelperButton/>

                                    </div>

                                    <div className="dropdown-divider"/>

                                    <div className="row">
                                        <button id="button-proceedCreateThread-createThread" type="button"
                                                className="btn btn-success btn-lg btn-block"
                                                onClick={this.onCreate}>Create
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