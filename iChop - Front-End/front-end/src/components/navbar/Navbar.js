import React, {Component} from 'react';
import Roles from "../../constants/roles.constants";
import UserFooter from "../footer/roles/UserFooter";
import ModeratorFooter from "../footer/roles/ModeratorFooter";
import AdminFooter from "../footer/roles/AdminFooter";
import OwnerFooter from "../footer/roles/OwnerFooter";
import GuestFooter from "../footer/roles/GuestFooter";

class CHANGE extends Component {


    render() {
        let role = '';

        return (
            <div className="container" id="div-containter-top_nav_bar">

                <nav className="navbar navbar-expand-lg navbar-light bg-light rounded">

                    <img src="/res/img/navbar-icon.png" width="30" height="30" className="d-inline-block align-top"
                         alt="">
                        <a className="navbar-brand" href="/">iChop</a>
                        <button id="button-minimized-navbar" className="navbar-toggler" type="button"
                                data-toggle="collapse"
                                data-target="#navbarSupportedContent"
                                aria-controls="navbarSupportedContent" aria-expanded="false"
                                aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>

                        {
                            (() => {
                                switch (role) {
                                    case Roles.USER:
                                        return (<UserFooter/>);
                                    case Roles.MODERATOR:
                                        return (<ModeratorFooter/>);
                                    case Roles.ADMIN:
                                        return (<AdminFooter/>);
                                    case Roles.OWNER:
                                        return (<OwnerFooter/>);
                                    default:
                                        return (<GuestFooter/>);
                                }
                            })()
                        }

                        <th:block sec:authorize="isAnonymous()">
                            <th:block th:insert="navbar/top/top-navbar-guest"></th:block>
                        </th:block>

                        <th:block sec:authorize="isAuthenticated()">
                            <th:block th:switch="${#session.getAttribute('user-role')}">

                                <th:block th:case="MODERATOR">
                                    <th:block th:insert="navbar/top/top-navbar-moderator"></th:block>
                                </th:block>

                                <th:block th:case="ADMIN">
                                    <th:block th:insert="navbar/top/top-navbar-admin"></th:block>
                                </th:block>

                                <th:block th:case="OWNER">
                                    <th:block th:insert="navbar/top/top-navbar-owner"></th:block>
                                </th:block>

                                <th:block th:case="*">
                                    <th:block th:insert="navbar/top/top-navbar-user"></th:block>
                                </th:block>

                            </th:block>
                        </th:block>

                </nav>
            </div>
    );
    }

    }

    export default CHANGE;