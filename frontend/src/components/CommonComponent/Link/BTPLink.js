import React from 'react';
import BTPLinkCell from './BTPLinkCell'

const BTPLink = ({ to, networkId, label, ellipsis }) => {
  return (
    <BTPLinkCell
      pageType="btp"
      aClassName={`on ${ellipsis ? 'ellipsis' : ''}`}
      to={to}
      networkId={networkId}
      label={label}
    />
  )
}

export default BTPLink;