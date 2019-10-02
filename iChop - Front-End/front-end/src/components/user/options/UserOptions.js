import React, {Component} from 'react';
import UserOptionsSidebar from "./other/UserOptionsSidebar";
import optionsSidebarReduxHoc from "../../../redux/hocs/options.sidebar.hoc";
import UserOptionsMinecraft from "./minecraft/UserOptionsMinecraft";

class UserOptions extends Component {


    render() {

        let {isInformationSelected, isChangePasswordSelected, isMinecraftSelected} = this.props.redux;

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
                                            return (<h1>Information</h1>)
                                        } else if (isChangePasswordSelected) {
                                            return (<h1>Change password</h1>);
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

export default optionsSidebarReduxHoc(UserOptions);