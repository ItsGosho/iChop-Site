import React, {Component} from 'react';
import './ThreadCreate.css'
import ThreadCreateHelpModal from "./ThreadCreateHelpModal";
import ModalOpen from "../../modal/ModalOpen";
import CreateReactClass from "create-react-class";
import HTMLEditor from "../../editors/HTMLEditor";
import ThreadServices from "../../../services/thread.services";
import NotificationHelper from "../../../helpers/notification.helper";
import {Redirect} from "react-router-dom";
import RoutingURLs from "../../../constants/routing/routing.constants";

class ThreadCreate extends Component {

    constructor(props) {
        super(props);

        this.state = {
            title: '',
            content: '',
            isSuccessful: false
        };

        this.onCreate = this.onCreate.bind(this);
        this.onContentUpdate = this.onContentUpdate.bind(this);
    }

    onContentUpdate(content) {
        this.setState({content})
    }

    async onCreate() {
        let {title, content} = this.state;

        let response = await ThreadServices.create(title, content);
        NotificationHelper.showNotificationByResponse(response);

        if (response.successful) {
            this.setState({isSuccessful: true})
        }
    }


    render() {
        let {isSuccessful} = this.state;

        return (
            <CreateCard>
                <div className="card-header">
                    <small>🆕</small>
                    <span>Create a new thread:</span>
                </div>

                <div className="card-body">
                    <form>

                        <div className="input-group mb-3">
                            <input id="input-title-threadCreate"
                                   className="form-control"
                                   name="title"
                                   placeholder="Thread title"
                                   onChange={(event) => {
                                       this.setState({title: event.target.value})
                                   }}/>
                        </div>

                        <div className="dropdown-divider"/>

                        <HTMLEditor onChangeHTML={this.onContentUpdate}/>

                        <div id="textarea-content" contentEditable="true"/>

                        <ModalOpen relationTo={'help'} title={'Help'}>
                            <span>❓</span>
                        </ModalOpen>

                        <ThreadCreateHelpModal/>

                        <div className="dropdown-divider"/>

                        <div className="row">
                            <button type="button"
                                    className="btn btn-warning btn-lg btn-block"
                                    onClick={this.onCreate}>Create
                            </button>
                        </div>

                    </form>
                </div>
                {isSuccessful ? <Redirect to={RoutingURLs.HOME} push/> : null}
            </CreateCard>
        );
    }
}


export default ThreadCreate;


const CreateCard = CreateReactClass({
    render() {
        return (
            <div className="container d-flex justify-content-center align-items-center">
                <div className="row">
                    <div className="col-xs-6">
                        <div className="card">
                            {this.props.children}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
});