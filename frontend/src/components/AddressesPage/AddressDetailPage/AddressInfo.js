import React, { Component } from 'react'
import { withRouter } from 'react-router-dom'
import { convertNumberToText } from 'utils/utils'
import { CopyButton, LoadingComponent, QrCodeButton } from 'components'
import NotificationManager from 'utils/NotificationManager'
import { isUrl } from '../../../utils/utils';
import configData from '../../../config/config.json'

const _isNotificationAvailable = NotificationManager.available()

class AddressInfo extends Component {
    constructor(props) {
        super(props)
        this.state = {
            notification: _isNotificationAvailable && this.props.walletNotification,
            icxMore: false,
            tokenMore: false,
            showNode: "none",
        }
    }

    onNotificationChange = () => {
        if (!_isNotificationAvailable) {
            return
        }
        const notification = !this.state.notification
        this.setState({ notification }, () => {
            this.props.setNotification(notification)
            if (notification) {
                const { wallet } = this.props
                const { data, error } = wallet
                const address = data.address || error
                NotificationManager.registerServiceWorker(address)
            } else {
                NotificationManager.deregisterServiceWorker()
            }
        })
    }

    toggleIcxMore = () => {
        this.setState({ icxMore: !this.state.icxMore })
    }

    toggleTokenMore = () => {
        this.setState({ tokenMore: !this.state.tokenMore })
    }

    goBlock = height => {
        window.open('/block/' + height, '_blank')
    }

    onSocialClick = async link => {
        if (isUrl(link)) {
            window.open(link, '_blank')
        }
    }

    clickShowBtn = () => {
        this.setState({ showNode: "table-row" })
    }

    render() {
        const { icxMore, tokenMore, showNode } = this.state

        const { wallet, walletAddress } = this.props
        const { loading, data, error } = wallet
        const {
            available,
        } = data

        const balance = Number(available || 0);

        const Content = () => {
            if (loading) {
                return <LoadingComponent height="206px" />
            }
            else {
                const { address, tokenList } = data
                const _address = !!address ? address : error
                const isConnected = walletAddress === _address

                return (
                    <div className="screen0">
                        <div className="wrap-holder">
                            {isConnected ? (
                                <p className="title">
                                    My Address
                                    <span className="connected">
                                        <i className="img" />Connected to Iconsdk
                                    </span>
                                </p>
                            ) : (
                                    <p className="title">Address</p>
                                )}
                            <div className="contents">
                                <div className="table-box">
                                    <table className="table-typeB address">
                                        <thead>
                                            <tr>
                                                <th>Address</th>
                                                <th>value</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr className="">
                                                <td>Address</td>
                                                <td colSpan='1'>
                                                    {_address} <QrCodeButton address={_address} />
                                                    <CopyButton data={_address} title={'Copy Address'} isSpan />
                                                    <span className="show-node-addr" style={{display: "none"}} onClick={this.clickShowBtn}>Show node address</span>
                                                </td>
                                            </tr>
                                            <tr className="node-addr" style={{display:showNode}}>
                                                <td>Node Address</td>
                                                <td colSpan="3">
                                                    <i className="img node-addr"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Balance</td>
                                                <td colSpan="3" className="balance">
                                                    <div>
                                                        <p><span><i className="coin icon"></i>{configData.ICONSDK_EXPLORER_SYM}</span><span>{`${convertNumberToText(balance, icxMore ? undefined : 4)}`}<em>{configData.ICONSDK_EXPLORER_SYM}</em></span></p>
                                                    </div>
                                                    <div className={tokenMore ? 'on' : ''}>
                                                        <p><span><i className="coin"></i>Token</span><span>{(tokenList || []).length}<em>Tokens</em></span><em className="drop-btn" onClick={this.toggleTokenMore}><i className="img"></i></em></p>
                                                        {(tokenList || []).sort((a, b) => (a.contractName < b.contractName ? -1 : a.contractName > b.contractName ? 1 : 0)).map((token, index) => {
                                                            const { contractName, contractSymbol, quantity } = token
                                                            return <p key={index}><span>{contractName}</span><span>{`${convertNumberToText(quantity)}`}<em>{contractSymbol}</em></span></p>
                                                        })}
                                                    </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                )
            }
        }
        return Content()
    }
}

export default withRouter(AddressInfo)
