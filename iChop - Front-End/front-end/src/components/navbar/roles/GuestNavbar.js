import React,{Component} from 'react';

class GuestNavbar extends Component {


    render() {

        return (
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">

                </ul>
                <ul className="navbar-nav ml-auto">
                    <li className="nav-item dropdown active">
                        <div id="div-signIn-signInForms">
                            <button id="button-signIn-navBar" type="button"
                                    className="btn btn-success dropdown-toggle btn-sm"
                                    data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">
                                Sign in
                            </button>
                            <div id="div-authForms-signInForm" className="dropdown-menu dropdown-menu-right">


                                {/*<th:block th:insert="auth/login-dropdown.html"></th:block>


                                <th:block th:insert="auth/register-dropdown.html"></th:block>


                                <th:block th:insert="auth/forgotten_password-dropdown.html"></th:block>


                                <script src="/res/js/auth/authFormsConfig.js"></script>
                                <script src="/res/js/auth/url/authUrlHandlers.js"></script>
                                <script>

                                    runAuthFormsConfig();
                                    runAuthUrlHandlers();
                                </script>*/}
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        );
    }

}

export default GuestNavbar;