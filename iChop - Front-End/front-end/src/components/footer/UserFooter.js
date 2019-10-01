import React,{Component} from 'react';

class UserFooter extends Component {


    render() {

        return (
            <div className="col-md-2 mx-auto">

                <h5 className="font-weight-bold text-uppercase mt-3 mb-4">---</h5>

                <ul className="list-unstyled">
                    <li>
                        <a th:href="@{'/user/'+${#authentication.name}+'/profile'}">
                            <small>👤</small>
                            <span>Profile</span></a>
                    </li>
                    <li>
                        <a href="/user/my-profile/options/information">
                            <small>⚙</small>
                            <span>Options</span></a>
                    </li>
                    <li>
                        <a href="/logout">
                            <small>🚪</small>
                            Logout</a>
                    </li>
                </ul>

            </div>
        );
    }

}

export default UserFooter;