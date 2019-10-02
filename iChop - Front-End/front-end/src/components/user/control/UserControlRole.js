import React,{Component} from 'react';

class UserControlRole extends Component {


    render() {

        return (
            <div>
                <div className="row">
                    <div className="col-md-auto">
                        <span style="font-family: Consolas">Current Role: <b th:text="*{role}"></b></span>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-auto">
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
                    </div>
                    <div className="row">
                        <div className="col-md-auto">
                            <small><b>Logs:</b></small>
                        </div>
                        <div className="col-md-auto">
                            <ul>
                                <th:block th:each="userLog : *{roleChangeHistory}" th:object="${userLog}">
                                    <li>
                                        <small><b th:text="*{#temporals.format(happenedOn, 'dd MMMM,yyyy HH:mm')}">[10
                                            March,2019 20:36] </b> <span th:text="*{message}"></span>.
                                        </small>
                                    </li>
                                </th:block>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default UserControlRole;