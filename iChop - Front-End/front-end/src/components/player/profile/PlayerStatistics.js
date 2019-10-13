import React, {Component, Fragment} from 'react';
import formatDate from "dateformat";
import './PlayerStatistics.css'

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

                                    {isOnline ?
                                        (<span>🔋 <span className="span-online">Online</span></span>) :
                                        (<span>🔴 <span className="span-offline">Offline</span></span>)}

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="col-md-auto">

                    <div className="dropdown-divider"/>

                    <div className="col-md-auto head div-statistics-holder">

                        <span>Basic Statistcs:</span>

                        <StatisticsRow icon={'💀'} title={'Deaths'} value={totalDeaths}/>
                        <StatisticsRow icon={'🗡️'} title={'Damage Taken'} value={totalDamageDealt}/>
                        <StatisticsRow icon={'🤕'} title={'Last Join'} value={totalDamageTaken}/>
                        <StatisticsRow icon={'️🧟'} title={'Last Join'} value={totalMobKills}/>
                        <StatisticsRow icon={'👤'} title={'Last Join'} value={totalPlayerKills}/>
                        <StatisticsRow icon={'⏰'} title={'Last Join'} value={lastJoin}/>

                    </div>

                </div>

            </Fragment>
        );
    }
}

const StatisticsRow = (props) => {
    let {icon, title, value} = props;

    return (
        <div className="row">
            <span>{icon}</span>{title}: <span>{value}</span>
        </div>
    );
};

export default PlayerStatistics;