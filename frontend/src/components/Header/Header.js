import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { SearchBox } from 'components'
import { Connect } from 'components'
import { withRouter } from 'react-router-dom'

class Header extends Component {
    render() {
        return (
            <div className="header-wrap">
                <div className="wrap-holder">
                    <div className="content">
                        <Link to="/">
                            <div className="logo">
                                <font style={{ fontSize: "150%", fontWeight: "900", color: "white"}}>ICONSDK Explorer</font>
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
                                                this.props.history.push('/addresses')
                                            }}
                                        >
                                            <span>Addresses List</span>
                                        </li>
                                        <li
                                            onClick={() => {
                                                this.props.history.push('/contracts')
                                            }}
                                        >
                                            <span>Contracts List</span>
                                        </li>
                                    </ol>
                                </li>
                                <li
                                    onClick={() => {
                                        this.props.history.push('/blocks')
                                    }}
                                >
                                    <span>Block</span>
                                </li>
                                <li
                                    onClick={() => {
                                        this.props.history.push('/transactions')
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
                                                this.props.history.push('/tokens')
                                            }}
                                        >
                                            <span>Tokens List</span>
                                        </li>
                                        <li
                                            onClick={() => {
                                                this.props.history.push('/tokentransfers')
                                            }}
                                        >
                                            <span>Token Transfers List</span>
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
