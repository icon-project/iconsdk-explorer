import React from 'react';
import { Link } from 'react-router-dom';
import { getChainInfo } from "utils/utils";

const BTPLinkCell = ({ pageType, to, networkId, label, aClassName, onClick }) => {
    return (
        <Link
            className={aClassName}
            to={`/${pageType}/${to}/${networkId}#${getChainInfo()}`}
            onClick={() => { if (typeof onClick === 'function') { onClick() } }}            
            title={to}
        >
            {label || to}
        </Link>
    )
}

export default BTPLinkCell;
