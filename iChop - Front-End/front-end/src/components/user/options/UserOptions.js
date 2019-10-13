import React, {Component} from 'react';
import UserOptionsSidebar from "./other/UserOptionsSidebar";
import UserOptionsMinecraft from "./minecraft/UserOptionsMinecraft";
import UserOptionsChangePassword from "./password/UserOptionsChangePassword";
import UserOptionsInformation from "./information/UserOptionsInformation";

class UserOptions extends Component {


    render() {

        return (
            <div className="container" style={{'marginLeft': '0'}}>
                <div className="row">

                    <UserOptionsSidebar/>

                    <div className="col-sm">
                        <div className="card">
                            <div className="card-body">
                                {
                                    (() => {
                                        if (isInformationSelected) {
                                            return (<UserOptionsInformation/>)
                                        } else if (isChangePasswordSelected) {
                                            return (<UserOptionsChangePassword/>);
                                        } else if (isMinecraftSelected) {
                                            return (<UserOptionsMinecraft/>);
                                        }
                                    })()
                                }
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default UserOptions;