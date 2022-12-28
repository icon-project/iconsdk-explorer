import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import { Popup } from './components/';
import { HeaderContainer, FooterContainer } from './containers/'
import MainPage from './pages/MainPage';
import AddressListPage from './pages/AddressListPage';
import AddressDetailPage from './pages/AddressDetailPage';
import ContractDetailPage from './pages/ContractDetailPage'
import ContractListPage from './pages/ContractListPage'
import BlockListPage from './pages/BlockListPage';
import BlockDetailPage from './pages/BlockDetailPage';
import BTPListPage from "./pages/BTPListPage";
import BTPDetailPage from "./pages/BTPDetailPage";
import NetworkListPage from "./pages/NetworkListPage";
import NetworkDetailPage from "./pages/NetworkDetailPage";
import TransactionListPage from './pages/TransactionListPage';
import TransactionDetailPage from './pages/TransactionDetailPage';
import NotFoundPage from './pages/NotFoundPage';
import TokenListPage from './pages/TokenListPage';
import TokenDetailPage from './pages/TokenDetailPage';
import NFTListPage from "./pages/NFTListPage";
import NFTDetailPage from "./pages/NFTDetailPage";
import TxPage from './pages/TxPage'
import { TX_TYPE, SEARCH_TYPE } from './utils/const'
import { getIsSolo } from './utils/utils'
import BodyClassName from 'react-body-classname'


class Routes extends Component {
  constructor(props) {
    super(props)
    this.state = {
      isSolo: true
    }
  }

  async componentDidMount() {
    const isSolo = await getIsSolo()
    this.setState({ isSolo })
  }

  render() {
    const isMain = window.location.pathname === '/'

    return (
      <BodyClassName className='main-back'>
        <div id="app-root">
          <div className="root">
            <div className={`wrap ${isMain ? 'home' : 'sub'}`}>
              <HeaderContainer />
              <Switch>
                <Route onEnter={window.scroll(0, 0)} path='/' component={MainPage} exact />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.ADDRESSES}`} component={AddressListPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.ADDRESSES}/:pageId`} component={AddressListPage} />
                <Route onEnter={window.scroll(0, 0)} path='/address/:addressId' component={AddressDetailPage} exact />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.BLOCKS}`} component={BlockListPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.BLOCKS}/:pageId`} component={BlockListPage} />
                <Route onEnter={window.scroll(0, 0)} path='/block/:blockId' component={BlockDetailPage} exact />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.TRANSACTIONS}`} component={TransactionListPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.TRANSACTIONS}/:pageId`} component={TransactionListPage} />
                <Route onEnter={window.scroll(0, 0)} path='/transaction/:txHash' component={TransactionDetailPage} exact />

                <Route onEnter={window.scroll(0, 0)} path={`/${SEARCH_TYPE.CONTRACTS}`} component={ContractListPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${SEARCH_TYPE.CONTRACTS}/:pageId`} component={ContractListPage} />
                <Route onEnter={window.scroll(0, 0)} path='/contract/:contractId' component={ContractDetailPage} exact />

                {/*BTP*/}
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.BTPS}`} component={BTPListPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.BTPS}/:pageId`} component={BTPListPage} exact />
                <Route onEnter={window.scroll(0, 0)} path='/btp/:btpId/:networkId' component={BTPDetailPage} exact />

                {/*NETWORK*/}
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.NETWORKS}`} component={NetworkListPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.NETWORKS}/:pageId`} component={NetworkListPage} />
                <Route onEnter={window.scroll(0, 0)} path='/network/:networkId' component={NetworkDetailPage} exact />

                {/*NETWORK BTPs*/}
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.NETWORK_BTPS}/:networkId`} component={TxPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.NETWORK_BTPS}/:networkId/:pageId`} component={TxPage} />

                <Route onEnter={window.scroll(0, 0)} path={`/${SEARCH_TYPE.TOKENS}`} component={TokenListPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${SEARCH_TYPE.TOKENS}/:pageId`} component={TokenListPage} exact />
                <Route onEnter={window.scroll(0, 0)} path='/token/:tokenId' component={TokenDetailPage} exact />

                {/*NFT*/}
                <Route onEnter={window.scroll(0, 0)} path={`/${SEARCH_TYPE.NFTS}`} component={NFTListPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${SEARCH_TYPE.NFTS}/:pageId`} component={NFTListPage} exact />
                <Route onEnter={window.scroll(0, 0)} path='/nft/:nftId' component={NFTDetailPage} exact />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.NFT_TRANSFERS}`} component={TxPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.NFT_TRANSFERS}/:pageId`} component={TxPage} />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.NFT_HOLDERS}/:nftId`} component={TxPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.NFT_HOLDERS}/:nftId/:pageId`} component={TxPage} />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.NFT_TX}/:nftId`} component={TxPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.NFT_TX}/:nftId/:pageId`} component={TxPage} />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.TOKEN_TRANSFERS}`} component={TxPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.TOKEN_TRANSFERS}/:pageId`} component={TxPage} />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.ADDRESS_TX}/:addressId/`} component={TxPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.ADDRESS_TX}/:addressId/:pageId`} component={TxPage} />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.ADDRESS_INTERNAL_TX}/:addressId/`} component={TxPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.ADDRESS_INTERNAL_TX}/:addressId/:pageId`} component={TxPage} />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.ADDRESS_TOKEN_TX}/:addressId/`} component={TxPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.ADDRESS_TOKEN_TX}/:addressId/:pageId`} component={TxPage} />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.BLOCK_TX}/:heightId/`} component={TxPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.BLOCK_TX}/:heightId/:pageId`} component={TxPage} />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.CONTRACT_TX}/:contractId/`} component={TxPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.CONTRACT_TX}/:contractId/:pageId`} component={TxPage} />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.CONTRACT_INTERNAL_TX}/:contractId/`} component={TxPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.CONTRACT_INTERNAL_TX}/:contractId/:pageId`} component={TxPage} />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.CONTRACT_TOKEN_TX}/:contractId/`} component={TxPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.CONTRACT_TOKEN_TX}/:contractId/:pageId`} component={TxPage} />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.CONTRACT_EVENTS}/:contractId/`} component={TxPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.CONTRACT_EVENTS}/:contractId/:pageId`} component={TxPage} />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.TOKEN_TX}/:tokenId`} component={TxPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.TOKEN_TX}/:tokenId/:pageId`} component={TxPage} />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.TOKEN_HOLDERS}/:tokenId`} component={TxPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.TOKEN_HOLDERS}/:tokenId/:pageId`} component={TxPage} />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.TRANSACTION_INTERNAL_TX}/:txHash/`} component={TxPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.TRANSACTION_INTERNAL_TX}/:txHash/:pageId`} component={TxPage} />

                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.TRANSACTION_EVENTS}/:txHash`} component={TxPage} exact />
                <Route onEnter={window.scroll(0, 0)} path={`/${TX_TYPE.TRANSACTION_EVENTS}/:txHash/:pageId`} component={TxPage} />

                <Route onEnter={window.scroll(0, 0)} path='/notfound' component={NotFoundPage} />
                <Route onEnter={window.scroll(0, 0)} component={NotFoundPage} />
              </Switch>
            </div>
            <div className="blank"></div>
          </div>
          <FooterContainer />
          <Popup />
        </div>
      </BodyClassName>
    );
  }
}

export default Routes;
