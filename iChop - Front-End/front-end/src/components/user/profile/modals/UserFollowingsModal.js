import React, {Component} from 'react';
import Image from "../../../other/Image";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";

class UserFollowingsModal extends Component {

    render() {

        return (
            <div className="modal modal-all-following" tabIndex="-1" role="dialog">
                <div className="modal-dialog" role="document">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">All users that <span><b>ItsGosho</b></span> is
                                following:</h5>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div id="div-insert_followings-user_followings_all" className="modal-body"
                             style={{'overflow': 'auto', 'maxHeight': '400px'}}>

                            <div className="row">
                                <div className="w-100" style={{'marginTop':'10px'}}>

                                    <Image url={'/'}
                                           defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                                           style={{'width': '50px', 'height': '50px', 'marginLeft': '15px'}}/>

                                    <span style={{'marginLeft': '15px'}}><b><a href="{profileURL}">Joreto</a></b></span>
                                    <button className="btn btn-warning btn-sm float-right" style={{
                                        'fontFamily': 'Consolas',
                                        'marginTop': '15px',
                                        'marginRight': '15px'
                                    }}>
                                        Unfollow
                                    </button>
                                </div>
                                <div className="w-100" style={{'marginTop':'10px'}}>

                                    <Image url={'/'}
                                           defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                                           style={{'width': '50px', 'height': '50px', 'marginLeft': '15px'}}/>

                                    <span style={{'marginLeft': '15px'}}><b><a href="{profileURL}">Joreto</a></b></span>
                                    <button className="btn btn-warning btn-sm float-right" style={{
                                        'fontFamily': 'Consolas',
                                        'marginTop': '15px',
                                        'marginRight': '15px'
                                    }}>
                                        Unfollow
                                    </button>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default UserFollowingsModal;