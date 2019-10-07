import React, {Component} from 'react';
import './PlayerProfile.css'
import formatDate from 'dateformat';

class PlayerProfile extends Component {


    render() {
        let siteUser = 'ItsGosho';
        let uuid = '8ed20904-3262-401a-901a-1946504d2eea';
        let playerName = 'ItsGosho';

        let isOnline = false;

        let totalDeaths = 1;
        let totalDamageDealt = 53422;
        let totalDamageTaken = 12332;
        let totalMobKills = 655;
        let totalPlayerKills = 11;
        let lastJoin = formatDate(new Date(),'dd/MM/yyyy HH:mm');

        return (
            <div className="container">

                <div className="row">

                    <div className="col-md-auto">
                        <div className="col-md-sm">

                            <div align="center">
                                <img th:src="@{https://crafatar.com/renders/body/{uuid}(uuid=*{uuid})}"
                                     className="img-player_body-player_profile"/>
                            </div>


                            <div className="card menu">
                                <div className="card-body menu">

                                    <div className="col-md-auto">
                                        <button className="btn btn-warning btn-statistics" type="button">Statistics
                                        </button>
                                    </div>

                                    <div className="col-md-auto">
                                        <div className="row">

                                        </div>
                                    </div>

                                </div>
                            </div>

                            <th:block th:if="*{siteUserUsername != null}">
                                <div className="card site-user-username">
                                    <div className="card-body site-user-username">

                                        <div className="col-md-auto">
                                            <div className="row site-user-title">
                                                <div className="col-md-auto site-user-title">
                                                    Site User:
                                                </div>
                                                <div className="col-md-auto site-user-username">
                               <span className="site-user-username">
                                   <a th:href="@{/user/{username}/profile(username=*{siteUserUsername})}"
                                      th:text="*{siteUserUsername}"></a>
                               </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </th:block>

                        </div>
                    </div>

                    <div className="central-content">

                        <div className="col-md-auto">
                            <div className="player-name-div">
                                <div className="row">
                                    <div>
                                        <div className="col-md-auto head">
                                            <span className="player-name" th:text="*{name}"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="dropdown-divider"></div>
                        </div>

                        <div className="col-md-auto status">
                            <div className="player-connection-status">
                                <div className="row">
                                    <div>
                                        <div className="col-md-auto head player-connection-status">

                                            <th:block th:if="*{getIsOnline()}">
                                                <span>🔋<span style="color: green">Online</span></span>
                                            </th:block>

                                            <th:block th:if="*{!getIsOnline()}">
                                                <span>🔴<span style="color: darkred">Offline</span></span>
                                            </th:block>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-auto">
                            <div className="dropdown-divider"></div>
                            <div className="col-md-auto head" style="font-size: 19px;font-family: Consolas">
                                <span>Basic Statistcs:</span>
                                <div className="row">
                                    <span>💀</span>Deaths: <span th:text="*{totalDeaths}"></span>
                                </div>
                                <div className="row">
                                    <span>🗡️ </span>Damage Taken: <span th:text="*{totalDamageDealt}"></span>
                                </div>
                                <div className="row">
                                    <span>🤕</span>Damage Taken: <span th:text="*{totalDamageTaken}"></span>
                                </div>
                                <div className="row">
                                    <span>️🧟</span>Mobs Killed: <span th:text="*{totalMobKills}"></span>
                                </div>
                                <div className="row">
                                    <span>👤</span>Player Killed: <span th:text="*{totalPlayerKills}"></span>
                                </div>
                                <div className="row">
                                    <span>⏰</span>Last Join: <span
                                    th:text="*{#temporals.format(lastJoin, 'dd/MM/yyyy HH:mm')}"></span>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
            </div>
        );
    }

}

export default PlayerProfile;