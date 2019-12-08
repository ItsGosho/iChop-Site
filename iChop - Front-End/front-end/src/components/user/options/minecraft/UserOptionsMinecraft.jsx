import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../../constants/routing/routing.constants";
import {Link} from "react-router-dom";
import ServerRoutingURLs from "../../../../constants/routing/server.routing.urls";
import './UserOptionsMinecraft.css'
import LinkAccountServices from "../../../../services/link_account.services";
import withDispatchers from "../../../../hocs/with.dispatchers";

class UserOptionsMinecraft extends Component {

    constructor(props) {
        super(props);

        this.onUnlink = this.onUnlink.bind(this);
    }

    async onUnlink() {
        let {username} = this.props.authenticatedUserInfo;
        let response = await LinkAccountServices.unlink(username);

        if (response.successful) {
            this.props.fetchAuthenticatedUserInfo();
        }
    }


    render() {
        let {playerName, playerUUID} = this.props.authenticatedUserInfo;

        return (
            <Fragment>
                {!playerName ? (<Fragment>
                        <div className="dropdown-divider"/>

                        <div className="row" align="center">
                            <div className="col-lg">
                                <span>You have not linked account yet!</span>
                            </div>
                        </div>

                        <div className="dropdown-divider"/>

                        <div className="row">
                            <div className="col-lg">
                                <br></br>
                                <span>I.Type in the server <b>/link-account</b>.</span>
                                <br></br>
                                <span>II.Open the generated <b>link</b>.</span>
                                <br></br>
                                <span>III.Then <b>confirm</b> that you want to link your account.</span>
                            </div>
                        </div>

                        <div className="dropdown-divider"/>
                    </Fragment>) :
                    (<Fragment>
                        <div className="row" align="center">
                            <div className="col-lg">
                                <b><Link to={RoutingURLs.PLAYER.PROFILE.VIEW(playerUUID)}>{playerName}</Link></b>
                            </div>
                        </div>

                        <div className="dropdown-divider"/>

                        <div align="center">
                            <img src={ServerRoutingURLs.OUTSIDE.CRAFATAR.MINECRAFT.SKIN(playerUUID)}
                                 className="skin"
                                 alt=''/>
                        </div>

                        <div className="dropdown-divider"/>

                        <div align="center" className="unlink-button-holder">

                            <button type="button"
                                    className="btn btn-warning"
                                    onClick={this.onUnlink}>
                                Unlink
                            </button>

                        </div>
                    </Fragment>)
                }
            </Fragment>
        );
    }

}

export default withDispatchers(UserOptionsMinecraft);