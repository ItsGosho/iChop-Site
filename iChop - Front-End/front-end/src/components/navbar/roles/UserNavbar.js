import React,{Component} from 'react';

class UserNavbar extends Component {


    render() {

        return (
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">

                </ul>
                <ul className="navbar-nav ml-auto">
                    <li className="nav-item dropdown active">
                        <div id="userDiv">
                            <button type="button" className="btn btn-success dropdown-toggle btn-sm"
                                    data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">
                                <img
                                    th:src="@{http://localhost:8001/data/user/{username}/avatar(username=${#authentication.getName()})}"
                                    onError="this.onerror = null;this.src = '/res/img/avatar-user.png'"
                                    className="img-user_avatar-top_nav_bar"/>
                                    <span>⚙</span>
                            </button>
                            <div className="dropdown-menu dropdown-menu-right">
                                <a className="dropdown-item" th:href="@{'/user/'+${#authentication.name}+'/profile'}">
                                    <small>👤</small>
                                    <span>Profile</span></a>
                                <a className="dropdown-item" href="/user/my-profile/options/information">
                                    <small>⚙</small>
                                    <span>Options</span></a>
                                <div className="dropdown-divider"></div>
                                <a className="dropdown-item" href="/logout">
                                    <small>🚪</small>
                                    Logout</a>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        );
    }

}

export default UserNavbar;