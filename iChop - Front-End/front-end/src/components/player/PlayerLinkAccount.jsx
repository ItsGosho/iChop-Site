import React, {Component, Fragment} from 'react';
import './PlayerLinkAccount.css'
import {Link, Redirect} from "react-router-dom";
import RoutingURLs from "../../constants/routing/routing.constants";
import ServerRoutingURLs from "../../constants/routing/server.routing.urls";
import LinkAccountServices from "../../services/link_account.services";
import NotificationHelper from "../../helpers/notification.helper";
import QueryHelper from "../../helpers/query.helper";

class PlayerLinkAccount extends Component {

    constructor(props) {
        super(props);

        this.state = {
            redirectToHome: false,
            playerName: ''
        };

        this.linkAccount = this.linkAccount.bind(this);
        this.queryHelper = new QueryHelper(this.props);
    }

    componentWillUpdate(nextProps, nextState, nextContext) {
        this.queryHelper = new QueryHelper(nextProps);
    }

    async componentDidMount() {
        let key = this.queryHelper.getQueryParam(QueryParams.KEY);

        let isKeyInvalid = await LinkAccountServices.isKeyValid(key);
        this.setState({redirectToHome: !isKeyInvalid});

        if(!isKeyInvalid){
            NotificationHelper.showErrorNotification('Invalid key!');
            return;
        }

        let {playerName} = await LinkAccountServices.retrieveKey(key);
        this.setState({playerName})
    }

    async linkAccount() {
        let key = this.queryHelper.getQueryParam(QueryParams.KEY);

        let response = await LinkAccountServices.link(key);

        if(response.successful){
            this.setState({redirectToHome: true})
        }
    }

    render() {
        let {playerName, redirectToHome} = this.state;
        let avatarUrl = ServerRoutingURLs.OUTSIDE.MINOTAR.MINECRAFT.HEAD(playerName);

        return (
            <Fragment>
                <div className="container d-flex justify-content-center align-items-center"
                     id="div-container-player_link_account">
                    <div className="card" id="div-card-player_link_account">

                        <div className="card-header">
                            <span>Link your account:</span>
                        </div>

                        <div className="card-body">

                            <p className="card-text">Are you sure that you want to link
                                <img id="img-player_head-player_link_account" src={avatarUrl} alt=''/>
                                <b id="b-minecraft_name-player_link_account">{' ' + playerName}</b>
                                <span> to your account?</span>
                            </p>

                            <div className="float-right">
                                <Link to={RoutingURLs.HOME}>
                                    <button className="btn btn-danger">No</button>
                                </Link>
                            </div>

                            <div className="float-left">
                                    <button className="btn btn-warning" onClick={this.linkAccount}>Yes</button>
                            </div>

                        </div>

                    </div>
                </div>
                {redirectToHome ? <Redirect to={RoutingURLs.HOME} push/> : null}
            </Fragment>
        );
    }

}

export default PlayerLinkAccount;

const QueryParams = {
    KEY: 'key'
};