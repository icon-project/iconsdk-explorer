import React, { Component } from 'react'
import { withRouter } from 'react-router-dom'
import { numberWithCommas } from 'utils/utils'
import { LoadingComponent, AddressLink } from 'components'

class NFTSummary extends Component {
    render() {
        const { nft } = this.props
        const { loading, data } = nft

        const Content = () => {
            if (loading) {
                return <LoadingComponent height="206px" />
            } else {
                const { tokenName, totalSupply, contract, holders, transfers, symbol } = data
                return (
                    <div className="screen0">
                        <div className="wrap-holder">
                            <p className="title dapp">
                                {tokenName} ({symbol})
                            </p>
                            <div className="contents">
                                <div className="table-box">
                                    <table className="table-typeB contract">
                                        <tbody>
                                            <tr>
                                                <td>Total Supply</td>
                                                <td>
                                                    {numberWithCommas(totalSupply)} {symbol}
                                                </td>
                                                <td>Contract </td>
                                                <td>
                                                    <span>{contract ? <AddressLink to={contract} /> : '-'}</span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Holders</td>
                                                <td>{numberWithCommas(holders)} Address(es)</td>
                                                <td>Transfers</td>
                                                <td>{numberWithCommas(transfers)}</td>
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

export default withRouter(NFTSummary)
