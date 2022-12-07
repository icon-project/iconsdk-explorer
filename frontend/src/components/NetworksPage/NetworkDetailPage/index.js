import React, { Component } from 'react';
import NetworkInfo from "./NetworkInfo";
import NetworkTabs from "./NetworkTabs";
import {
    NETWORK_TABS
} from "../../../utils/const";
import {
    DetailPage
} from 'components';


class NetworkDetailPage extends Component {

  render() {
    const { network } = this.props;
    const { loading, error } = network

    return (
        <DetailPage
            {...this.props}
            loading={loading}
            error={error}
            TABS={NETWORK_TABS}
            ROUTE="/network"
            getInfo={(networkId) => {this.props.networkInfo({ networkId })}}
            getList={[
                (networkId) => {
                    this.props.networkBTPList({networkId, page: 1, count: 10})
                }
            ]}
            InfoComponent={NetworkInfo}
            TabsComponent={NetworkTabs}
        />
    )
  }
}

export default NetworkDetailPage;