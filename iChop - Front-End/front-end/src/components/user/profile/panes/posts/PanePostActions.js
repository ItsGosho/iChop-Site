import React, {Component} from 'react';
import dateFormat from 'dateformat';
import Roles from "../../../../../constants/roles.constants";
import './PanePostActions.css'

class PanePostActions extends Component {

    constructor(props) {
        super(props);

        this.onDelete = this.onDelete.bind(this);
        this.onReport = this.onReport.bind(this);
    }


    onDelete(post) {
        console.log('Delete!');
    }

    onReport(post) {
        console.log('Report!');
    }

    render() {
        let createdOn = dateFormat(new Date(), 'dd mmm, yyyy');
        let username = 'ItsGosho';

        let postUserUsername = 'Roki';
        let postCreatorUsername = 'Joni';
        let role = Roles.MODERATOR;
        
        let post = null;

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
                                <button className="control-button" onClick={this.onDelete(post)}>❌Delete</button>
                            );
                        }
                    })()
                }

                <button className="control-button" onClick={this.onReport(post)}>🎌Report</button>

            </div>
        );
    }
}

export default PanePostActions;