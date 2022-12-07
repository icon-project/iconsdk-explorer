import React, { Component } from 'react'
import { withRouter } from 'react-router-dom'
import TxBottomTitle from './TxBottomTitle'
import { TxTableHead, TxTableBody, LoadingComponent, NoBox } from 'components'
import {TX_TYPE} from "../../../utils/const";

class TxBottomComponent extends Component {

    render() {
        const { txData, txType, goAllTx, address, tableClassName, noBoxText, networks } = this.props

        const { data, listSize, totalSize, loading } = txData

        const Content = () => {
            if (loading) {
                return <LoadingComponent height="349px" />
            } else if (!data || data.length === 0) {
                return <NoBox text={noBoxText} />
            } else {
                const { fromAddr, toAddr } = data[0]
                return (
                    <div className="contents">
                        <TxBottomTitle txType={txType} listSize={listSize} totalSize={totalSize} goAllTx={goAllTx} fromAddr={fromAddr} toAddr={toAddr} />
                        <div className="table-box">
                            <table className={tableClassName}>
                                <thead>
                                    <TxTableHead txType={txType} />
                                </thead>
                                <tbody>
                                    {(data || []).map((item, index) => {
                                        if(TX_TYPE.CONTRACT_EVENTS === txType) {
                                            if (item.method !== null && item.method.length > 30) {
                                                const words = item.method.split('(');
                                                item.method = words[0] + '\n(' + words[1];
                                            }
                                        }
                                        return <TxTableBody key={index} data={item} txType={txType} address={address} networks={networks}/>
                                    })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                )
            }
        }
        return Content()
    }
}

export default withRouter(TxBottomComponent)
