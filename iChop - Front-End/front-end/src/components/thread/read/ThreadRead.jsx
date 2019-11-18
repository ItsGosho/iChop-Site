import React, {Component} from 'react';
import ThreadMainContent from "./main/ThreadMainContent";
import ThreadComments from "./comments/ThreadComments";
import './ThreadRead.css'
import {compose} from "redux";
import {connect} from "react-redux";
import threadReadDispatchers from "../../../redux/dispatchers/thread.read.dispatchers";

class ThreadRead extends Component {

    async componentDidMount() {
        let {id} = this.props.match.params;

       this.props.fetchById(id);
    }

    render() {
        return (
            <div className="container d-flex justify-content-center align-items-center">
                <div className="row">
                    <div className="col-xs-6">

                        <ThreadMainContent/>
                        {/*<ThreadAddComment/>*/}
                        <ThreadComments/>

                    </div>
                </div>
            </div>
        );
    }

}

export default compose(
    connect(null,threadReadDispatchers)
)(ThreadRead);