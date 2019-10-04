import React,{Component} from 'react';

class SideInformationMessages extends Component {

    render() {
        let totalMessages = 32;

        return (
            <div className="col-md-auto">
                <div className="row" style={{'fontSize': '11px'}}>
                    <div className="col-md-auto" style={{'columnWidth': '75px'}}>
                        Messages:
                    </div>
                    <div className="col-md-auto" style={{'columnWidth': '100px'}}>
                               <span style={{'display': 'inlineBlock','float': 'right'}}>
                                   {totalMessages}
                               </span>
                    </div>
                </div>
            </div>
        );
    }
}

export default SideInformationMessages;