import React, {Component} from 'react';
import ThreadReadMainContent from "./ThreadReadMainContent";
import ThreadReadAddComment from "./ThreadReadAddComment";
import ThreadReadComments from "./ThreadReadComments";
import './ThreadRead.css'

class ThreadRead extends Component {


    render() {

        return (
            <div id="thread-view" className="container d-flex justify-content-center align-items-center">
                <div className="row">
                    <div className="col-xs-6">

                        <ThreadReadMainContent/>
                        <ThreadReadAddComment/>
                        {/*<ThreadReadComments/>*/}

                    </div>
                </div>
            </div>
        );
    }

}

export default ThreadRead;