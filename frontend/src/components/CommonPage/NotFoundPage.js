import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import img_sorry from '../../style/image/img-sorry.png'

class NotFoundPage extends Component {

  constructor(props) {
    super(props);
    this.state = {}
  }

  componentDidMount() {
  }

  componentWillUnmount() {
    if (typeof this.props.searchErrorReset === 'function') {
      this.props.searchErrorReset();
    }
  }

  render() {
    const { error } =  this.props
    return (
      <div className="content-wrap nodata">
        <div className="screen0">
          <div className="wrap-holder">
            <div className="contents">
              <span><img alt='Not Found' src={img_sorry}/></span>
              <p className="title">Sorry.</p>
              {error ?
                <p className="txt">
                  The string below is invalid.<br/>
                  Please double check your key.<br/>
                  Transactions being processed may not be seen.
                </p>
                :
                <p className="txt">
                  The requested order can not be processed.<br/>
                  Please check again as this may be <br/>
                  abnormal access or unpredicted error.
                </p>
              }
              {error && <p className="address">{error}</p>}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default withRouter(NotFoundPage);
