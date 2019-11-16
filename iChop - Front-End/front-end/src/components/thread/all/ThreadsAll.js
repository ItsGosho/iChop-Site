import React, {Component} from 'react';
import './ThreadsAll.css';
import '../read/ThreadRead.css';
import PaginationNav from "../../other/PaginationNav";
import RoutingURLs from "../../../constants/routing/routing.constants";
import CreateReactClass from "create-react-class";
import ThreadsAllThread from "./ThreadsAllThread";
import threadDispatchers from "../../../redux/dispatchers/thread.dispatchers";
import {connect} from "react-redux";
import ReactPaginate from 'react-paginate';

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
        this.props.fetchAllPageable(0, 5);
        this.props.fetchTotal();
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
        let {threads, total} = this.props.threadsAll;
        let pageCount = total / 5;

        return (
            <Threads>

                <div className="card-header">
                    <small>📰</small>
                    <span>News:</span>
                </div>

                {this.iterateThreads()}

                <div className="dropdown-divider"/>

                {/*<PaginationNav totalResults={threads.length}
                               resultsPerPage={1}
                               redirectPage={RoutingURLs.HOME}/>*/}

                <ReactPaginate
                    previousLabel={'previous'}
                    nextLabel={'next'}
                    breakLabel={'...'}
                    pageCount={pageCount}/*
                    marginPagesDisplayed={2}
                    pageRangeDisplayed={5}*/
                    onPageChange={this.handlePageClick}
                    breakClassName={'page-item'}
                    breakLinkClassName={'page-link'}
                    containerClassName={'pagination justify-content-center'}
                    pageClassName={'page-item'}
                    pageLinkClassName={'page-link'}
                    previousClassName={'page-item'}
                    previousLinkClassName={'page-link'}
                    nextClassName={'page-item'}
                    nextLinkClassName={'page-link'}
                    activeClassName={'active'}/>

            </Threads>
        );
    }

}

let mapState = (state) => {
    return {...state};
};

export default connect(mapState, threadDispatchers)(ThreadsAll);


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