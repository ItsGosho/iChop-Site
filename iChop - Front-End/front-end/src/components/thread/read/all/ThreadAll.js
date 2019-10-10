import React, {Component} from 'react';
import './ThreadAll.css';
import formatDate from 'dateformat'
import PaginationNav from "../../../report/PaginationNav";
import RoutingURLs from "../../../../constants/routing.constants";
import ThreadAllInformation from "./ThreadAllInformation";
import Interweave from "interweave";

class ThreadAll extends Component {


    render() {
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

        return (
            <div id="threads" className="col-xs-6">
                <div className="card">
                    <div className="card-header">
                        <small>📰</small>
                        News:
                    </div>


                    {
                        (() => {
                            if (threads.length > 0) {
                                threads.map((thread, index) => {

                                    let {id, title, createdOn, creatorUsername, postTime, totalViews, totalReactions, totalComments, content} = thread;

                                    return (
                                        <div className="card-body">
                                            <ThreadAllInformation id={id}
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
                                                        <button
                                                            sec:authorize="isAuthenticated() && hasAuthority('MODERATOR')"
                                                            className="btn btn-secondary btn-sm dropdown-toggle"
                                                            type="button"
                                                            data-toggle="dropdown" aria-haspopup="true"
                                                            aria-expanded="false">
                                                            <small>⚙</small>
                                                            Options
                                                        </button>
                                                        <div className="dropdown-menu">
                                                            <form className="dropdown-item"
                                                                  th:action="@{/thread/{id}/delete(id=*{id})}"
                                                                  method="post">
                                                                <button
                                                                    sec:authorize="isAuthenticated() && hasAuthority('MODERATOR')"
                                                                    type="submit"
                                                                    className="btn btn-light btn-sm thread-delete_button">
                                                                    <small>❌</small>
                                                                    Delete
                                                                </button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="col-md-4">
                                                    <a className="dropdown-item"
                                                       th:href="@{/thread/{id}/read(id=*{id})}">
                                                        <button type="button"
                                                                className="btn btn-primary btn-sm btn-brand btn-reddit continueReading">
                                                            <small>📖</small>
                                                            Continue reading...
                                                        </button>
                                                    </a>
                                                </div>

                                            </div>
                                            <div className="dropdown-divider"/>
                                        </div>
                                    );
                                })
                            } else {
                                return (<span>There are no news!</span>);
                            }
                        })()
                    }

                    <div className="dropdown-divider"/>

                    <PaginationNav totalResults={threads.length} resultsPerPage={10} redirectPage={RoutingURLs.HOME}/>

                </div>
            </div>

        );
    }

}

export default ThreadAll;