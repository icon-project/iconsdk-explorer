import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';

class Footer extends Component {

	componentWillMount() {
		this.props.chainInfoList();
	}

	onChainClick = chainName => {
		localStorage.setItem("chainName", chainName)
		window.location.reload();
	}

	render() {
		return (
			<div className="footer-wrap">
				<div className="screen0">
					<div className="wrap-holder">
						<p>©2022 ICON Foundation</p>
						<div className="selector">
							<div className="chain"><p>{localStorage.getItem("chainName")}<i className="img"></i></p>
								<ul>
									{this.props.chainInfos.data.map(chain => <li key={chain.channel}><span onClick={() => { this.onChainClick(chain.chainName) }}>{chain.chainName}</span></li>)}
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		);
	}
}

export default withRouter(Footer);
