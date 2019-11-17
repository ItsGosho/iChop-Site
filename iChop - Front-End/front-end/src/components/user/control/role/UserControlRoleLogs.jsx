import React, {Component, Fragment} from 'react';
import dateFormat from "dateformat";

class UserControlRoleLogs extends Component {

    constructor(props) {
        super(props);

        this.state = {
            logs: []
        };

        this.iterateLogs = this.iterateLogs.bind(this);
    }

    componentDidMount() {
        let format = 'dd mmmm,yyyy HH:mm';

        let logs = [
            {happenedOn: dateFormat(Date.now(), format), message: ' Changed the role to X'},
            {happenedOn: dateFormat(Date.now(), format), message: ' Changed the role to X'},
        ];

        this.setState({logs})
    }

    iterateLogs() {
        return this.state.logs.map((item, index) => {
            return (
                <li key={index}>
                    <small>
                        <b>[{item.happenedOn}]</b>
                        <span>{item.message}</span>.
                    </small>
                </li>
            );
        });
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