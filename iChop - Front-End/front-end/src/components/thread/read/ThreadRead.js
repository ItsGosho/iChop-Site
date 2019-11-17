import React, {Component} from 'react';
import ThreadMainContent from "./main/ThreadMainContent";
import ThreadComments from "./comments/ThreadComments";
import './ThreadRead.css'
import {withRouter} from "react-router-dom";
import ThreadAddComment from "./comments/ThreadAddComment";

class ThreadRead extends Component {

    componentDidMount() {
        let {id} = this.props.match.params;
        console.log(id);

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

export default withRouter(ThreadRead);