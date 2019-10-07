import React, {Component} from 'react';

class ReportTableActionButtons extends Component {

    render() {
        let entityName = this.props.entityName;

        let onDeleteEntity = this.props.onDeleteEntity;
        let onDeleteReport = this.props.onDeleteReport;

        return (
            <div className="row">
                <div className="dropdown">

                    <button className="btn btn-warning btn-sm dropdown-toggle" type="button"
                            id="dropdownMenuButton"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ⚙Take Action!
                    </button>

                    <div className="dropdown-menu">

                        <a className="dropdown-item" href="#">
                            <button type="button" onClick={onDeleteEntity}
                                    style={{'all': 'initial'}}>❌Delete {entityName}</button>
                        </a>

                        <a className="dropdown-item" href="#">
                            <button onClick={onDeleteReport} type="button"
                                    style={{'all': 'initial'}}>😖Delete Report
                            </button>
                        </a>

                    </div>
                </div>
            </div>
        );
    }
}

export default ReportTableActionButtons;