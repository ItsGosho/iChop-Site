import React, {Component, Fragment} from 'react';
import './UserProfileCentralContent.css'
import UserProfileCentralHead from "./UserProfileCentralHead";
import PanePosts from "./panes/PanePosts";
import PaneInformation from "./panes/PaneInformation";
import PropTypes from "prop-types";

class UserProfileCentralContent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isPostsTabSelected: true,
            isInformationSelected: false,
        };

        this.allTabsSelectionToFalse = this.allTabsSelectionToFalse.bind(this);
        this.selectPostsTab = this.selectPostsTab.bind(this);
        this.selectInformationTab = this.selectInformationTab.bind(this);
        this.getTab = this.getTab.bind(this);
    }

    allTabsSelectionToFalse() {
        this.setState({
            isPostsTabSelected: false,
            isInformationSelected: false,
        })
    }

    selectPostsTab() {
        this.allTabsSelectionToFalse();
        this.setState({isPostsTabSelected: true})
    }

    selectInformationTab() {
        this.allTabsSelectionToFalse();
        this.setState({isInformationSelected: true})
    }

    getTab() {
        let {isPostsTabSelected, isInformationSelected,} = this.state;

        if (isPostsTabSelected) {
            return (<PanePosts/>)
        }

        if (isInformationSelected) {
            return (<PaneInformation/>)
        }
    }

    render() {

        return (
            <Fragment>
                <div className="central-content">

                    <UserProfileCentralHead/>

                    <div className="col-md-auto user-information-navigation">

                        <NavTabs>
                            <Tab text="Profile posts" onClick={this.selectPostsTab}/>
                            <Tab text="Soon"/>
                            <Tab text="In-Game Activity"/>
                            <Tab text="Information" onClick={this.selectInformationTab}/>
                        </NavTabs>


                        <NavContent>
                            {this.getTab()}
                        </NavContent>

                    </div>
                </div>
            </Fragment>
        );
    }

}

export default UserProfileCentralContent;

const NavContent = ({children}) => (
    <div className="tab-content">
        <div className="tab-pane container active">
            {children}
        </div>
    </div>
);

const NavTabs = ({children}) => (
    <div className="navigation">
        <ul className="nav nav-tabs">
            {children}
        </ul>
    </div>
);

const Tab = ({text, onClick}) => {

    return (
        <li className="nav-item">
            <a className="nav-link" data-toggle='tab' href='#' onClick={onClick}>{text}</a>
        </li>
    )
};

Tab.propTypes = {
    text: PropTypes.string,
    onClick: PropTypes.func,
};