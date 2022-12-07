import React, { Component } from 'react'
import { withRouter } from 'react-router-dom'
import { LoadingComponent } from "../../index";
import {getNetworkStateAsString} from "utils/utils";

class NetworkInfo extends Component {

    render() {
        const { network } = this.props

        const { loading, data } = network

        const Content = () => {
            if (loading) {
                return <LoadingComponent height="206px" />
            } else {
                const { networkName, networkTypeName, startHeight, open } = data
                return (
                    <div className="screen0">
                        <div className="wrap-holder">
                            <p className="title">Network</p>
                            <div className="contents">
                                <div className="table-box">
                                    <table className="table-typeB detail">
                                        <tbody>
                                        <tr>
                                            <td>Network</td>
                                            <td>{networkName}</td>
                                        </tr>
                                        <tr>
                                            <td>Network Type</td>
                                            <td>{networkTypeName}</td>
                                        </tr>
                                        <tr>
                                            <td>Start Height</td>
                                            <td>{startHeight}</td>
                                        </tr>
                                        <tr>
                                            <td>State</td>
                                            <td>{getNetworkStateAsString(open)}</td>
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

export default withRouter(NetworkInfo)
