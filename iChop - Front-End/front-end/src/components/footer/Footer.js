import React, {Component} from 'react';

class CHANGE extends Component {


    render() {

        let role = '';

        return (
            <footer className="page-footer font-small stylish-color-dark pt-4" style="margin-top: 125px">

                <div className="container text-center text-md-left">
                    <div className="row">
                        <div className="col-md-4 mx-auto">
                            <h5 className="font-weight-bold text-uppercase mt-3 mb-4">iChop - The Minecraft Server</h5>
                            <p>
                                Welcome to our website ,here you can find the latest news
                                about something new ,to link your account with your in-game account and
                                so many other things!
                            </p>

                        </div>

                        <hr className="clearfix w-100 d-md-none">

                            {
                                (() => {
                                    switch (role) {
                                        case "":
                                    }
                                })()
                            }

                            <th:block sec:authorize="isAnonymous()">
                                <th:block th:insert="footers/footer/footer-guest"></th:block>
                            </th:block>

                            <th:block sec:authorize="isAuthenticated()">
                                <th:block th:switch="${#session.getAttribute('user-role')}">

                                    <th:block th:case="MODERATOR">
                                        <th:block th:insert="footers/footer/footer-moderator"></th:block>
                                    </th:block>

                                    <th:block th:case="ADMIN">
                                        <th:block th:insert="footers/footer/footer-admin"></th:block>
                                    </th:block>

                                    <th:block th:case="OWNER">
                                        <th:block th:insert="footers/footer/footer-owner"></th:block>
                                    </th:block>

                                    <th:block th:case="*">
                                        <th:block th:insert="footers/footer/footer-user"></th:block>
                                    </th:block>

                                </th:block>
                            </th:block>
                        </hr>
                    </div>

                </div>

                <div className="footer-copyright text-center py-3">© 2019 Copyright:
                    <a href="localhost:8000">iChop.bg</a>
                </div>
            </footer>
        );
    }

}

export default CHANGE;