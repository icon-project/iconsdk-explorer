import React from 'react';
import LinkCell from './LinkCell'

const NetworkLink = ({ to, label, ellipsis }) => {
  return (
    <LinkCell
      pageType="network"
      aClassName={`on ${ellipsis ? 'ellipsis' : ''}`}
      to={to}
      label={label}
    />
  )
}

export default NetworkLink;