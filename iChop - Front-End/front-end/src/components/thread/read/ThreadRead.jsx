import React, {Component} from 'react';
import ThreadMainContent from "./main/ThreadMainContent";
import ThreadComments from "./comments/ThreadComments";
import './ThreadRead.css'
import {withRouter} from "react-router-dom";
import ThreadAddComment from "./comments/ThreadAddComment";
import ThreadServices from "../../../services/thread.services";
import UserServices from "../../../services/user.services";
import CommentServices from "../../../services/comment.services";

class ThreadRead extends Component {

    async componentDidMount() {
        let {id} = this.props.match.params;
        let thread = await ThreadServices.findById(id);
        let creator = await UserServices.findByUsername(thread.creatorUsername);
        let comments = await CommentServices.findAllThreadComments(id);

        console.log(thread);
        console.log(creator);
        console.log(comments);



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