import React from 'react';
import RoutingURLs from "../../../constants/routing/routing.constants";

const FooterCopyright = () => (
    <div className="footer-copyright text-center py-3">© 2019 Copyright:
        <a href={RoutingURLs.HOME}> iChop.bg</a>
    </div>
);

export default FooterCopyright;