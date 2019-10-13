import React, {Component, Fragment} from 'react';
import dateFormat from 'dateformat';

class UserControlInformation extends Component {

    componentDidMount() {
        let {username} = this.props;

        /*TODO: fetch data and add her to the state*/
    }

    render() {
        let {username} = this.props;

        let id = '123';
        let rank = 'rank';
        let email = 'email';
        let registrationDate = dateFormat(Date.now(), "dd/mm/yyyy");
        let lastOnline = dateFormat(Date.now(), "dd/mm/yyyy");
        let lastAccessedLocation = 'Bulgaria';
        let enabled = true;
        let isAccountExpired = false;
        let isAccountLocked = false;
        let isCredentialsExpired = false;

        return (
            <Fragment>
                <div className="row">
                    <div className="col-md-auto" style={{'fontFamily': 'Consolas'}}>
                        <span>Core Information:</span>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-auto">
                        <ul>
                            <li>ID: <span>{id}</span></li>
                            <li>Username: <span>{username}</span></li>
                            <li>Rank: <span>{rank}</span></li>
                            <li>Email: <span>{email}</span></li>
                            <li>Registration Date: <span>{registrationDate}</span>
                            </li>
                            <li>Last Online: <span>{lastOnline}</span>
                            </li>
                            <li>Last Accessed Location: <span>{lastAccessedLocation}</span></li>
                            <li>Enabled: <span>{enabled}</span></li>
                            <li>Account Not Expired: <span>{isAccountExpired}</span></li>
                            <li>Account Not Locked: <span>{isAccountLocked}</span></li>
                            <li>Credentials Not Expired: <span>{isCredentialsExpired}</span></li>
                        </ul>
                    </div>
                </div>
            </Fragment>
        );
    }

}

export default UserControlInformation;