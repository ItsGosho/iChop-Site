import React, {Component} from 'react';
import './ThreadsAll.css';
import formatDate from 'dateformat'
import PaginationNav from "../../other/PaginationNav";
import RoutingURLs from "../../../constants/routing.constants";
import CreateReactClass from "create-react-class";
import ThreadsAllThread from "./ThreadsAllThread";

class ThreadsAll extends Component {

    constructor(props) {
        super(props);

        this.state = {
            threads: []
        };

        this.iterateThreads = this.iterateThreads.bind(this);
    }

    iterateThreads() {
        let {threads} = this.state;

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