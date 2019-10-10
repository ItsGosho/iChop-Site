import React,{Component} from 'react';
import RoutingURLs from "../../../constants/routing.constants";

class FooterCopyright extends Component {

    render() {

        return (
            <div className="footer-copyright text-center py-3">© 2019 Copyright:
                <a href={RoutingURLs.HOME}> iChop.bg</a>
            </div>
        );
    }

}

export default FooterCopyright;