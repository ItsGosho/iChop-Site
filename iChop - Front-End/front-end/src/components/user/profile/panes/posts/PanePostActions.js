import React, {Component} from 'react';
import PanePostReportModal from "./PanePostReportModal";
import dateFormat from 'dateformat';
import Roles from "../../../../../constants/roles.constants";

class PanePostActions extends Component {

    render() {
        let createdOn = dateFormat(new Date(), 'MMM dd,yyyy');
        let username = 'ItsGosho';

        let postUserUsername = 'Roki';
        let postCreatorUsername = 'Joni';
        let role = Roles.MODERATOR;

        return (
            <div className="col-md-12">
                <span style={{'color': '#7f7f7f', 'fontSize': '10px'}}>{createdOn}</span>
                
                {
                    (() => {
                        let isPostCreator = postCreatorUsername === username;
                        let isPostOnHisProfile = postUserUsername === username;
                        let isModerator = role === Roles.MODERATOR;

                        if (isPostCreator || isPostOnHisProfile || isModerator) {
                            return (
                                <button type="button" style={{all: 'initial', 'cursor': 'pointer'}}>
                                    <span style={{'fontSize': '10px', 'color': '#007bff'}}>❌Delete</span>
                                </button>
                            );
                        }
                    })()
                }

                <button type="button" style={{all: 'initial', 'cursor': 'pointer'}}>
                    <span style={{'fontSize': '10px', 'color': '#007bff'}}>🎌Report</span>
                </button>

                <PanePostReportModal/>
            </div>
        );
    }
}

export default PanePostActions;