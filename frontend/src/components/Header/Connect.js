import React, { Component } from 'react'
import { requestAddress } from '../../utils/connect'
import { CopyButton } from 'components'
import checkIconsdk from '../../lib/check-iconsdk'
import NotificationManager from 'utils/NotificationManager'
import { getChainInfo } from "../../utils/utils";

class Connect extends Component {
    constructor(props) {
        super(props)
        this.state = {
            disabled: false,
            walletAddress: this.props.walletAddress,
        }
    }

    async componentDidMount() {
        const { isChrome, iconsdkInstalled, hasIconsdkWallet } = await checkIconsdk(1000, 2000)
        this.setState({
            disabled: !(isChrome && iconsdkInstalled && hasIconsdkWallet),
        })
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.walletAddress !== this.props.walletAddress) {
            this.setState(
                {
                    walletAddress: nextProps.walletAddress,
                },
                () => {
                    window.dispatchEvent(
                        new CustomEvent('CUSTOM_FX', {
                            detail: { type: 'SET_WALLET' },
                        }),
                    )
                },
            )
        }
    }

    getWalletAddress = async () => {
        // TODO iconsdk-wallet chrome extension
        // if (this.state.disabled) {
        //     window.open('https://chrome.google.com/webstore/detail/iconex/flpiciilemghbmfalicajoolhkkenfel', '_blank')
        //     return
        // }

        if (this.state.walletAddress) {
            return
        }
        
        const walletAddress = await requestAddress()
        this.setState({ walletAddress }, () => {
            window.dispatchEvent(
                new CustomEvent('CUSTOM_FX', {
                    detail: { type: 'SET_WALLET' },
                }),
            )
            this.props.setAddress(walletAddress)
            this.props.history.push(`/address/${walletAddress}#${getChainInfo()}`)
        })
    }

    disconnect = () => {
        this.setState({ walletAddress: undefined }, () => {
            this.props.clearWallet()
            NotificationManager.deregisterServiceWorker()
        })
    }

    render() {
        const { walletAddress } = this.state
        return (
            <div className={`connect ${walletAddress ? 'join' : ''}`}>
                <span onClick={this.getWalletAddress}>
                    <em className="img" />
                </span>
                {walletAddress ? (
                    <div className="sub-menu">
                        <p>
                            <span>Wallet Address</span>
                            <CopyButton data={walletAddress} title={'Copy Address'} wallet={true} />
                        </p>
                        <span className="btn" onClick={this.disconnect}>
                            Disconnect
                        </span>
                        <span
                            className="btn"
                            onClick={() => {
                                this.props.history.push(`/address/${walletAddress}#${getChainInfo()}`)
                            }}
                        >
                            View Details
                        </span>
                    </div>
                ) : (
                    ''
                )}
            </div>
        )
    }
}

export default Connect
