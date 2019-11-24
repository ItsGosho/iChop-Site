import React, {Component, Fragment} from 'react';
import dateFormat from "dateformat";

const ACTION_DATE_FORMAT = 'dd mmmm,yyyy HH:mm';

class UserControlRoleLogs extends Component {

    constructor(props) {
        super(props);

        this.state = {
            logs: []
        };

        this.iterateLogs = this.iterateLogs.bind(this);
    }

    componentDidMount() {
        let logs = [
            {happenedOn: dateFormat(Date.now(), ACTION_DATE_FORMAT), message: ' Changed the role to X'},
            {happenedOn: dateFormat(Date.now(), ACTION_DATE_FORMAT), message: ' Changed the role to X'},
        ];

        this.setState({logs})
    }

    iterateLogs() {
        return this.state.logs.map(({happenedOn, message}, index) => (
            <li key={index}>
                <small>
                    <b>[{happenedOn}]</b>
                    <span>{message}</span>.
                </small>
            </li>
        ));
    }

    render() {
        return (
            <Fragment>
                <div className="col-md-auto">
                    <small>
                        <b>Logs:</b>
                    </small>
                </div>
                <div className="col-md-auto">
                    <ul>
                        {this.iterateLogs()}
                    </ul>
                </div>
            </Fragment>
        );
    }

}

export default UserControlRoleLogs;