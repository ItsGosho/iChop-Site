import React, {Fragment} from 'react';
import './SideInformationLocation.css'
import withState from "../../../../hocs/with.state";


const SideInformationLocation = (props) => {
    let {location} = props.userProfileInfo;

    return (
        <Fragment>
            {location !== null ? (
                <div className="card user-location-card">
                    <div className="card-body user-location-body">
                        <div className="col-md-auto">
                            <div className="row font-size-11px">
                                <div className="col-md-auto col-width-75">
                                    Location:
                                </div>
                                <div className="col-md-auto col-width-100">
                                    <span className="location-information-holder">{location}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            ) : null}
        </Fragment>
    );
};

export default withState(SideInformationLocation);