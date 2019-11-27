import React, {Component, Fragment} from 'react';
import dateFormat from 'dateformat';
import UserServices from "../../../../services/user.services";

const DATE_PATTERN = 'dd/mm/yyyy';

class UserControlInformation extends Component {

    constructor(props) {
        super(props);

        this.state = {
            id: '',
            username: '',
            authorities: [],
            email: '',
            registrationDate: new Date(),
            lastOnline: new Date(),
            location: '',
        };

        this.iterateAuthorities = this.iterateAuthorities.bind(this);
    }


    async componentDidMount() {
        let {username} = this.props;

        let user = await UserServices.adminFindByUsername(username);
        this.setState({...user})
    }

    iterateAuthorities() {
        let {authorities} = this.state;
        return authorities.map(({authority}, index) => {
            return (<li>{authority}</li>)
        });
    }

    render() {
        let {id, username, email, registrationDate, lastOnline, location} = this.state;

        return (
            <Fragment>
                <div className="row">
                    <div className="col-md-auto">
                        <span>Core Information:</span>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-auto">
                        <ul>
                            <li>ID: <span>{id}</span></li>
                            <li>Username: <span>{username}</span></li>
                            <li>Authorities: <span>{123}</span></li>
                            <ul>
                                <li>{this.iterateAuthorities()}</li>
                            </ul>
                            <li>Email: <span>{email}</span></li>
                            <li>Registration Date: <span>{dateFormat(registrationDate, DATE_PATTERN)}</span>
                            </li>
                            <li>Last Online: <span>{dateFormat(lastOnline, DATE_PATTERN)}</span>
                            </li>
                            <li>Last Accessed Location: <span>{location}</span></li>
                        </ul>
                    </div>
                </div>
            </Fragment>
        );
    }

}

export default UserControlInformation;