import React, { Component } from 'react';
import NFTSummary from './NFTSummary'
import NFTTabs from './NFTTabs'
import {
    DetailPage
} from 'components';
import {
    NFT_TABS
} from 'utils/const'

class NFTDetailPage extends Component {

    render() {
        const { nft } = this.props;
        const { loading, error } = nft

        return (
            <DetailPage
                {...this.props}
                loading={loading}
                error={error}
                TABS={NFT_TABS}
                ROUTE="/nft"
                getInfo={contractAddr => { this.props.nftSummary({ contractAddr }) }}
                getList={[
                    contractAddr => {
                        this.props.nftTransfersList({ contractAddr, page: 1, count: 10 })
                    },
                    contractAddr => {
                        this.props.nftHoldersList({ contractAddr, page: 1, count: 10 })
                    },
                    address => {
                        this.props.readContractInformation({ address  })
                    }
                ]}
                InfoComponent={NFTSummary}
                TabsComponent={NFTTabs}
            />
        )
    }
}

export default NFTDetailPage;
