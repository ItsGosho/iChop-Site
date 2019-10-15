import React, {Component, Fragment} from 'react';
import dateFormat from "dateformat";
import './SideInformationUser.css'

class SideInformationUser extends Component {


    render() {
        let datePattern = "dd mmm,yyyy";

        let lastOnline = dateFormat(new Date(), datePattern);
        let registrationDate = dateFormat(new Date(), datePattern);
        let totalMessages = 32;

        return (
            <Fragment>

                <Info title="Last Online" content={lastOnline}/>
                <Info title="Joined On" content={registrationDate}/>
                <Info title="Messages" content={totalMessages}/>

            </Fragment>
        );
    }
}

export default SideInformationUser;

const Info = (props) => {
    let {title, content} = props;

    return (
        <div className="col-md-auto">
            <div className="row info-row">

                <div className="col-md">
                    {title}:
                </div>

                <div className="col-md">
                    <span className="info-content">{content}</span>
                </div>

            </div>
        </div>
    )
};