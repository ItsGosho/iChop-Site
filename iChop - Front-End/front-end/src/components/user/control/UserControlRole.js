import React, {Component} from 'react';
import Roles from "../../../constants/roles.constants";
import dateFormat from 'dateformat';

class UserControlRole extends Component {


    render() {
        let role = Roles.USER;
        let logs = [
            {happenedOn: dateFormat(Date.now(), "dd mmmm,yyyy HH:mm"), message: ' Changed the role to X'},
            {happenedOn: dateFormat(Date.now(), "dd mmmm,yyyy HH:mm"), message: ' Changed the role to X'},
        ];


        return (
            <div>
                <div className="row">
                    <div className="col-md-auto">
                        <span style={{'fontFamily': 'Consolas'}}>Current Role: <b>{role}</b></span>
                    </div>
                </div>
                <div className="row">
                    {/*<div className="col-md-auto">
                        <th:block th:if="*{role != 'OWNER'}">
                            <th:block th:if="*{previousRole != null}">

                                <form style="all:initial;font-family: Consolas" method="post"
                                      th:action="@{/user/{username}/control/role-management/demote(username=*{username})}">
                                    <button type="submit" className="btn btn-warning btn-sm"><span>👇🏻</span><span
                                        th:text="*{previousRole}"></span></button>
                                </form>

                            </th:block>
                            <th:block th:if="*{nextRole != null and nextRole != 'OWNER'}">

                                <form style="all:initial;font-family: Consolas" method="post"
                                      th:action="@{/user/{username}/control/role-management/promote(username=*{username})}">
                                    <button type="submit" className="btn btn-warning btn-sm"><span>👆🏻</span><span
                                        th:text="*{nextRole}"></span></button>
                                </form>

                            </th:block>
                        </th:block>
                    </div>*/}
                    <div className="row">
                        <div className="col-md-auto">
                            <small><b>Logs:</b></small>
                        </div>
                        <div className="col-md-auto">
                            <ul>

                                {
                                    logs.map((item, index) => {
                                        return (
                                            <li key={index}>
                                                <small>
                                                    <b>[{item.happenedOn}]</b>
                                                    <span>{item.message}</span>.
                                                </small>
                                            </li>
                                        );
                                    })
                                }
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default UserControlRole;