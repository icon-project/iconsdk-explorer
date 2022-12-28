import React from 'react';
import LinkCell from './LinkCell'

const TokenLink = ({ pageType, to, label }) => {
  return (
    <LinkCell
      pageType={pageType}
      aClassName="on"
      to={to}
      label={label}
    />
  )
}

export default TokenLink;