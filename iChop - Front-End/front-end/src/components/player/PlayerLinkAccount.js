import React,{Component} from 'react';
import './PlayerLinkAccount.css'

class CHANGE extends Component {


    render() {

        return (
            <div className="container d-flex justify-content-center align-items-center"
                 id="div-container-player_link_account">
                <div className="card" id="div-card-player_link_account">
                    <div className="card-header">
                        Link your account:
                    </div>
                    <div className="card-body">
                        <p className="card-text">Are you sure that you want to link <img
                            id="img-player_head-player_link_account"
                            th:src="@{https://minotar.net/avatar/{uuid}(uuid=${uuid})}">
                            <b id="b-minecraft_name-player_link_account" th:text="${name}">ItsGosho</b> to your account?
                        </p>
                        <div className="float-left">
                            <form id="form-confirm_player_link_connection-player_link_account" method="post" action="#">
                                <button className="btn btn-warning">Yes</button>
                            </form>
                        </div>
                        <div className="float-right">
                            <a href="/">
                                <button className="btn btn-danger">No</button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default CHANGE;