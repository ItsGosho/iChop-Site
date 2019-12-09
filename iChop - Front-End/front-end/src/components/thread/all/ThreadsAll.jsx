import React, {Component} from 'react';
import './ThreadsAll.css';
import '../read/ThreadRead.css';
import ThreadsAllThread from "./ThreadsAllThread";
import threadAllDispatchers from "../../../redux/dispatchers/thread.all.dispatchers";
import PaginationNav from "../../other/PaginationNav";
import withDispatcher from "../../../hocs/with.dispatcher";

class ThreadsAll extends Component {

    constructor(props) {
        super(props);

        this.state = {
            resultsPerPage: 5
        };

        this.iterateThreads = this.iterateThreads.bind(this);
        this.onPageChange = this.onPageChange.bind(this);
    }

    iterateThreads() {
        let {threads} = this.props.threadsAll;

        if (threads.length === 0) {
            return (<span>There are no news!</span>);
        }

        return threads.map(({id, title, createdOn, creatorUsername, postTime, totalViews, totalReactions, totalComments, content}, index) => (
            <ThreadsAllThread id={id}
                              title={title}
                              createdOn={createdOn}
                              creatorUsername={creatorUsername}
                              postTime={postTime}
                              totalViews={totalViews}
                              totalReactions={totalReactions}
                              totalComments={totalComments}
                              content={content}/>
        ));
    }

    onPageChange(obj) {
        let page = obj.selected;
        let {resultsPerPage} = this.state;
        this.props.fetchAllPageable(page, resultsPerPage);
    }

    componentDidMount() {
        let {resultsPerPage} = this.state;
        this.props.fetchAllPageable(0, resultsPerPage);
        this.props.fetchTotal();
    }

    render() {
        let {resultsPerPage} = this.state;
        let {total} = this.props.threadsAll;

        return (
            <Threads>

                <div className="card-header">
                    <small>📰</small>
                    <span>News:</span>
                </div>

                {this.iterateThreads()}

                <div className="dropdown-divider"/>

                {total > 0 ? (
                    <PaginationNav total={total}
                                   resultsPerPage={resultsPerPage}
                                   onPageChange={this.onPageChange}/>
                ) : null}

            </Threads>
        );
    }

}


export default withDispatcher(threadAllDispatchers)(ThreadsAll);


const Threads = ({children}) => (
    <div className="d-flex justify-content-center">
        <div className="col-md-auto width-75-percent">
            <div className="col-xs-6">
                <div className="card">
                    {children}
                </div>
            </div>
        </div>
    </div>
);