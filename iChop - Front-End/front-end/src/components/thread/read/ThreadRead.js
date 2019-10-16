import React, {Component} from 'react';
import ThreadMainContent from "./main/ThreadMainContent";
import ThreadComments from "./comments/ThreadComments";
import './ThreadRead.css'

class ThreadRead extends Component {


    render() {

        return (
            <div id="thread-view" className="container d-flex justify-content-center align-items-center">
                <div className="row">
                    <div className="col-xs-6">

                        <ThreadMainContent/>
                        {/*<ThreadReadAddComment/>*/}
                        <ThreadComments/>

                    </div>
                </div>
            </div>
        );
    }

}

export default ThreadRead;