import React, {Component, Fragment} from 'react';

class UserControlInformation extends Component {


    render() {
        let id = 'ID';
        let username = 'username';
        let rank = 'rank';
        let email = 'email';
        let registrationDate = Date.now();
        let lastOnline = Date.now();
        let lastAccessedLocation = 'Bulgaria';
        let enabled = true;
        let isAccountExpired = false;
        let isAccountLocked = false;
        let isCredentialsExpired = false;

        return (
            <Fragment>
                <div className="row">
                    <div className="col-md-auto" style={{'font-family': 'Consolas'}}>
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