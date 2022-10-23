import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';

class Footer extends Component {

	render() {
		return (
			<div className="footer-wrap">
				<div className="screen0">
					<div className="wrap-holder">
						<p>©2022 ICON Foundation</p>
					</div>
				</div>
			</div>
		);
	}
}

export default withRouter(Footer);
