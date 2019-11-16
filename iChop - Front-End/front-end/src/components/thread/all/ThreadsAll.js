import React, {Component} from 'react';
import './ThreadsAll.css';
import '../read/ThreadRead.css';
import formatDate from 'dateformat'
import PaginationNav from "../../other/PaginationNav";
import RoutingURLs from "../../../constants/routing/routing.constants";
import CreateReactClass from "create-react-class";
import ThreadsAllThread from "./ThreadsAllThread";
import withState from "../../../hocs/with.state";
import threadDispatchers from "../../../redux/dispatchers/thread.dispatchers";
import {connect} from "react-redux";

class ThreadsAll extends Component {

    constructor(props) {
        super(props);

        this.iterateThreads = this.iterateThreads.bind(this);
    }

    iterateThreads() {
        let {threads} = this.props.threadsAll;

        if (threads.length === 0) {
            return (<span>There are no news!</span>);
        }

        return threads.map((thread, index) => {
            let {id, title, createdOn, creatorUsername, postTime, totalViews, totalReactions, totalComments, content} = thread;

            return (
                <ThreadsAllThread id={id}
                                  title={title}
                                  createdOn={createdOn}
                                  creatorUsername={creatorUsername}
                                  postTime={postTime}
                                  totalViews={totalViews}
                                  totalReactions={totalReactions}
                                  totalComments={totalComments}
                                  content={content}/>
            )
        });
    }

    componentDidMount() {
        /*TODO: page and size*/
        this.props.fetchAllPageable(0,5);
        /*let threads = [
            {
                id: 'id1',
                title: 'Welcome',
                createdOn: formatDate(new Date(), 'dd mmm,yyyy'),
                creatorUsername: 'ItsGosho',
                postTime: formatDate(new Date(), 'HH:mm'),
                totalViews: 10,
                totalReactions: 5,
                totalComments: 3,
                content: '<h1>Welcome</h1><b>tesssssssssssssst!</b>'
            }
        ];*/
    }

    render() {
        let {threads} = this.props.threadsAll;

        return (
            <Threads>

                <div className="card-header">
                    <small>📰</small>
                    <span>News:</span>
                </div>

                {this.iterateThreads()}

                <div className="dropdown-divider"/>

                <PaginationNav totalResults={threads.length}
                               resultsPerPage={10}
                               redirectPage={RoutingURLs.HOME}/>

            </Threads>
        );
    }

}

let mapState = (state) => {
    return {...state};
};

export default connect(mapState,threadDispatchers)(ThreadsAll);


const Threads = CreateReactClass({
    render() {
        return (
            <div className="d-flex justify-content-center">
                <div className="col-md-auto width-75-percent">
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