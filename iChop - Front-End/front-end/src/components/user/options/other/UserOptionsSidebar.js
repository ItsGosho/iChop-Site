import React, {Component} from 'react';
import optionsSidebarReduxHoc from "../../../../redux/hocs/options.sidebar.hoc";

class UserOptionsSidebar extends Component {

    constructor(props) {
        super(props);

        this.onInformationClick = this.onInformationClick.bind(this);
        this.onChangePasswordClick = this.onChangePasswordClick.bind(this);
        this.onMinecraftClick = this.onMinecraftClick.bind(this);
    }


    onInformationClick(event) {
        event.preventDefault();
        this.props.selectInformation();
    }

    onChangePasswordClick(event) {
        event.preventDefault();
        this.props.selectChangePassword();
    }

    onMinecraftClick(event) {
        event.preventDefault();
        this.props.selectMinecraft();
    }


    render() {
        return (
            <div className="col-sm">
                <div className="card" style={{'width': '15rem'}}>
                    <div className="card-header">
                        Options Menu
                    </div>
                    <ul className="list-group list-group-flush">
                        <a href=' ' onClick={this.onInformationClick}>
                            <li className="list-group-item control-option">Information</li>
                        </a>
                        <a href=' ' onClick={this.onChangePasswordClick}>
                            <li className="list-group-item control-option">Change Password</li>
                        </a>
                        <a href=' ' onClick={this.onMinecraftClick}>
                            <li className="list-group-item control-option">Minecraft</li>
                        </a>
                    </ul>
                </div>
            </div>
        );
    }

}

export default optionsSidebarReduxHoc(UserOptionsSidebar);