import React from 'react';
import Interweave from "interweave";
import ThreadCreatorInformation from "./components/ThreadCreatorInformation";
import ThreadInformation from "./components/ThreadInformation";
import ThreadButtonsLeft from "./components/ThreadButtonsLeft";
import ThreadButtonsRight from "./components/ThreadButtonsRight";
import withState from "../../../../hocs/with.state";

const REMOVE_KEYWORD = 'SHOW_TO_NOW';

const ThreadMainContent = (props) => {
    let {content} = props.threadRead;
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
                        <Interweave content={removeTheHiddenKeyword(content)}/>
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
};

export default withState(ThreadMainContent);

let removeTheHiddenKeyword = (content) => {
    return content.indexOf(REMOVE_KEYWORD) !== -1
        ?  content.replace(REMOVE_KEYWORD,'')
        : content;
};