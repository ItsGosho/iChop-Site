import React,{Component} from 'react';

class UserOptions extends Component {


    render() {

        return (
            <div className="container" style={{'marginLeft': '0'}}>
                <div className="row">
                    <div className="col-sm">
                        <div className="card" style={{'width': '15rem'}}>
                            <div className="card-header">
                                Options Menu
                            </div>
                            <ul className="list-group list-group-flush">
                                <a href="/user/my-profile/options/information">
                                    <li className="list-group-item control-option">Information</li>
                                </a>
                                <a href="/user/my-profile/options/change-password">
                                    <li className="list-group-item control-option">Change Password</li>
                                </a>
                                <a href="/user/my-profile/options/minecraft">
                                    <li className="list-group-item control-option">Minecraft</li>
                                </a>
                            </ul>
                        </div>
                    </div>
                    <div className="col-sm">
                        <div className="card">
                            <div className="card-body">
                                {/*<th:block th:insert="${optionsPage}"/>*/}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default UserOptions;