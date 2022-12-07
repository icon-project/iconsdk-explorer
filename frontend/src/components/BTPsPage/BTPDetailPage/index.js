import React, { Component } from 'react';
import BTPInfo from "./BTPInfo";
import BTPTabs from "./BTPTabs";
import {
    BTP_TABS
} from "../../../utils/const";
import {
    DetailPage
} from 'components';

class BTPDetailPage extends Component {

  render() {
    const { btp } = this.props;
    const { loading, error } = btp

    return (
        <DetailPage
            {...this.props}
            loading={loading}
            error={error}
            TABS={BTP_TABS}
            ROUTE="/btp"
            getInfo={(height, networkId) => {this.props.btpInfo({ height, networkId })}}
            getList={[
                (height, networkId) => {
                    this.props.btpTxList({ height, networkId, page: 1, count: 10})
                }
            ]}
            InfoComponent={BTPInfo}
            TabsComponent={BTPTabs}
        />
    )
  }
}

export default BTPDetailPage;