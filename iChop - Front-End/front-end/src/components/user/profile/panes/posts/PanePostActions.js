import React, {Component} from 'react';
import dateFormat from 'dateformat';
import Roles from "../../../../../constants/roles.constants";
import './PanePostActions.css'

class PanePostActions extends Component {

    render() {
        let createdOn = dateFormat(new Date(), 'MMM dd,yyyy');
        let username = 'ItsGosho';

        let postUserUsername = 'Roki';
        let postCreatorUsername = 'Joni';
        let role = Roles.MODERATOR;

        return (
            <div className="col-md-12">
                <span className="post-createdOn">{createdOn}</span>

                {
                    (() => {
                        let isPostCreator = postCreatorUsername === username;
                        let isPostOnHisProfile = postUserUsername === username;
                        let isModerator = role === Roles.MODERATOR;

                        if (isPostCreator || isPostOnHisProfile || isModerator) {
                            return (
                                <button className="control-button">❌Delete</button>
                            );
                        }
                    })()
                }

                <button className="control-button">🎌Report</button>

            </div>
        );
    }
}

export default PanePostActions;