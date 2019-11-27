import React, {Component} from 'react';
import ThreadMainContent from "./main/ThreadMainContent";
import ThreadComments from "./comments/ThreadComments";
import './ThreadRead.css'
import threadReadDispatchers from "../../../redux/dispatchers/thread.read.dispatchers";
import ThreadAddComment from "./comments/ThreadAddComment";
import withDispatcher from "../../../hocs/with.dispatcher";


class ThreadRead extends Component {

    async componentDidMount() {
        let {id} = this.props.match.params;

       this.props.fetchThreadById(id);
    }

    render() {
        return (
            <div className="container d-flex justify-content-center align-items-center">
                <div className="row">
                    <div className="col-xs-6">

                        <ThreadMainContent/>
                        <ThreadAddComment/>
                        <ThreadComments/>

                    </div>
                </div>
            </div>
        );
    }

}

export default withDispatcher(threadReadDispatchers)(ThreadRead);