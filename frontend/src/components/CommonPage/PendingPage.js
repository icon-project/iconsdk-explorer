import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import img_pending from '../../style/image/img-pending.png'

class PendingPage extends Component {
  render() {
    const { error } =  this.props
    return (
    <div className="content-wrap nodata">
        <div className="screen0">
          <div className="wrap-holder">
            <div className="contents">
              <span><img src={img_pending} alt='pending' /></span>
              <p className="title">Pending…</p>
              <p className="txt">The string below is invalid.<br />
                Please double check your key.<br />
                Transactions being processed may not be seen.
              </p>
              <p className="address">{error}</p>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default withRouter(PendingPage);
