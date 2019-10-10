import React, {Component} from 'react';
import formatDate from 'dateformat';
import Interweave from "interweave";
import ThreadCreatorInformation from "./ThreadCreatorInformation";
import ThreadInformation from "./ThreadInformation";
import ThreadButtonsLeft from "./ThreadButtonsLeft";
import ThreadButtonsRight from "./ThreadButtonsRight";

class ThreadReadMainContent extends Component {

    render() {
        let threadId = 'threadId123';
        let creatorUsername = 'ItsGosho';
        let creatorTotalComments = 15;
        let title = 'Abra kadabra';
        let createdOn = formatDate(new Date(), 'dd mmm,yyyy');
        let postedAt = formatDate(new Date(), 'HH:mm');
        let totalViews = 45;
        let totalReactions = 15;
        let totalComments = 3;
        let content = '<center><h1>Hi!</h1></center>';

        let uuid = '8ed20904-3262-401a-901a-1946504d2eea';
        let creatorMinecraftAccountName = 'ItsGosho';

        return (
            <div className="card thread">

                <div className="card-header">
                    <ThreadCreatorInformation uuid={uuid}
                                              username={creatorUsername}
                                              totalComments={creatorTotalComments}
                                              minecraftAccountName={creatorMinecraftAccountName}/>
                </div>

                <div className="card-body">

                    <ThreadInformation
                        id={threadId}
                        title={title}
                        createdOn={createdOn}
                        postedAt={postedAt}
                        totalViews={totalViews}
                        totalReactions={totalReactions}
                        totalComments={totalComments}/>

                    <div className="dropdown-divider"/>

                    <div className="content">
                        <p className="card-text thread-content">
                            <Interweave content={content}/>
                        </p>
                    </div>


                    <div className="row">
                        <div className="col-md-8 thread-random_separation"/>

                        <div className="row">
                            <div className="col-md-12">
                                <div className="btn-group thread-left_side_buttons">
                                    <ThreadButtonsLeft/>
                                </div>
                                <div className="btn-group thread-right_side_buttons">
                                    <ThreadButtonsRight/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default ThreadReadMainContent;