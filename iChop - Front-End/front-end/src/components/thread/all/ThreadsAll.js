import React, {Component} from 'react';
import './ThreadsAll.css';
import formatDate from 'dateformat'
import PaginationNav from "../../other/PaginationNav";
import RoutingURLs from "../../../constants/routing.constants";
import ThreadsAllInformation from "./ThreadsAllInformation";
import Interweave from "interweave";
import ThreadsAllOptionsDropdown from "./ThreadsAllOptionsDropdown";
import {Link} from "react-router-dom";
import CreateReactClass from "create-react-class";

class ThreadsAll extends Component {

    constructor(props) {
        super(props);

        this.state = {
            threads: []
        };

        this.iterateThreads = this.iterateThreads.bind(this);
    }

    iterateThreads() {
        return this.state.threads.map((thread, index) => {

            let {id, title, createdOn, creatorUsername, postTime, totalViews, totalReactions, totalComments, content} = thread;
            let threadReadUrl = RoutingURLs.THREAD.VIEW.replace(':id', id);

            return (
                <div className="card-body">
                    <ThreadsAllInformation id={id}
                                           title={title}
                                           createdOn={createdOn}
                                           username={creatorUsername}
                                           postTime={postTime}
                                           totalViews={totalViews}
                                           totalReactions={totalReactions}
                                           totalComments={totalComments}/>

                    <div className="dropdown-divider"/>

                    <div className="content thread-content">
                        <p className="card-text">
                            <Interweave content={content}/>
                        </p>
                    </div>


                    <div className="dropdown-divider"/>

                    <div className="row">
                        <div className="col-md-8">
                            <div className="btn-group">
                                <ThreadsAllOptionsDropdown/>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <Link className="dropdown-item"
                                  to={threadReadUrl}>
                                <button type="button"
                                        className="btn btn-primary btn-sm btn-brand btn-reddit continueReading">
                                    <small>📖</small>
                                    <span>Continue reading...</span>
                                </button>
                            </Link>
                        </div>

                    </div>
                    <div className="dropdown-divider"/>
                </div>
            )
        });
    }

    componentDidMount() {
        let threads = [
            {
                id: 'id1',
                title: 'Welcome',
                createdOn: formatDate(new Date(), 'dd mmm,yyyy'),
                creatorUsername: 'ItsGosho',
                postTime: formatDate(new Date(), 'HH:mm'),
                totalViews: 10,
                totalReactions: 5,
                totalComments: 3,
                content: '<h1>Welcome</h1>'
            },
            {
                id: 'id2',
                title: 'Ooops',
                createdOn: formatDate(new Date(), 'dd mmm,yyyy'),
                creatorUsername: 'Mariika',
                postTime: formatDate(new Date(), 'HH:mm'),
                totalViews: 3,
                totalReactions: 16,
                totalComments: 2,
                content: '<h1>Welcome 2</h1>'
            },
            {
                id: 'id3',
                title: 'Guten tag!',
                createdOn: formatDate(new Date(), 'dd mmm,yyyy'),
                creatorUsername: 'Ivancho',
                postTime: formatDate(new Date(), 'HH:mm'),
                totalViews: 4,
                totalReactions: 7,
                totalComments: 19,
                content: '<h1>Welcome 3</h1>'
            }
        ];

        this.setState({threads})
    }

    render() {
        let {threads} = this.state;

        return (
            <Threads>

                <div className="card-header">
                    <small>📰</small>
                    <span>News:</span>
                </div>

                {this.iterateThreads()}

                {threads.length === 0 ? (<span>There are no news!</span>) : null}

                <div className="dropdown-divider"/>

                <PaginationNav totalResults={threads.length}
                               resultsPerPage={10}
                               redirectPage={RoutingURLs.HOME}/>

            </Threads>
        );
    }

}

export default ThreadsAll;

const Threads = CreateReactClass({
    render() {
        return (
            <div className="d-flex justify-content-center">
                <div className="col-md-auto" style={{'width': '75%'}}>
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