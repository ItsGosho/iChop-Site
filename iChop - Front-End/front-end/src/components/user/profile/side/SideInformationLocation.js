import React,{Component} from 'react';

class SideInformationLocation extends Component {

    render() {
        let location = 'Bulgaria';

        return (
            <div className="col-md-auto">
                <div className="row" style={{'fontSize': '11px'}}>
                    <div className="col-md-auto" style={{'columnWidth': '75px'}}>
                        Location:
                    </div>
                    <div className="col-md-auto" style={{'columnWidth': '100px'}}>
                                    <span style={{'display': 'inlineBlock','float': 'right'}}>
                                        {location}
                                    </span>

                    </div>
                </div>
            </div>
        );
    }
}

export default SideInformationLocation;