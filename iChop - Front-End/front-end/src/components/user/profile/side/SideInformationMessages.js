import React,{Component} from 'react';

class SideInformationMessages extends Component {

    render() {

        return (
            <div className="col-md-auto">
                <div className="row" style="font-size: 11px">
                    <div className="col-md-auto" style="column-width: 75px">
                        Messages:
                    </div>
                    <div className="col-md-auto" style="column-width: 100px">
                               <span style="display: inline-block;float: right;" th:text="*{totalMessages}">
                                    00000
                               </span>
                    </div>
                </div>
            </div>
        );
    }
}

export default SideInformationMessages;