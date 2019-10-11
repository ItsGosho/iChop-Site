import React, {Component, Fragment} from 'react';
import formatDate from "dateformat";

class PlayerStatistics extends Component {


    render() {

        let playerName = 'ItsGosho';

        let isOnline = false;

        let totalDeaths = 1;
        let totalDamageDealt = 53422;
        let totalDamageTaken = 12332;
        let totalMobKills = 655;
        let totalPlayerKills = 11;
        let lastJoin = formatDate(new Date(), 'dd/MM/yyyy HH:mm');

        return (
            <Fragment>
                <div className="col-md-auto">
                    <div className="player-name-div">
                        <div className="row">
                            <div>
                                <div className="col-md-auto head">
                                    <span className="player-name">{playerName}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="dropdown-divider"/>
                </div>

                <div className="col-md-auto">
                    <div className="player-connection-status">
                        <div className="row">
                            <div>
                                <div className="col-md-auto head player-connection-status">

                                    {
                                        (() => {
                                            if (isOnline) {
                                                return (
                                                    <span>🔋 <span
                                                        style={{'color': 'green'}}>Online</span></span>
                                                );
                                            } else {
                                                return (
                                                    <span>🔴 <span
                                                        style={{'color': 'darkred'}}>Offline</span></span>
                                                );
                                            }
                                        })()
                                    }

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="col-md-auto">
                    <div className="dropdown-divider"/>
                    <div className="col-md-auto head" style={{'fontSize': '19px', 'fontFamily': 'Consolas'}}>
                        <span>Basic Statistcs:</span>
                        <div className="row">
                            <span>💀</span>Deaths: <span>{totalDeaths}</span>
                        </div>
                        <div className="row">
                            <span>🗡️ </span>Damage Taken: <span>{totalDamageDealt}</span>
                        </div>
                        <div className="row">
                            <span>🤕</span>Damage Taken: <span>{totalDamageTaken}</span>
                        </div>
                        <div className="row">
                            <span>️🧟</span>Mobs Killed: <span>{totalMobKills}</span>
                        </div>
                        <div className="row">
                            <span>👤</span>Players Killed: <span>{totalPlayerKills}</span>
                        </div>
                        <div className="row">
                            <span>⏰</span>Last Join: <span>{lastJoin}</span>
                        </div>
                    </div>
                </div>

            </Fragment>
        );
    }

}

export default PlayerStatistics;