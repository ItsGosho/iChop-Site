import React, {Component} from 'react';

class CHANGE extends Component {


    render() {

        return (
            <div>
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <a className="navbar-brand" th:href="@{/user/{username}/control(username=*{username})}">
                        <span>
                        <img th:src="@{http://localhost:8001/data/user/{username}/avatar(username=*{username})}"
                             style="width: 20px;height: 20px"/>
                        </span><span th:text="*{username}">username</span></a>
                    <button className="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"/>
                    </button>

                    <div className="collapse navbar-collapse" id="navbarSupportedContent">

                    </div>
                </nav>

                <div className="container" style="margin-left: 0;margin-top: 10px">
                    <div className="row">
                        <div className="col-sm">
                            <div className="card" style="width: 15rem;">
                                <div className="card-header">
                                    Options Menu
                                </div>
                                <ul className="list-group list-group-flush">
                                    <a th:href="@{/user/{username}/control/role-management(username=*{username})}">
                                        <li className="list-group-item control-option">Role Management</li>
                                    </a>
                                    <li className="list-group-item control-option">Option2</li>
                                    <li className="list-group-item control-option">Option3</li>
                                </ul>
                            </div>
                        </div>
                        <div className="col-sm">
                            <div className="card">
                                <div className="card-body">

                                    <th:block th:insert="${controlPage}"/>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <script>
                    runMenuSelectorColorizer();
                </script>
            </div>
        );
    }

}

export default CHANGE;