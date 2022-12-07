import React, { Component } from 'react'
import { withRouter } from 'react-router-dom'
import {LoadingComponent} from "../../index";
import {dateToUTC, numberWithCommas, utcDateInfo} from "../../../utils/utils";

class BTPInfo extends Component {

    async componentDidMount() {
        this.props.networkList({page: 1, count: 50});
    }

    getNetworkName = (networkId) => {
        return this.props.networks.data.map(network => {
            if(network.networkId === networkId){
                return network.networkName;
            }
            return null;
        });
    }

    getNetworkType = (btpNetworkNetworkId) => {
        return this.props.networks.data.map(network => {
            if(network.networkTypeId === btpNetworkNetworkId){
                return network.networkTypeName;
            }
            return null;
        });
    }

    render() {
        const { btp } = this.props

        const { loading, data } = btp

        const Content = () => {
            if (loading) {
                return <LoadingComponent height="206px" />
            } else {
                const { blockHeight, networkId, prev, messageCnt, messageRoot, createDate, btpNetworkNetworkId } = data
                const isFirst = blockHeight === 0
                return (
                    <div className="screen0">
                        <div className="wrap-holder">
                            <p className="title">BTP</p>
                            <div className="contents">
                                <div className="table-box">
                                    <table className="table-typeB detail">
                                        <tbody>
                                        <tr>
                                            <td>Height</td>
                                            <td>{numberWithCommas(blockHeight)}</td>
                                        </tr>
                                        <tr>
                                            <td>Time Stamp</td>
                                            {isFirst ? (
                                                <td>-</td>
                                            ) : (
                                                <td>
                                                    {dateToUTC(createDate)}
                                                    <em>{utcDateInfo(createDate)}</em>
                                                </td>
                                            )}
                                        </tr>
                                        <tr>
                                            <td>Network Type</td>
                                            <td>{this.getNetworkType(btpNetworkNetworkId)}</td>
                                        </tr>
                                        <tr>
                                            <td>Network</td>
                                            <td>{this.getNetworkName(networkId)}</td>
                                        </tr>
                                        <tr>
                                            <td>Prev Hash</td>
                                            <td>{prev}</td>
                                        </tr>
                                        <tr>
                                            <td>Message Root</td>
                                            <td>{messageRoot}</td>
                                        </tr>
                                        <tr>
                                            <td>No of Messages</td>
                                            <td>{messageCnt}</td>
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

export default withRouter(BTPInfo)
