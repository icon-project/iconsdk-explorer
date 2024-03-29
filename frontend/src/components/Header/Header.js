import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { SearchBox } from 'components'
import { Connect } from 'components'
import { withRouter } from 'react-router-dom'
import configData from "../../config/config.json";
import { getChainInfo } from "../../utils/utils";

class Header extends Component {
    constructor(props) {
        super(props);
        this.state = {
            chainName: ""
        };
    }

    componentDidMount() {
        this.props.chainInfoList();
    }

    render() {
        const { loading, data, } = this.props.chainInfos;
        if(!loading) {
        	const chainName = localStorage.getItem('chainName');
        	if(chainName === null || chainName === 'undefined' || chainName === '') {
        		localStorage.setItem("chainName", data[0].chainName);
        	}
        }

        return (
            <div className="header-wrap">
                <div className="wrap-holder">
                    <div className="content">
                        <Link to="/">
                            <div className="logo">
                                <p><font style={{ fontSize: "150%", fontWeight: "900", color: "white"}}>{configData.ICONSDK_EXPLORER_NAME}</font></p>
                                <p><font style={{ fontSize: "150%", fontWeight: "900", color: "white"}}>[{localStorage.getItem("chainName")}]</font></p>
                            </div>
                        </Link>
                        <div className="link">
                            <ul>
                                <li>
                                    <span>
                                        Address
                                        <em className="img" />
                                    </span>
                                    <ol className="sub-menu">
                                        <li
                                            onClick={() => {
                                                this.props.history.push(`/addresses#${getChainInfo()}`)
                                            }}
                                        >
                                            <span>Addresses</span>
                                        </li>
                                        <li
                                            onClick={() => {
                                                this.props.history.push(`/contracts#${getChainInfo()}`)
                                            }}
                                        >
                                            <span>Contracts</span>
                                        </li>
                                    </ol>
                                </li>
                                <li
                                    onClick={() => {
                                        this.props.history.push(`/blocks#${getChainInfo()}`)
                                    }}
                                >
                                    <span>Block</span>
                                </li>
                                <li>
                                    <span>
                                        BTP
                                        <em className="img" />
                                    </span>
                                    <ol className="sub-menu">
                                        <li
                                            onClick={() => {
                                                this.props.history.push(`/networks#${getChainInfo()}`)
                                            }}
                                        >
                                            <span>Networks</span>
                                        </li>
                                        <li
                                            onClick={() => {
                                                this.props.history.push(`/btps#${getChainInfo()}`)
                                            }}
                                        >
                                            <span>BTPs</span>
                                        </li>
                                    </ol>
                                </li>
                                <li
                                    onClick={() => {
                                        this.props.history.push(`/transactions#${getChainInfo()}`)
                                    }}
                                >
                                    <span>Transaction</span>
                                </li>
                                <li>
                                    <span>
                                        Token
                                        <em className="img" />
                                    </span>
                                    <ol className="sub-menu">
                                        <li
                                            onClick={() => {
                                                this.props.history.push(`/tokens#${getChainInfo()}`)
                                            }}
                                        >
                                            <span>Tokens</span>
                                        </li>
                                        <li
                                            onClick={() => {
                                                this.props.history.push(`/nfts#${getChainInfo()}`)
                                            }}
                                        >
                                            <span>NFTs</span>
                                        </li>
                                        <li
                                            onClick={() => {
                                                this.props.history.push(`/tokentransfers#${getChainInfo()}`)
                                            }}
                                        >
                                            <span>Token Transfers</span>
                                        </li>
                                    </ol>
                                </li>
                            </ul>
                            <div className="link-right">
                                <SearchBox {...this.props} />
                                <Connect {...this.props} />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default withRouter(Header)
