import React, {Component} from 'react';
import formatDate from 'dateformat';
import Interweave from "interweave";
import ThreadCreatorInformation from "./components/ThreadCreatorInformation";
import ThreadInformation from "./components/ThreadInformation";
import ThreadButtonsLeft from "./components/ThreadButtonsLeft";
import ThreadButtonsRight from "./components/ThreadButtonsRight";
import withState from "../../../../hocs/with.state";

class ThreadMainContent extends Component {

    render() {
        let {content} = this.props.threadRead;

        return (
            <div className="card thread">

                <div className="card-header">
                    <ThreadCreatorInformation/>
                </div>

                <div className="card-body">

                    <ThreadInformation/>

                    <div className="dropdown-divider"/>

                    <div className="content">
                        <p className="card-text thread-content">
                            <Interweave content={content}/>
                        </p>
                    </div>

                    <div className="row thread-random_separation">
                        <div className="col">
                            <div className="btn-group thread-left_side_buttons">
                                <ThreadButtonsLeft/>
                            </div>
                        </div>
                        <div className="col">
                            <div className="btn-group thread-right_side_buttons">
                                <ThreadButtonsRight/>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        );
    }
}

export default withState(ThreadMainContent);