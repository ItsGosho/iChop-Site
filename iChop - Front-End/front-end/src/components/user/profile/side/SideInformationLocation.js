import React,{Component} from 'react';

class SideInformationLocation extends Component {

    render() {

        return (
            <div className="col-md-auto">
                <div className="row" style="font-size: 11px">
                    <div className="col-md-auto" style="column-width: 75px">
                        Location:
                    </div>
                    <div className="col-md-auto" style="column-width: 100px">
                                    <span style="display: inline-block;float: right;">
                                         <span th:text="*{location}"></span>
                                    </span>

                    </div>
                </div>
            </div>
            </div>
        );
    }
}

export default SideInformationLocation;