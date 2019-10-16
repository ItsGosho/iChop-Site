import React,{Component} from 'react';
import './SideInformationLocation.css'

class SideInformationLocation extends Component {

    render() {
        let location = 'Bulgaria';

        return (
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
        );
    }
}

export default SideInformationLocation;