import React from 'react';
import { Link } from 'react-router-dom';

const BTPLinkCell = ({ pageType, to, networkId, label, aClassName, onClick }) => {
    return (
        <Link
            className={aClassName}
            to={`/${pageType}/${to}/${networkId}`}
            onClick={() => { if (typeof onClick === 'function') { onClick() } }}            
            title={to}
        >
            {label || to}
        </Link>
    )
}

export default BTPLinkCell;
